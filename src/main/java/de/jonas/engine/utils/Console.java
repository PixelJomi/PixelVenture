package de.jonas.engine.utils;

import de.jonas.engine.data.UserData;

import java.util.Scanner;

/**
 * A utility class for enhanced console output and input, providing formatted, colored
 * log messages and controlled user input. This class leverages ANSI escape codes for
 * rich text formatting and integrates with a debug flag to control output verbosity.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
@SuppressWarnings("unused")
public class Console {
    /**
     * Icon for warning messages, typically displayed as " WARN ".
     */
    public static final String warnIcon               = " WARN ";
    /**
     * Icon for error messages, typically displayed as " ERRR ".
     */
    public static final String errorIcon              = " ERRR ";
    /**
     * Icon for debug messages, typically displayed as " DEBG ".
     */
    public static final String debugIcon              = " DEBG ";
    /**
     * Icon for success messages, typically displayed as " SUCC ".
     */
    public static final String successIcon            = " SUCC ";
    /**
     * Icon for fatal error messages, formatted with underlined red text for emphasis.
     */
    public static final String fatalIcon              =   " " +     ColorUtils.consoleColor.RED_UNDERLINED    + "FATL"   + ColorUtils.consoleColor.RESET    + " ";
    /**
     * The opening bracket icon for console messages, formatted in bright white.
     */
    public static final String enclosureIconFront     =             ColorUtils.consoleColor.WHITE_BRIGHT      + "["      + ColorUtils.consoleColor.RESET;
    /**
     * The closing bracket icon for console messages, formatted in bright white.
     */
    public static final String enclosureIconBack      =             ColorUtils.consoleColor.WHITE_BRIGHT      + "]"      + ColorUtils.consoleColor.RESET;
    /**
     * The opening curly brace icon for data displayed in console messages, formatted in bright white.
     */
    public static final String dataEnclosureIconFront =             ColorUtils.consoleColor.WHITE_BRIGHT      + "{"      + ColorUtils.consoleColor.RESET;
    /**
     * The closing curly brace icon for data displayed in console messages, formatted in bright white.
     */
    public static final String dataEnclosureIconBack  =             ColorUtils.consoleColor.WHITE_BRIGHT      + "}"      + ColorUtils.consoleColor.RESET;
    /**
     * A vertical bar icon used as a divider in console messages, formatted in bright white.
     */
    public static final String textDividerIcon        =             ColorUtils.consoleColor.WHITE_BRIGHT      +  " | "   + ColorUtils.consoleColor.RESET;

    /**
     * Prints an object to the console without a new line, but only if {@code UserData.DEBUG} is enabled.
     * The output will be reset to default console colors after the value.
     *
     * @param val The object to be printed. Its {@code toString()} method will be called.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static void print(Object val) {
        if (UserData.DEBUG) {
            System.out.print(val + ColorUtils.consoleColor.RESET);
        }
    }

    /**
     * Prints an object to the console followed by a new line, but only if {@code UserData.DEBUG} is enabled.
     * The output will be reset to default console colors after the value.
     *
     * @param val The object to be printed. Its {@code toString()} method will be called.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static void println(Object val) {
        if (UserData.DEBUG) {
            System.out.println(val + ColorUtils.consoleColor.RESET);
        }
    }

    /**
     * Prints a formatted warning message to the console.
     * The message includes a "WARN" icon, the provided message, and associated data,
     * all styled with specific console colors (e.g., yellow for warning icon, blue for data).
     * This method respects the {@code UserData.DEBUG} flag.
     *
     * @param msg  The primary warning message string.
     * @param data An object containing additional data relevant to the warning,
     * which will be displayed in curly braces.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static void printWarn(String msg, Object data) {
        println(enclosureIconFront + ColorUtils.consoleColor.YELLOW_BOLD_BRIGHT + warnIcon + ColorUtils.consoleColor.RESET + enclosureIconBack + textDividerIcon + msg + " " + dataEnclosureIconFront + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + dataEnclosureIconBack);
    }

    /**
     * Prints a formatted error message to the console.
     * The message includes an "ERRR" icon, the provided message, and associated data,
     * all styled with specific console colors (e.g., red for error icon, blue for data).
     * This method respects the {@code UserData.DEBUG} flag.
     *
     * @param msg  The primary error message string.
     * @param data An object containing additional data relevant to the error,
     * which will be displayed in curly braces.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static void printError(String msg, Object data) {
        println(enclosureIconFront + ColorUtils.consoleColor.RED_BOLD_BRIGHT + errorIcon + ColorUtils.consoleColor.RESET + enclosureIconBack + textDividerIcon + msg + " " + dataEnclosureIconFront + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + dataEnclosureIconBack);
    }

    /**
     * Prints a formatted debug message to the console.
     * The message includes a "DEBG" icon, the provided message, and associated data,
     * all styled with specific console colors (e.g., blue for debug icon and data).
     * This method respects the {@code UserData.DEBUG} flag.
     *
     * @param msg  The primary debug message string.
     * @param data An object containing additional data relevant to the debug information,
     * which will be displayed in curly braces.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static void printDebug(String msg, Object data) {
        println(enclosureIconFront + ColorUtils.consoleColor.BLUE_BOLD_BRIGHT + debugIcon + ColorUtils.consoleColor.RESET + enclosureIconBack + textDividerIcon + msg + " " + dataEnclosureIconFront + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + dataEnclosureIconBack);
    }

    /**
     * Prints a formatted success message to the console.
     * The message includes a "SUCC" icon, the provided message, and associated data,
     * all styled with specific console colors (e.g., green for success icon, blue for data).
     * This method respects the {@code UserData.DEBUG} flag.
     *
     * @param msg  The primary success message string.
     * @param data An object containing additional data relevant to the success,
     * which will be displayed in curly braces.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static void printSucc(String msg, Object data) {
        println(enclosureIconFront + ColorUtils.consoleColor.GREEN_BOLD_BRIGHT + successIcon + ColorUtils.consoleColor.RESET + enclosureIconBack + textDividerIcon + msg + " " + dataEnclosureIconFront + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + dataEnclosureIconBack);
    }

    /**
     * Prints a formatted fatal error message to the console.
     * The message includes a "FATL" icon (underlined red), the provided message, and associated data,
     * with the error icon and message styled prominently in red.
     * This method respects the {@code UserData.DEBUG} flag.
     *
     * @param msg  The primary fatal error message string.
     * @param data An object containing additional data relevant to the fatal error,
     * which will be displayed in curly braces.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static void printFatal(String msg, Object data) {
        println(enclosureIconFront + ColorUtils.consoleColor.RED_BOLD_BRIGHT + fatalIcon + enclosureIconBack + textDividerIcon + msg + " " + dataEnclosureIconFront + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + dataEnclosureIconBack);
    }

    /**
     * Prompts the user for input by printing a message and then reads the next token from the console.
     * This method only operates if {@code UserData.DEBUG} is enabled.
     * If debugging is disabled, it returns {@code null}.
     *
     * @param val The message or prompt to display to the user before awaiting input.
     * @return The next token (word) entered by the user, or {@code null} if debugging is disabled.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static String input(Object val) {
        if (UserData.DEBUG) {
            print(val + ColorUtils.consoleColor.RESET); // Use print to keep cursor on same line
            Scanner scanner = new Scanner(System.in);
            return scanner.next();
        } else {
            return null;
        }
    }
}