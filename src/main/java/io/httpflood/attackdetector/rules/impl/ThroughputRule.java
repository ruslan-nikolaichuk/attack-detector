package io.httpflood.attackdetector.rules.impl;

import io.httpflood.attackdetector.rules.DetectorRule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ThroughputRule implements DetectorRule {

    @Value("${attackDetector.rules.throughput.threshold:1000}")
    protected Integer threshold;
    private Map<String, Integer> requestsPerIp = new HashMap<>();

    @Override
    public synchronized void consumeIp(String ip, String url) {
        requestsPerIp.compute(ip, (k, v) -> (v == null) ? 1 : v + 1);
    }

    @Override
    public synchronized Set<String> getIpBlacklist() {
        Map<String, Integer> collectedRequests = swapMap();

        return collectedRequests.entrySet().stream()
                       .filter(this::thresholdPredicate)
                       .map(Entry::getKey)
                       .collect(Collectors.toSet());
    }

    private Map<String, Integer> swapMap() {
        Map<String, Integer> temp = requestsPerIp;
        requestsPerIp = new HashMap<>();
        return temp;
    }

    private Boolean thresholdPredicate(Entry<String, Integer> e) {
        return e.getValue() >= threshold;
    }
}
