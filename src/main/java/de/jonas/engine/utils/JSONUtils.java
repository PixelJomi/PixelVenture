package de.jonas.engine.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A utility class for working with JSON files, specifically for loading JSON content
 * into {@link JSONObject}s and extracting specific data using a path-like string.
 * This class uses the 'json-simple' library for JSON parsing.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
public class JSONUtils {
    /**
     * Reads a JSON file from the specified path and parses its content into a {@link JSONObject}.
     * This method handles {@link FileNotFoundException} if the file does not exist,
     * and {@link IOException} or {@link ParseException} if there's an issue reading or parsing the file.
     * Errors are logged to the console using {@link Console#printError(String, Object)}.
     *
     * @param path The string path to the JSON file to be read.
     * @return A {@link JSONObject} representing the parsed content of the file, or {@code null}
     * if the file could not be found or parsed.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static JSONObject getJSONFile(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            // Parse the file content into a generic Object, then cast to JSONObject
            Object object = new JSONParser().parse(fileReader);
            return (JSONObject) object;
        } catch (FileNotFoundException e) {
            Console.printError("Could not find file: " + path, e);
        } catch (IOException | ParseException e) {
            Console.printError("Could not parse file: " + path, e);
        }
        return null;
    }

    /**
     * Extracts an object from a {@link JSONObject} using a dot-separated "JSONPath" string.
     * This method navigates through nested JSON objects to find the desired value.
     * For example, a `jsonPath` like "level.player.name" would retrieve the "name" field
     * from the "player" object nested within the "level" object.
     *
     * @param jsonObject The starting {@link JSONObject} from which to retrieve the value.
     * @param jsonPath   A dot-separated string representing the path to the desired object or value
     * within the JSON structure (e.g., "key1.nestedKey.valueName").
     * @return The object found at the specified `jsonPath`. This can be a {@link String},
     * {@link Long}, {@link Double}, {@link Boolean}, another {@link JSONObject}, or {@code null}
     * if the starting `jsonObject` is null, a part of the path is not found, or the path
     * does not lead to a concrete value.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Object getObjectFromJSON(JSONObject jsonObject, String jsonPath) {
        if (jsonObject == null) {
            Console.printError("Starting JSONObject was null!", jsonObject);
            return null;
        }

        // Split the jsonPath into individual keys/segments
        String[] pathStrings = jsonPath.split("[.]");
        Object object = null; // Initialize object to null

        // Iterate through each segment of the path
        for (int i = 0; i < pathStrings.length; i++) {
            // Attempt to get the object for the current path segment
            object = jsonObject.get(pathStrings[i]);

            if (object == null) {
                // If a segment leads to null, the path is invalid or the value doesn't exist
                Console.printError("Getting JSONObject of \"" + pathStrings[i] + "\" resulted in null.", jsonPath);
                return null;
            } else if (object instanceof JSONObject) {
                // If the current object is another JSONObject, continue traversing
                jsonObject = (JSONObject) object;
                // If this is the last segment and it's a JSONObject, it means the path ended on an object
                if (i == pathStrings.length - 1) {
                    return jsonObject;
                }
            } else {
                // If it's not a JSONObject and not null, it must be the final value
                return object;
            }
        }
        // This point should ideally not be reached if the path leads to a value or an object.
        // It indicates the loop completed without returning a concrete value, implying an issue.
        Console.printError("Could not find value in given JSONPath!", jsonPath);
        return null;
    }
}