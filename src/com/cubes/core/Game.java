package com.cubes.core;

import com.cubes.managers.GraphicsManager;
import com.cubes.world.GameWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: drewmalin
 * Date: 2/10/13
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game {

    private final List<GameObject> gameObjectList;
    private final GameWorld gameWorld;
    private final Camera camera;

    private Game (GameBuilder gb) {
        gameObjectList = new ArrayList<GameObject>();
        gameWorld = gb.gameWorld;
        camera = gb.camera;

        GraphicsManager.MANAGER.setWindowX(gb.x);
        GraphicsManager.MANAGER.setWindowY(gb.y);
        GraphicsManager.MANAGER.setWindowWidth(gb.width);
        GraphicsManager.MANAGER.setWindowHeight(gb.height);
        GraphicsManager.MANAGER.setNearZ(gb.nearZ);
        GraphicsManager.MANAGER.setFarZ(gb.farZ);
        GraphicsManager.MANAGER.setFullScreen(gb.fullscreen);
        GraphicsManager.MANAGER.init(camera);
    }

    public void start() {
        loop();
    }

    private void loop() {
        while (GraphicsManager.MANAGER.isCloseRequested() == false) {
            GraphicsManager.MANAGER.reset();
            GraphicsManager.MANAGER.drawScreen();
        }
        GraphicsManager.MANAGER.destroy();
    }

    public static class GameBuilder {
        private int x, y;
        private int width, height;
        private float frust, nearZ, farZ;
        private boolean fullscreen;
        private GameWorld gameWorld;
        private Camera camera;

        public GameBuilder() {}

        public GameBuilder screenResolution(int width, int height) {
            this.width  = width;
            this.height = height;
            return this;
        }

        public GameBuilder screenPosition(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public GameBuilder clipping(float near, float far) {
            this.nearZ = near;
            this.farZ = far;
            return this;
        }

        public GameBuilder gameWorld(GameWorld gw) {
            this.gameWorld = gw;
            return this;
        }

        public GameBuilder frust(float frust) {
            this.frust = frust;
            return this;
        }

        public GameBuilder fullscreen(boolean fullscreen) {
            this.fullscreen = fullscreen;
            return this;
        }

        public GameBuilder camera(Camera camera) {
            this.camera = camera;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
}
