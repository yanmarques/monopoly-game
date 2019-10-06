package com.prj.commom;

import com.prj.entity.Player;

public class Logger {
    private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Monopoly");

    public static void shPlayer(Player player, String text) {
        info("Player ["+ player.getName() +"]: "+ text);
    }

    public static void info(String text) {
        logger.info(text);
    }

    public static void debug(String text) {
        logger.finer(text);
    }

    public static void error(String text) {
        logger.warning(text);
    }
}
