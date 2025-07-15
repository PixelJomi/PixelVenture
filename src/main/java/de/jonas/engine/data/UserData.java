package de.jonas.engine.data;

import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.FileUtils;
import de.jonas.engine.utils.JSONUtils;
import de.jonas.engine.utils.ObjectUtils;
import org.json.simple.JSONObject;

public class UserData {
    public static int FPS = 120;
    public static boolean DEBUG = false;
    public static boolean VSYNC = true;
    public static int RENDER_DISTANCE = 12;
    public static float FOV = 90.0f;
    public static int START_WIDTH = 1280;
    public static int START_HEIGHT = 760;
    public static float CAMERA_NEAR = 0.1f;
    public static float CAMERA_FAR = 1000.0f;
    public static boolean WIREFRAME = false;
    public static float MOUSE_SENSITIVITY = 0.05f;

    public static void loadData(JSONData jsonData) {
        DEBUG = jsonData.settings.dev.debug;
        FPS = jsonData.settings.client.render.fps;
        VSYNC = jsonData.settings.client.window.vsync;
        RENDER_DISTANCE = jsonData.settings.client.render.render_distance;
        FOV = jsonData.settings.client.render.camera.fov;
        START_WIDTH = jsonData.settings.client.window.startWidth;
        START_HEIGHT = jsonData.settings.client.window.startHeight;
        CAMERA_NEAR = jsonData.settings.client.render.camera.cameraNear;
        CAMERA_FAR = jsonData.settings.client.render.camera.cameraFar;
        WIREFRAME = jsonData.settings.dev.wireframe;
        MOUSE_SENSITIVITY = jsonData.settings.client.input.mouseSensitivity;

        //DON'T REMOVE IG JUST DEBUG STUFF...
        Console.printSucc("Values initialized!",null);
    }
}
