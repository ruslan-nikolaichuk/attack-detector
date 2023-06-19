package io.httpflood.attackdetector.rules;

import org.springframework.stereotype.Service;

import java.util.Set;


public interface RulesService {
    Set<String> getIpBlacklist();
}
