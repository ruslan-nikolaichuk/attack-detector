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

    ThroughputRule throughputRule;
    @Test
    void testBlacklistWithThreshold() {

        throughputRule = new ThroughputRule();
        throughputRule.threshold = 3;

        throughputRule.consumeIp("127.0.0.1","/");
        throughputRule.consumeIp("127.0.0.2","/");
        throughputRule.consumeIp("127.0.0.2","/");
        throughputRule.consumeIp("127.0.0.3","/");
        throughputRule.consumeIp("127.0.0.3","/");
        throughputRule.consumeIp("127.0.0.3","/");

        Set blacklist = throughputRule.getIpBlacklist();

        Assert.isTrue(blacklist.size()==1, "Blacklist has to contain only one element");
        Assert.isTrue(blacklist.contains("127.0.0.3"), "127.0.0.3 is expected to be blocked");

    }


    @Test
    void testBlacklistWithZeroThreshold(){

        throughputRule = new ThroughputRule();
        throughputRule.threshold = 0;

        throughputRule.consumeIp("127.0.0.1","/");
        throughputRule.consumeIp("127.0.0.2","/");
        throughputRule.consumeIp("127.0.0.2","/");
        throughputRule.consumeIp("127.0.0.3","/");
        throughputRule.consumeIp("127.0.0.3","/");
        throughputRule.consumeIp("127.0.0.3","/");

        Set blacklist = throughputRule.getIpBlacklist();

        Assert.isTrue(blacklist.size()==3, "Blacklist has to contain 3 elements");
    }

    @Test
    void testBlacklistWithNegativeThreshold(){

        throughputRule = new ThroughputRule();
        throughputRule.threshold = -1000;

        throughputRule.consumeIp("127.0.0.1","/");
        throughputRule.consumeIp("127.0.0.2","/");
        throughputRule.consumeIp("127.0.0.2","/");
        throughputRule.consumeIp("127.0.0.3","/");
        throughputRule.consumeIp("127.0.0.3","/");
        throughputRule.consumeIp("127.0.0.3","/");

        Set blacklist = throughputRule.getIpBlacklist();

        Assert.isTrue(blacklist.size()==3, "Blacklist has to contain 3 elements");
        Assert.isTrue(blacklist.contains("127.0.0.3"), "127.0.0.3 is expected to be blocked");
    }

    @Test
    void testBlacklistWithNulls(){

        throughputRule = new ThroughputRule();
        throughputRule.threshold = -1000;

        throughputRule.consumeIp(null,null);
        throughputRule.consumeIp(null,null);


        Set blacklist = throughputRule.getIpBlacklist();

    }
}
