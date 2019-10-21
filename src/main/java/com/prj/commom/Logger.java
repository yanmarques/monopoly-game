package com.prj.commom;

import java.util.logging.Level;

public class Logger {
    private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Monopoly");

    static {
        setLevel(Level.FINEST);
    }

    public static void showInfo(Object header, String message) {
        info(header + ": " + message);
    }

    public static void info(String text) {
        logger.info(text);
    }

    public static void debug(String text) {
        logger.fine(text);
    }

    public static void error(String text) {
        logger.warning(text);
    }

    public static void setLevel(Level level) {
        logger.setLevel(level);
    }
}
