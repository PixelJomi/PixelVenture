package de.jonas.engine.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONUtils {
    public static JSONObject getJSONFile(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            Object object = new JSONParser().parse(fileReader);
            return (JSONObject) object;
        } catch (FileNotFoundException e) {
            Console.printError("Could not find file: " + path, e);
        } catch (IOException | ParseException e) {
            Console.printError("Could not parse file: " + path, e);
        }
        return null;
    }

    public static Object getObjectFromJSON(JSONObject jsonObject, String jsonPath) {
        if (jsonObject == null) {
            Console.printError("Starting JSONObject was null!",jsonObject);
            return null;
        }
        String[] pathStrings = jsonPath.split("[.]");
        Object object;
        for (int i = 0; i < pathStrings.length; i++) {
            object = jsonObject.get(pathStrings[i]);
            if (object == null) {
                Console.printError("Getting JSONObject of \"" + pathStrings[i] + "\" resulted in null.",jsonPath);
                return  null;
            } else if (object instanceof JSONObject) {
                jsonObject = (JSONObject) object;
            } else {
                return object;
            }
        }
        Console.printError("Cold not find value in given JSONPath!",jsonPath);
        return null;
    }
}