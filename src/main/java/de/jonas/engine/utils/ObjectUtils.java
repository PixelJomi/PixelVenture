package de.jonas.engine.utils;

public class ObjectUtils {

    public static boolean objectToBoolean(Object object, boolean defaultValue) {
        if (object == null) return defaultValue;
        if (object instanceof Boolean) return (Boolean) object;
        if (object instanceof String) {
            String str = ((String) object).toLowerCase();
            if (str.equals("true")) {
                return true;
            } else if (str.equals("false")) {
                return false;
            } else {
                return defaultValue;
            }
        }
        if (object instanceof Number) return ((Number) object).intValue() > 0;
        Console.printError("Cannot convert to boolean: " + object, object);
        return defaultValue;
    }

    public static int objectToInteger(Object object, int defaultValue) {
        if (object == null) return defaultValue;
        if (object instanceof Number) {
            return ((Number) object).intValue();
        }
        if (object instanceof String) {
            try {
                return Integer.parseInt((String) object);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        Console.printError("Cannot convert to int: " + object, object);
        return defaultValue;
    }

    public static float objectToFloat(Object object, float defaultValue) {
        if (object == null) return defaultValue;
        if (object instanceof Number) {
            return ((Number) object).floatValue();
        }
        if (object instanceof String) {
            try {
                return Float.parseFloat((String) object);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        Console.printError("Cannot convert to float: " + object, object);
        return defaultValue;
    }

    public static long objectToLong(Object object, long defaultValue) {
        if (object == null) return defaultValue;
        if (object instanceof Number) {
            return ((Number) object).longValue();
        }
        if (object instanceof String) {
            try {
                return Long.parseLong((String) object);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        Console.printError("Cannot convert to long: " + object, object);
        return defaultValue;
    }

    public static double objectToDouble(Object object, double defaultValue) {
        if (object == null) return defaultValue;
        if (object instanceof Number) {
            return ((Number) object).doubleValue();
        }
        if (object instanceof String) {
            try {
                return Double.parseDouble((String) object);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        Console.printError("Cannot convert to double: " + object, object);
        return defaultValue;
    }

    public static String objectToString(Object object, String defaultValue) {
        if (object == null) return defaultValue;
        String str = object.toString();
        return (str != null) ? str : defaultValue;
    }
}
