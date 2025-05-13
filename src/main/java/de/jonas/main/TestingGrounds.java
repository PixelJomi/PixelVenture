package de.jonas.main;

import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.FileUtils;
import de.jonas.engine.utils.JSONUtils;
import org.json.simple.JSONObject;

public class TestingGrounds {

    public static void main(String[] args) {
        JSONObject jsonObject = JSONUtils.getJSONFile("settings.json");
        Object jsonObject1 =  JSONUtils.getObjectFromJSON(jsonObject,"client.vsync");
        Console.printDebug("RESULT: ",jsonObject1);
    }
}
