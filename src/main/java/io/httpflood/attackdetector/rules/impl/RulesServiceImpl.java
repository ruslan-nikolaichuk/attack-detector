package io.httpflood.attackdetector.rules.impl;

import io.httpflood.attackdetector.rules.DetectorRule;
import io.httpflood.attackdetector.rules.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RulesServiceImpl implements RulesService {
    @Autowired
    protected Set<DetectorRule> rules;
    @Override
    public Set<String> getIpBlacklist() {
        return rules.stream().map(DetectorRule::getIpBlacklist).flatMap(Set::stream).collect(Collectors.toSet());
    }


}
