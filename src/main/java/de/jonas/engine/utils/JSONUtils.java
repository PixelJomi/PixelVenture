package de.jonas.engine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONUtils {
    public static JSONObject getJSONFile(String path) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            Console.printError("Could not find file: " + path,e);
            return null;
        }
        Object object;
        try {
            object = new JSONParser().parse(fileReader);
        } catch (IOException | ParseException e) {
            Console.printError("Could not parse file: " + path,e);
            return null;
        }
        return (JSONObject) object;
    }

    public static Object getObjectFromJSON(JSONObject jsonObject,String jsonPath) {
        String[] jsonStrings = jsonPath.split("[.]");
        int jsonValueIndex = jsonStrings.length - 1;
        String jsonValueString = jsonStrings[jsonValueIndex];

        for (int i = 0;i < jsonValueIndex; i++) {
            if (jsonObject == null) {
                Console.printError("JSONObject resulted in null!",jsonPath);
                return null;
            } else {
                Object object = null;
                try {
                    object = jsonObject.get(jsonStrings[i]);
                } catch (Exception e) {
                    Console.printError("Could not get JSONObject!",jsonStrings[i]);
                    return null;
                }
                if (object == null) {
                    Console.printError("Could not get key (resulted in null): " + jsonStrings[i],jsonObject);
                    return null;
                } else {
                    try {
                        jsonObject = (JSONObject) object;
                    } catch (ClassCastException cCE) {
                        Console.printError("Could not cast object to JSONObject!",cCE);
                        return null;
                    }
                }
            }
        }
        if (jsonObject == null) {
            Console.printError("Could not get JSONObject (JSONObject given was null!)", jsonPath);
            return null;
        }
        Object result = null;
        try {
            result = jsonObject.get(jsonValueString);
        } catch (Exception e) {
            Console.printError("Could not get JSONObject!",jsonValueString);
            return null;
        }
        if (result == null) {
            Console.printError("Getting JSONObject resulted in null! JSONObjectName given: " + jsonValueString,jsonPath);
            return null;
        } else {
            Console.printDebug("Setting value \""+jsonValueString+"\" to:",result);
            return result;
        }
    }

    public static boolean objectToBoolean(Object object, boolean defaultValue) {
        if (object == null) {
            return defaultValue;
        } else {
            try {
                return (boolean) object;
            } catch (Exception e) {
                Console.printError("Error while casting object to int!",object);
                return defaultValue;
            }
        }
    }

    public static int objectToInteger(Object object, int defaultValue) {
        if (object == null) {
            return defaultValue;
        } else {
            try {
                return ((Long) object).intValue();
            } catch (Exception e) {
                Console.printError("Error while casting object to int (Long)!",object);
                return defaultValue;
            }
        }
    }

    public static long objectToLong(Object object, long defaultValue) {
        if (object == null) {
            return defaultValue;
        } else {
            try {
                return (long) object;
            } catch (Exception e) {
                Console.printError("Error while casting object to long!",object);
                return defaultValue;
            }
        }
    }

    public static String objectToString(Object object, String defaultValue) {
        if (object == null) {
            return defaultValue;
        } else {
            try {
                return (String) object;
            } catch (Exception e) {
                Console.printError("Error while casting object to String!",object);
                return defaultValue;
            }
        }
    }

    public static double objectToDouble(Object object, double defaultValue) {
        if (object == null) {
            return defaultValue;
        } else {
            try {
                return (double) object;
            } catch (Exception e) {
                Console.printError("Error while casting object to double!",object);
                return defaultValue;
            }
        }
    }

    public static JSONObject objectToJSONObject(Object object, JSONObject defaultValue) {
        if (object == null) {
            return defaultValue;
        } else {
            try {
                return (JSONObject) object;
            } catch (Exception e) {
                Console.printError("Error while casting object to JSONObject!",object);
                return defaultValue;
            }
        }
    }

    public static JSONArray objectToJSONObject(Object object, JSONArray defaultValue) {
        if (object == null) {
            return defaultValue;
        } else {
            try {
                return (JSONArray) object;
            } catch (Exception e) {
                Console.printError("Error while casting object to JSONArray!",object);
                return defaultValue;
            }
        }
    }

    public static float objectToFloat(Object object, float defaultValue) {
        if (object == null) {
            return defaultValue;
        } else {
            try {
                return ((Double) object).floatValue();
            } catch (Exception e) {
                Console.printError("Error while casting object to float (Long)!",object);
                return defaultValue;
            }
        }
    }

}