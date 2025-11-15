package de.jonas.engine.utils;

import java.util.Scanner;

import de.jonas.engine.data.UserData;

/**
 * A utility class for enhanced console output and input, providing formatted, colored
 * log messages and controlled user input. This class leverages ANSI escape codes for
 * rich text formatting and integrates with a debug flag to control output verbosity.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
public class Console {
    public static final String WARN_ICON = " WARN ";
    public static final String ERROR_ICON = " ERRR ";
    public static final String DEBUG_ICON = " DEBG ";
    public static final String SUCCESS_ICON = " SUCC ";
    public static final String FATAL_ICON = " " + ColorUtils.consoleColor.RED_UNDERLINED + "FATL" + ColorUtils.consoleColor.RESET + " ";
    public static final String ENCLOSURE_ICON_FRONT = ColorUtils.consoleColor.WHITE_BRIGHT + "[" + ColorUtils.consoleColor.RESET;
    public static final String ENCLOSURE_ICON_BACK = ColorUtils.consoleColor.WHITE_BRIGHT + "]" + ColorUtils.consoleColor.RESET;
    public static final String DATA_ENCLOSURE_FRONT = ColorUtils.consoleColor.WHITE_BRIGHT + "{" + ColorUtils.consoleColor.RESET;
    public static final String DATA_ENCLOSURE_BACK = ColorUtils.consoleColor.WHITE_BRIGHT + "}" + ColorUtils.consoleColor.RESET;
    public static final String TEXT_DIVIDER_ICON = ColorUtils.consoleColor.WHITE_BRIGHT +  " | " + ColorUtils.consoleColor.RESET;

    public static void print(Object val) {
        if (UserData.DEBUG) {
            System.out.print(val + ColorUtils.consoleColor.RESET);
        }
    }

    public static void println(Object val) {
        if (UserData.DEBUG) {
            System.out.println(val + ColorUtils.consoleColor.RESET);
        }
    }

    public static void printWarn(String msg, Object data) {
        println(ENCLOSURE_ICON_FRONT + ColorUtils.consoleColor.YELLOW_BOLD_BRIGHT + WARN_ICON + ColorUtils.consoleColor.RESET + ENCLOSURE_ICON_BACK + TEXT_DIVIDER_ICON + msg + " " + DATA_ENCLOSURE_FRONT + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + DATA_ENCLOSURE_BACK);
    }

    public static void printError(String msg, Object data) {
        println(ENCLOSURE_ICON_FRONT + ColorUtils.consoleColor.RED_BOLD_BRIGHT + ERROR_ICON + ColorUtils.consoleColor.RESET + ENCLOSURE_ICON_BACK + TEXT_DIVIDER_ICON + msg + " " + DATA_ENCLOSURE_FRONT + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + DATA_ENCLOSURE_BACK);
    }

    public static void printDebug(String msg, Object data) {
        println(ENCLOSURE_ICON_FRONT + ColorUtils.consoleColor.BLUE_BOLD_BRIGHT + DEBUG_ICON + ColorUtils.consoleColor.RESET + ENCLOSURE_ICON_BACK + TEXT_DIVIDER_ICON + msg + " " + DATA_ENCLOSURE_FRONT + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + DATA_ENCLOSURE_BACK);
    }

    public static void printSucc(String msg, Object data) {
        println(ENCLOSURE_ICON_FRONT + ColorUtils.consoleColor.GREEN_BOLD_BRIGHT + SUCCESS_ICON + ColorUtils.consoleColor.RESET + ENCLOSURE_ICON_BACK + TEXT_DIVIDER_ICON + msg + " " + DATA_ENCLOSURE_FRONT + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + DATA_ENCLOSURE_BACK);
    }

    public static void printFatal(String msg, Object data) {
        println(ENCLOSURE_ICON_FRONT + ColorUtils.consoleColor.RED_BOLD_BRIGHT + FATAL_ICON + ENCLOSURE_ICON_BACK + TEXT_DIVIDER_ICON + msg + " " + DATA_ENCLOSURE_FRONT + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + DATA_ENCLOSURE_BACK);
    }

    public static String input(Object val) {
        if (UserData.DEBUG) {
            print(val + ColorUtils.consoleColor.RESET); // Use print to keep cursor on same line
            try (Scanner scanner = new Scanner(System.in)) {
                String input = scanner.next();
                scanner.close();
                return input;
            } catch (Exception e) {
                Console.printError("Input failed!", val);
                return null;
            }
        } else {
            return null;
        }
    }
}