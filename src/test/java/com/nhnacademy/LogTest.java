package com.nhnacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger("TEST");

        logger.trace("Hello");
    }
}
