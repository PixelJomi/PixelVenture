package de.jonas.engine.utils;

import de.jonas.engine.data.UserData;

import java.util.Scanner;

@SuppressWarnings("unused")  public class Console {
    public static final String warnIcon               = " WARN ";
    public static final String errorIcon              = " ERRR ";
    public static final String debugIcon              = " DEBG ";
    public static final String successIcon            = " SUCC ";
    public static final String fatalIcon              =   " " +     ColorUtils.consoleColor.RED_UNDERLINED    + "FATL"   + ColorUtils.consoleColor.RESET    + " ";
    public static final String enclosureIconFront     =             ColorUtils.consoleColor.WHITE_BRIGHT      + "["      + ColorUtils.consoleColor.RESET;
    public static final String enclosureIconBack      =             ColorUtils.consoleColor.WHITE_BRIGHT      + "]"      + ColorUtils.consoleColor.RESET;
    public static final String dataEnclosureIconFront =             ColorUtils.consoleColor.WHITE_BRIGHT      + "{"      + ColorUtils.consoleColor.RESET;
    public static final String dataEnclosureIconBack  =             ColorUtils.consoleColor.WHITE_BRIGHT      + "}"      + ColorUtils.consoleColor.RESET;
    public static final String textDividerIcon        =             ColorUtils.consoleColor.WHITE_BRIGHT      +  " | "   + ColorUtils.consoleColor.RESET;

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
        println(enclosureIconFront + ColorUtils.consoleColor.YELLOW_BOLD_BRIGHT + warnIcon + ColorUtils.consoleColor.RESET + enclosureIconBack + textDividerIcon + msg + " " + dataEnclosureIconFront + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + dataEnclosureIconBack);

    }
    public static void printError(String msg, Object data) {
        println(enclosureIconFront + ColorUtils.consoleColor.RED_BOLD_BRIGHT + errorIcon + ColorUtils.consoleColor.RESET + enclosureIconBack + textDividerIcon + msg + " " + dataEnclosureIconFront + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + dataEnclosureIconBack);
    }
    public static void printDebug(String msg, Object data) {
        println(enclosureIconFront + ColorUtils.consoleColor.BLUE_BOLD_BRIGHT + debugIcon + ColorUtils.consoleColor.RESET + enclosureIconBack + textDividerIcon + msg + " " + dataEnclosureIconFront + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + dataEnclosureIconBack);
    }
    public static void printSucc(String msg, Object data) {
        println(enclosureIconFront + ColorUtils.consoleColor.GREEN_BOLD_BRIGHT + successIcon + ColorUtils.consoleColor.RESET + enclosureIconBack + textDividerIcon + msg + " " + dataEnclosureIconFront + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + dataEnclosureIconBack);

    }
    public static void printFatal(String msg, Object data) {
        println(enclosureIconFront + ColorUtils.consoleColor.RED_BOLD_BRIGHT + fatalIcon + enclosureIconBack + textDividerIcon + msg + " " + dataEnclosureIconFront + ColorUtils.consoleColor.BLUE + data + ColorUtils.consoleColor.RESET + dataEnclosureIconBack);
    }
    public static String input(Object val) {
        if (UserData.DEBUG) {
            print(val + ColorUtils.consoleColor.RESET);
            Scanner scanner = new Scanner(System.in);
            return scanner.next();
        } else {
            return null;
        }
    }
}