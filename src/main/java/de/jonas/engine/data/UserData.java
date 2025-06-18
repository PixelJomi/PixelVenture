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

    public static void loadData() {
        JSONObject settingsJSONFile = JSONUtils.getJSONFile("data/settings.json");
        DEBUG = ObjectUtils.objectToBoolean(JSONUtils.getObjectFromJSON(settingsJSONFile,"dev.debug"), DEBUG);
        FPS = ObjectUtils.objectToInteger(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.render.fps"),FPS);
        VSYNC = ObjectUtils.objectToBoolean(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.window.vsync"),VSYNC);
        RENDER_DISTANCE = ObjectUtils.objectToInteger(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.render.render_distance"),RENDER_DISTANCE);
        FOV = ObjectUtils.objectToFloat(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.render.camera.fov"),FOV);
        START_WIDTH = ObjectUtils.objectToInteger(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.window.startWidth"),START_WIDTH);
        START_HEIGHT = ObjectUtils.objectToInteger(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.window.startHeight"),START_HEIGHT);
        CAMERA_NEAR = ObjectUtils.objectToFloat(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.render.camera.cameraNear"),CAMERA_NEAR);
        CAMERA_FAR = ObjectUtils.objectToFloat(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.render.camera.cameraFar"),CAMERA_FAR);
        WIREFRAME = ObjectUtils.objectToBoolean(JSONUtils.getObjectFromJSON(settingsJSONFile,"dev.wireframe"),WIREFRAME);
        MOUSE_SENSITIVITY = ObjectUtils.objectToFloat(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.input.mouseSensitivity"),MOUSE_SENSITIVITY);


        //DON'T REMOVE IG JUST DEBUG STUFF...
        Console.printSucc("Values initialized!",null);
    }
}
