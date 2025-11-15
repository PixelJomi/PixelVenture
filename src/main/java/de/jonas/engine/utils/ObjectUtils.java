package de.jonas.engine.utils;

public class ObjectUtils {

    /**
     * Attempts to convert a given {@code Object} to a boolean value.
     *
     * <p>The conversion rules are as follows:
     * <ul>
     * <li>If the object is {@code null}, the {@code defaultValue} is returned.</li>
     * <li>If the object is an instance of {@link Boolean}, its boolean value is returned.</li>
     * <li>If the object is an instance of {@link String}:
     * <ul>
     * <li>"true" (case-insensitive) converts to {@code true}.</li>
     * <li>"false" (case-insensitive) converts to {@code false}.</li>
     * <li>Any other string returns the {@code defaultValue}.</li>
     * </ul>
     * </li>
     * <li>If the object is an instance of {@link Number}, it's converted to an int,
     * and if the int value is greater than 0, {@code true} is returned; otherwise, {@code false}.</li>
     * <li>For any other object type, an error is printed to the console using
     * {@link Console#printError(String, Object)}, and the {@code defaultValue} is returned.</li>
     * </ul>
     *
     * @param object       The {@code Object} to convert.
     * @param defaultValue The boolean value to return if the conversion is not possible or the object is null.
     * @return The converted boolean value, or the {@code defaultValue} if conversion fails.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static boolean objectToBoolean(Object object, boolean defaultValue) {
        if (object == null) return defaultValue;
        if (object instanceof Boolean aBoolean) return aBoolean;
        if (object instanceof String string) {
            String str = string.toLowerCase();
            return switch (str) {
                case "true" -> true;
                case "false" -> false;
                default -> defaultValue;
            };
        }
        if (object instanceof Number number) return number.intValue() > 0;
        Console.printError("Cannot convert to boolean: " + object, object);
        return defaultValue;
    }

    /**
     * Attempts to convert a given {@code Object} to an integer value.
     *
     * <p>The conversion rules are as follows:
     * <ul>
     * <li>If the object is {@code null}, the {@code defaultValue} is returned.</li>
     * <li>If the object is an instance of {@link Number}, its {@code int} value is returned.</li>
     * <li>If the object is an instance of {@link String}, it attempts to parse the string
     * as an integer using {@link Integer#parseInt(String)}. If parsing fails due to a
     * {@link NumberFormatException}, the {@code defaultValue} is returned.</li>
     * <li>For any other object type, an error is printed to the console using
     * {@link Console#printError(String, Object)}, and the {@code defaultValue} is returned.</li>
     * </ul>
     *
     * @param object       The {@code Object} to convert.
     * @param defaultValue The integer value to return if the conversion is not possible or the object is null.
     * @return The converted integer value, or the {@code defaultValue} if conversion fails.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static int objectToInteger(Object object, int defaultValue) {
        if (object == null) return defaultValue;
        if (object instanceof Number number) {return number.intValue();}
        if (object instanceof String string) {
            try {
                return Integer.parseInt(string);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        Console.printError("Cannot convert to int: " + object, object);
        return defaultValue;
    }

    /**
     * Attempts to convert a given {@code Object} to a float value.
     *
     * <p>The conversion rules are as follows:
     * <ul>
     * <li>If the object is {@code null}, the {@code defaultValue} is returned.</li>
     * <li>If the object is an instance of {@link Number}, its {@code float} value is returned.</li>
     * <li>If the object is an instance of {@link String}, it attempts to parse the string
     * as a float using {@link Float#parseFloat(String)}. If parsing fails due to a
     * {@link NumberFormatException}, the {@code defaultValue} is returned.</li>
     * <li>For any other object type, an error is printed to the console using
     * {@link Console#printError(String, Object)}, and the {@code defaultValue} is returned.</li>
     * </ul>
     *
     * @param object       The {@code Object} to convert.
     * @param defaultValue The float value to return if the conversion is not possible or the object is null.
     * @return The converted float value, or the {@code defaultValue} if conversion fails.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static float objectToFloat(Object object, float defaultValue) {
        if (object == null) return defaultValue;
        if (object instanceof Number number) {return number.floatValue();}
        if (object instanceof String string) {
            try {
                return Float.parseFloat(string);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        Console.printError("Cannot convert to float: " + object, object);
        return defaultValue;
    }

    /**
     * Attempts to convert a given {@code Object} to a long value.
     *
     * <p>The conversion rules are as follows:
     * <ul>
     * <li>If the object is {@code null}, the {@code defaultValue} is returned.</li>
     * <li>If the object is an instance of {@link Number}, its {@code long} value is returned.</li>
     * <li>If the object is an instance of {@link String}, it attempts to parse the string
     * as a long using {@link Long#parseLong(String)}. If parsing fails due to a
     * {@link NumberFormatException}, the {@code defaultValue} is returned.</li>
     * <li>For any other object type, an error is printed to the console using
     * {@link Console#printError(String, Object)}, and the {@code defaultValue} is returned.</li>
     * </ul>
     *
     * @param object       The {@code Object} to convert.
     * @param defaultValue The long value to return if the conversion is not possible or the object is null.
     * @return The converted long value, or the {@code defaultValue} if conversion fails.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static long objectToLong(Object object, long defaultValue) {
        if (object == null) return defaultValue;
        if (object instanceof Number number) {return number.longValue();}
        if (object instanceof String string) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        Console.printError("Cannot convert to long: " + object, object);
        return defaultValue;
    }

    /**
     * Attempts to convert a given {@code Object} to a double value.
     *
     * <p>The conversion rules are as follows:
     * <ul>
     * <li>If the object is {@code null}, the {@code defaultValue} is returned.</li>
     * <li>If the object is an instance of {@link Number}, its {@code double} value is returned.</li>
     * <li>If the object is an instance of {@link String}, it attempts to parse the string
     * as a double using {@link Double#parseDouble(String)}. If parsing fails due to a
     * {@link NumberFormatException}, the {@code defaultValue} is returned.</li>
     * <li>For any other object type, an error is printed to the console using
     * {@link Console#printError(String, Object)}, and the {@code defaultValue} is returned.</li>
     * </ul>
     *
     * @param object       The {@code Object} to convert.
     * @param defaultValue The double value to return if the conversion is not possible or the object is null.
     * @return The converted double value, or the {@code defaultValue} if conversion fails.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static double objectToDouble(Object object, double defaultValue) {
        if (object == null) return defaultValue;
        if (object instanceof Number number) {return number.doubleValue();}
        if (object instanceof String string) {
            try {
                return Double.parseDouble(string);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        Console.printError("Cannot convert to double: " + object, object);
        return defaultValue;
    }

    /**
     * Converts a given {@code Object} to its string representation.
     *
     * <p>The conversion rules are as follows:
     * <ul>
     * <li>If the object is {@code null}, the {@code defaultValue} is returned.</li>
     * <li>Otherwise, the object's {@link Object#toString()} method is called.
     * If the result of {@code toString()} is {@code null}, the {@code defaultValue} is returned.</li>
     * </ul>
     *
     * @param object       The {@code Object} to convert to a string.
     * @param defaultValue The string value to return if the object is null or its string representation is null.
     * @return The string representation of the object, or the {@code defaultValue} if the object or its string representation is null.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static String objectToString(Object object, String defaultValue) {
        if (object == null) return defaultValue;
        String str = object.toString();
        return (str != null) ? str : defaultValue;
    }
}