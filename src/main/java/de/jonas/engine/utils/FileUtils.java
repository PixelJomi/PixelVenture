package de.jonas.engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.stream.Collectors;

public class FileUtils {

    public static String loadAsString(String pathString) {
        return loadAsString(pathString, Charset.defaultCharset());
    }

    public static String loadAsString(Path path) {
        return loadAsString(path, Charset.defaultCharset());
    }

    public static String loadAsString(String pathString, Charset charset) {
        Path path = getPath(pathString);
        if (path != null && Files.exists(path)) {
            return loadAsString(path, charset);
        } else {
            return loadResourceAsString(pathString, charset);
        }
    }

    public static String loadAsString(Path path, Charset charset) {
        try {
            return Files.readString(path, charset);
        } catch (IOException e) {
            Console.printError("Ran into IOException while reading file from path!", path.toString());
            return "";
        }
    }

    public static Path getPath(String pathString) {
        try {
            return Paths.get(pathString);
        } catch (InvalidPathException e) {
            Console.printError("Could not get path!", pathString);
            return null;
        }
    }

    public static String loadResourceAsString(String resourcePath, Charset charset) {
        try (InputStream stream = FileUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (stream == null) {
                Console.printError("Resource not found in classpath!", resourcePath);
                return "";
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            Console.printError("Ran into IOException while reading resource!", resourcePath);
            return "";
        }
    }

    public static InputStream loadAsStream(String pathString) {
        Path path = getPath(pathString);
        if (path != null && Files.exists(path)) {
            try {
                return Files.newInputStream(path);
            } catch (IOException e) {
                Console.printError("Failed to open file stream!", path.toString());
                return null;
            }
        } else {
            InputStream stream = FileUtils.class.getClassLoader().getResourceAsStream(pathString);
            if (stream == null) {
                Console.printError("Resource not found in classpath!", pathString);
            }
            return stream;
        }
    }

}