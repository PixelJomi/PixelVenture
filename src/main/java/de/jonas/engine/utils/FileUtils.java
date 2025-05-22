package de.jonas.engine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class FileUtils {
    public static String loadAsString(String pathString) {
        return loadAsString(pathString,Charset.defaultCharset());
        //boolean = 8
        //byte = 8

        //char = 16
        //short = 16

        //int = 32
        //float = 32

        //long = 64
        //double = 64
    }

    public static String loadAsString(Path path) {
        return loadAsString(path,Charset.defaultCharset());
    }

    public static String loadAsString(String pathString, Charset charset) {
        Path path = getPath(pathString);
        return loadAsString(path,charset);
    }

    public static String loadAsString(Path path, Charset charset) {
        try {
            return Files.readString(path, charset);
        } catch (IOException e) {
            Console.printError("Ran into IOException while reading file!",path.toString());
            return "";
        }
    }

    public static Path getPath(String pathString) {
        try {
            return Paths.get(pathString);
        } catch (InvalidPathException e) {
            Console.printError("Could not get path!",pathString);
            return null;
        }
    }
}
