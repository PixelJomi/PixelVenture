package de.jonas.engine.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A utility class providing convenient methods for file operations, primarily
 * focused on loading file content as strings. This class simplifies common file
 * reading tasks and includes basic error handling.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
public class FileUtils {
    /**
     * Loads the content of a file located at the specified string path into a {@code String}.
     * This method uses the system's default character set to decode the file content.
     * If an {@link IOException} occurs during reading, an error is logged to the console.
     *
     * @param pathString The string representation of the file's path.
     * @return The content of the file as a {@code String}, or an empty string if an error occurs.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static String loadAsString(String pathString) {
        return loadAsString(pathString, Charset.defaultCharset());
    }

    /**
     * Loads the content of a file specified by a {@link Path} object into a {@code String}.
     * This method uses the system's default character set to decode the file content.
     * If an {@link IOException} occurs during reading, an error is logged to the console.
     *
     * @param path The {@link Path} object representing the file.
     * @return The content of the file as a {@code String}, or an empty string if an error occurs.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static String loadAsString(Path path) {
        return loadAsString(path, Charset.defaultCharset());
    }

    /**
     * Loads the content of a file located at the specified string path into a {@code String},
     * using the given {@link Charset} to decode the file content.
     * This method first converts the string path to a {@link Path} object.
     * If an {@link IOException} occurs during reading, an error is logged to the console.
     *
     * @param pathString The string representation of the file's path.
     * @param charset    The {@link Charset} to use for decoding the file content (e.g., {@code StandardCharsets.UTF_8}).
     * @return The content of the file as a {@code String}, or an empty string if an error occurs.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static String loadAsString(String pathString, Charset charset) {
        Path path = getPath(pathString);
        return loadAsString(path, charset);
    }

    /**
     * Loads the content of a file specified by a {@link Path} object into a {@code String},
     * using the given {@link Charset} to decode the file content.
     * This is the core method for reading file content.
     * If an {@link IOException} occurs during reading, an error is logged to the console.
     *
     * @param path    The {@link Path} object representing the file.
     * @param charset The {@link Charset} to use for decoding the file content (e.g., {@code StandardCharsets.UTF_8}).
     * @return The content of the file as a {@code String}, or an empty string if an error occurs.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static String loadAsString(Path path, Charset charset) {
        try {
            return Files.readString(path, charset);
        } catch (IOException e) {
            Console.printError("Ran into IOException while reading file!", path.toString());
            return "";
        }
    }

    /**
     * Converts a string representation of a file path into a {@link Path} object.
     * This method provides a safe way to obtain a {@code Path} and handles potential
     * {@link InvalidPathException} by logging an error to the console.
     *
     * @param pathString The string representation of the file path.
     * @return A {@link Path} object corresponding to the given string, or {@code null} if the path string is invalid.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Path getPath(String pathString) {
        try {
            return Paths.get(pathString);
        } catch (InvalidPathException e) {
            Console.printError("Could not get path!", pathString);
            return null;
        }
    }
}