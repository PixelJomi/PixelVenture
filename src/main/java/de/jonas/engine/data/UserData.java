package de.jonas.engine.data;

import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.JSONUtils;
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

    public static void loadData() {
        JSONObject settingsJSONFile = JSONUtils.getJSONFile("data/settings.json");
        DEBUG = JSONUtils.objectToBoolean(JSONUtils.getObjectFromJSON(settingsJSONFile,"dev.debug"), DEBUG);
        FPS = JSONUtils.objectToInteger(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.render.fps"),FPS);
        VSYNC = JSONUtils.objectToBoolean(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.window.vsync"),VSYNC);
        RENDER_DISTANCE = JSONUtils.objectToInteger(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.render.render_distance"),RENDER_DISTANCE);
        FOV = JSONUtils.objectToFloat(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.render.camera.fov"),FOV);
        START_WIDTH = JSONUtils.objectToInteger(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.window.startWidth"),START_WIDTH);
        START_HEIGHT = JSONUtils.objectToInteger(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.window.startHeight"),START_HEIGHT);
        CAMERA_NEAR = JSONUtils.objectToFloat(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.render.camera.cameraNear"),CAMERA_NEAR);
        CAMERA_FAR = JSONUtils.objectToFloat(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.render.camera.cameraFar"),CAMERA_FAR);


        //DON'T REMOVE IG JUST DEBUG STUFF...
        Console.printSucc("Values initialized!",null);
    }
}
