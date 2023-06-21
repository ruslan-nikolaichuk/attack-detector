package io.httpflood.attackdetector.rules.impl;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockedIpLogger implements Observer {
    private static final Logger logger = Logger.getLogger(BlockedIpLogger.class.getName());

    @Override
    public void update(Observable o, Object arg) {
        logger.log(Level.INFO, arg.toString());
    }
}
