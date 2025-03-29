package de.jonas.engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    public static String loadAsString(String path) {
        StringBuilder result = new StringBuilder();
        Console.printDebug("Loading resource: " + FileUtils.class.getResource(path),path);

        InputStream resourceStream = FileUtils.class.getResourceAsStream(path);

        if (resourceStream == null) {
            Console.printError("Resource not found at path: ",path);
            return "";
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            Console.printError("Couldn't find the file at: ",path);
        }
        Console.printSucc("Got FileData!",path);
        return result.toString();
    }
}
