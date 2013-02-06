package com.cubes.logger;

import java.io.IOException;
import java.util.logging.*;

/**
 * Created with IntelliJ IDEA.
 * User: drewmalin
 * Date: 2/3/13
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameLogger {

    private final static Logger LOGGER = Logger.getLogger(GameLogger.class.getPackage().getName());
    private static boolean enabled = false;

    public enum GameLevel {
        INFO,
        FINE,
        WARNING,
        SEVERE;
    }

    public static void init(boolean flag) {
        String timestamp = Long.toString(System.currentTimeMillis()).substring(6);
        enabled = flag;
        LOGGER.setLevel(Level.ALL);

        try {
            FileHandler fileHandler = new FileHandler("logs/build_" + timestamp + ".log");
            fileHandler.setFormatter(
                new Formatter() {
                    public String format(LogRecord rec) {
                        String ret = String.format("%-30s %-10s %-30s %s\n",
                                new java.util.Date(),
                                "["+rec.getLevel()+"]",
                                rec.getSourceClassName(),
                                formatMessage(rec));
                        return ret;
                    }
                }
            );
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.log(Level.WARNING,"something to log");

    }

    public static void log(GameLogger.GameLevel level, String message) {
        if (enabled) {
            switch (level) {
                case INFO:
                    LOGGER.info(message);
                    break;
                case FINE:
                    LOGGER.fine(message);
                    break;
                case WARNING:
                    LOGGER.warning(message);
                    break;
                case SEVERE:
                    LOGGER.severe(message);
                    break;
            }
        }
    }
}
