package io.httpflood.attackdetector.rules.impl;

import io.httpflood.attackdetector.rules.DetectorRule;
import io.httpflood.attackdetector.rules.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Observable;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RulesServiceImpl extends Observable implements DetectorRule {
    @Autowired
    protected Set<DetectorRule> rules;

    @Override
    public void consumeIp(String ip, String url) {
        rules.stream().forEach(rule -> rule.consumeIp(ip, url));
    }

    @Override
    public Set<String> getIpBlacklist() {
        return rules.stream().map(DetectorRule::getIpBlacklist).flatMap(Set::stream).collect(Collectors.toSet());
    }


}
