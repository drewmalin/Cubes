package com.cubes.core;

import com.cubes.logger.GameLogger;

import com.cubes.world.GameWorld;
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
    /*
     * Command line arguments:
     * 0 - Boolean, logging status (true = on)
     */
    public static void main(String[] args) {
        GameLogger.log(GameLogger.GameLevel.INFO, "Starting up...");
        GameLogger.init(Boolean.parseBoolean(args[0]));

        Game.GameBuilder gameBuilder = new Game.GameBuilder();

        gameBuilder.screenResolution(800, 600);
        gameBuilder.screenPosition(100, 100);
        gameBuilder.clipping(-1f, 1f);
        gameBuilder.gameWorld(new GameWorld());
        gameBuilder.camera(new Camera());

        Game game = gameBuilder.build();

        GameLogger.log(GameLogger.GameLevel.INFO, "Loading saved data...");
        // Load game data here!
        GameLogger.log(GameLogger.GameLevel.INFO, "Creating game world...");
        // Create game world here!
        GameLogger.log(GameLogger.GameLevel.INFO, "Creating game entities...");
        // Create game entities here!

        GameLogger.log(GameLogger.GameLevel.INFO, "Game created!");
        game.start();
    }
}
