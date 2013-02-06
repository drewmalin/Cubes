package com.cubes.core;

import com.cubes.logger.GameLogger;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: drewmalin
 * Date: 2/3/13
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        /*
         * Command line arguments:
         * 0 - Boolean, logging status (true = on)
         */
        GameLogger.init(Boolean.parseBoolean(args[0]));
        GameLogger.log(GameLogger.GameLevel.FINE, "Starting up...");
        GameLogger.log(GameLogger.GameLevel.SEVERE, "severe message!");
    }
}
