package de.jonas.engine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Map;

public class FileUtils {
    public static String loadAsString(String path) {
        StringBuilder result = new StringBuilder();

        InputStream resourceStream = FileUtils.class.getResourceAsStream(path);

        if (resourceStream == null) {return "";}

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            Console.printError("Couldn't find the file at: ",path);
        }
        return result.toString();
    }
}
