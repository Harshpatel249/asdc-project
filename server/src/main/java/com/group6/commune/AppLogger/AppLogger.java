package com.group6.commune.AppLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AppLogger {
        private static final Logger logger = LoggerFactory.getLogger(AppLogger.class);
        public static Logger getLogger(){
            return logger;
    }
}
