package de.jonas.engine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Map;

public class FileUtils {
    public static String loadAsString(String path) {
        Console.printDebug("Loading file...",path);
        StringBuilder result = new StringBuilder();

        InputStream resourceStream = FileUtils.class.getResourceAsStream(path);

        if (resourceStream == null) {
            Console.printError("Getting resource stream resulted in null!",path);
            return "";
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream))) {
            String line = "";
            Console.printDebug("Reading file...",path);
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            Console.printError("Couldn't find the file at: ",path);
        }

        Console.printSucc("Successfully read file!",path);
        return result.toString();
    }
}
