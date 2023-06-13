package io.httpflood.attackdetector.rules;

import java.util.Set;

public interface DetectorRule {
    void consumeIp(String ip, String url);

    Set<String> getIpBlacklist();
}
