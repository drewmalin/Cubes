package com.cubes.managers;

import com.cubes.Globals;

import com.cubes.core.Camera;
import com.cubes.logger.GameLogger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;

/**
 * Created with IntelliJ IDEA.
 * User: drewmalin
 * Date: 2/3/13
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public enum GraphicsManager {
    MANAGER;

    private int windowWidth         =   Globals.DEFAULT_WINDOW_WIDTH;
    private int windowHeight        =   Globals.DEFAULT_WINDOW_HEIGHT;
    private int windowX             =   Globals.DEFAULT_WINDOW_X;
    private int windowY             =   Globals.DEFAULT_WINDOW_Y;
    private float nearZ             =   Globals.DEFAULT_NEAR_Z;
    private float farZ              =   Globals.DEFAULT_FAR_Z;
    private float frust             =   Globals.DEFAULT_FRUST;
    private boolean fullscreen      =   Globals.DEFAULT_FULLSCREEN;
    private int antialias           =   Globals.DEFAULT_ANTIALIAS;
    private boolean verticalSync    =   Globals.DEFAULT_VSYNC;

    public void init(Camera c) {
        createDisplay();
        createGraphics(c);
    }

    private void createDisplay() {
        GameLogger.log(GameLogger.GameLevel.FINE, "Creating LWJGL display...");
        try {
            Display.setVSyncEnabled(verticalSync);
            Display.setDisplayMode(new DisplayMode(windowWidth, windowHeight));
            if (fullscreen) {
                for (DisplayMode dm : Display.getAvailableDisplayModes()) {
                    if (dm.toString().contains(windowWidth + " x " + windowHeight + " x " + 32)) {
                        Display.setDisplayMode(dm);
                        Display.setFullscreen(true);
                        break;
                    }
                }
            }
            if (antialias > 0) {
                PixelFormat format = new PixelFormat(32, 0, 24, 8, antialias);
                Pbuffer pbuffer = new Pbuffer(windowWidth, windowHeight, format, null);
                pbuffer.makeCurrent();
                Display.create(format);
            }
            else {
                Display.create();
            }
        } catch (LWJGLException e) {
            GameLogger.log(GameLogger.GameLevel.SEVERE, "LWJGL creation failed!");
            e.printStackTrace();
            System.exit(1);
        }
        GameLogger.log(GameLogger.GameLevel.FINE, "LWJGL display successfully created:");
        GameLogger.log(GameLogger.GameLevel.FINE, "Resolution: "+windowHeight+"x"+windowHeight);
        GameLogger.log(GameLogger.GameLevel.FINE, "Position: "+windowX+", "+windowY);
        GameLogger.log(GameLogger.GameLevel.FINE, "AA: "+antialias+"x");
        GameLogger.log(GameLogger.GameLevel.FINE, "VSync: "+verticalSync);
    }

    private void createGraphics2D() {
        GameLogger.log(GameLogger.GameLevel.FINE, "Creating OpenGL graphics...");
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glLoadIdentity();
        GL11.glOrtho(0, windowWidth, windowHeight, 0, nearZ, farZ);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GameLogger.log(GameLogger.GameLevel.FINE, "OpenGL graphics successfully created.");
    }

    private void createGraphics(Camera c) {
        GameLogger.log(GameLogger.GameLevel.FINE, "Creating OpenGL graphics...");
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluPerspective(frust, windowWidth / windowHeight, nearZ, farZ);
        GLU.gluLookAt(c.position.x, c.position.y, c.position.z,
                c.target.x, c.target.y, c.target.z,
                c.up.x, c.up.y, c.up.z);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GameLogger.log(GameLogger.GameLevel.FINE, "OpenGL graphics successfully created.");
    }

    public void reset() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    public void drawScreen() {
        Display.update();
        Display.sync(60);
    }

    public void destroy() {
        GameLogger.log(GameLogger.GameLevel.WARNING, "Graphics destruction requested.");
        Display.destroy();
        GameLogger.log(GameLogger.GameLevel.WARNING, "Graphics successfully destroyed.");
    }

    public boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

    // ------    Getters/Setters   ------ //

    public void setWindowX(int x)           { this.windowX = x; }
    public void setWindowY(int y)           { this.windowY = y; }
    public void setWindowHeight(int height) { this.windowHeight = height; }
    public void setWindowWidth(int width)   { this.windowWidth = width; }
    public void setNearZ(float nearZ)       { this.nearZ = nearZ; }
    public void setFarZ(float farZ)         { this.farZ = farZ; }
    public void setFullScreen(boolean fullScreen) { this.fullscreen = fullScreen; }

    public int getWindowX()         { return this.windowX; }
    public int getWindowY()         { return this.windowY; }
    public int getWindowHeight()    { return this.windowHeight; }
    public int getWindowWidth()     { return this.windowWidth; }
    public float getNearZ()         { return this.nearZ; }
    public float getFarZ()          { return this.farZ; }
    public boolean getFullScreen()  { return this.fullscreen; }

    public void setCamera(Camera camera) {
    }
}
