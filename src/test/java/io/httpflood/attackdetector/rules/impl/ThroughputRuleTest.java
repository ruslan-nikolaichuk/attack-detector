package io.httpflood.attackdetector.rules.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;

import static org.springframework.util.Assert.notNull;


public class ThroughputRuleTest {

    ThroughputRule rule;
    @Test
    void testBlacklistWithThreashold() {

        rule = new ThroughputRule();
        rule.threshold = 3;

        rule.consumeIp("127.0.0.1","/");
        rule.consumeIp("127.0.0.2","/");
        rule.consumeIp("127.0.0.2","/");
        rule.consumeIp("127.0.0.3","/");
        rule.consumeIp("127.0.0.3","/");
        rule.consumeIp("127.0.0.3","/");

        Set blacklist = rule.getIpBlacklist();

        Assert.isTrue(blacklist.size()==1, "Blacklist has to contain only one element");
        Assert.isTrue(blacklist.contains("127.0.0.3"), "127.0.0.3 is expected to be blocked");

    }
}
