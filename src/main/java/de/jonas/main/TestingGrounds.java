package de.jonas.main;

import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.JSONUtils;
import de.jonas.engine.data.UserData;
import org.json.simple.JSONObject;

public class TestingGrounds {

    public static void main(String[] args) {
        UserData.loadData();
        JSONObject jsonObject = JSONUtils.getJSONFile("data/settings.json");
        Object jsonObject1 =  JSONUtils.getObjectFromJSON(jsonObject,"client.window.vsync");
        Console.printDebug("RESULT: ",jsonObject1);
        priato("HasdadsadasdadKO");
    }
    public static void priato(String x) {
        System.out.println("YOU JUST TESTED THIS METHODSASD" + x);
    }

}
