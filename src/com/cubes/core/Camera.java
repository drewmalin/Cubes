package com.cubes.core;

import com.cubes.Globals;
import org.lwjgl.util.vector.Vector3f;


/**
 * Created with IntelliJ IDEA.
 * User: drewmalin
 * Date: 2/10/13
 * Time: 5:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Camera {
    public Vector3f target;
    public Vector3f up;
    public Vector3f position;

    public Camera() {
        position = new Vector3f(Globals.DEFAULT_CAM_X,
                Globals.DEFAULT_CAM_Y,
                Globals.DEFAULT_CAM_Z);
        target = new Vector3f(Globals.DEFAULT_CAM_TARGET_X,
                Globals.DEFAULT_CAM_TARGET_Y,
                Globals.DEFAULT_CAM_TARGET_Z);
        up = new Vector3f(Globals.DEFAULT_CAM_UP_X,
                Globals.DEFAULT_CAM_UP_Y,
                Globals.DEFAULT_CAM_UP_Z);
    }
}
