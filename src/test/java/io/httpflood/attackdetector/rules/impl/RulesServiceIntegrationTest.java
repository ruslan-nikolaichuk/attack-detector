package io.httpflood.attackdetector.rules.impl;

import io.httpflood.attackdetector.rules.RulesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;

@SpringBootTest
public class RulesServiceIntegrationTest {

    @Autowired
    RulesServiceImpl rulesService;

    @Autowired
    ThroughputRule throughputRule;
    @Test
    void testBlacklistWithThreshold() {


        throughputRule.threshold = 3;

        throughputRule.consumeIp("127.0.0.1","/");
        throughputRule.consumeIp("127.0.0.2","/");
        throughputRule.consumeIp("127.0.0.2","/");
        throughputRule.consumeIp("127.0.0.3","/");
        throughputRule.consumeIp("127.0.0.3","/");
        throughputRule.consumeIp("127.0.0.3","/");

        Set blacklist = rulesService.getIpBlacklist();

        Assert.isTrue(blacklist.size()==1, "Blacklist has to contain only one element");
        Assert.isTrue(blacklist.contains("127.0.0.3"), "127.0.0.3 is expected to be blocked");

    }

    @Test
    void testRuleInjection(){
        Assert.isTrue(rulesService.rules.size()==1,"Rule service has contain 0ne rule");
    }
}
