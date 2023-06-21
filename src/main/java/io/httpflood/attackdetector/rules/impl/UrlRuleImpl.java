package io.httpflood.attackdetector.rules.impl;

import io.httpflood.attackdetector.rules.DetectorRule;

import java.util.Set;

public class UrlRuleImpl implements DetectorRule {
    @Override
    public void consumeIp(String ip, String url) {

    }

    @Override
    public Set<String> getIpBlacklist() {
        return null;
    }
}
