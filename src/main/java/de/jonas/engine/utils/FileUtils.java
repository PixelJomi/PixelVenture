package de.jonas.engine.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static String loadAsString(String pathString) {
        return loadAsString(pathString,Charset.defaultCharset());
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
