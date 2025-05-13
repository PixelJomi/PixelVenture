package de.jonas.main.data;

import de.jonas.engine.utils.JSONUtils;
import org.json.simple.JSONObject;

public class UserData {
    public static int FPS = 120;
    public static boolean DEBUG = false;
    public static boolean VSYNC = true;
    public static int RENDER_DISTANCE = 12;
    public static float FOV = 90.0f;

    public static void loadData() {
        JSONObject settingsJSONFile = JSONUtils.getJSONFile("data/settings.json");
        DEBUG = JSONUtils.objectToBoolean(JSONUtils.getObjectFromJSON(settingsJSONFile,"dev.debug"), DEBUG);
        FPS = JSONUtils.objectToInteger(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.fps"),FPS);
        VSYNC = JSONUtils.objectToBoolean(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.vsync"),VSYNC);
        RENDER_DISTANCE = JSONUtils.objectToInteger(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.render_distance"),RENDER_DISTANCE);
        FOV = JSONUtils.objectToFloat(JSONUtils.getObjectFromJSON(settingsJSONFile,"client.fov"),FOV);
    }
}
