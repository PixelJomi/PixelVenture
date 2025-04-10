package de.jonas.engine.utils;

import de.jonas.engine.math.Vector3f;

public class ColorUtils {
    public static Vector3f format01(int r, int g, int b) {
        return new Vector3f(r / 255f,g / 255f,b / 255f);
    }
    public static Vector3f format01(Vector3f color) {
        return new Vector3f(color.getX() / 255f,color.getY() / 255f,color.getZ() / 255f);
    }
    public static Vector3f format255(int r, int g, int b) {
        return new Vector3f(r * 255f,g * 255f,b * 255f);
    }
    public static Vector3f format255(Vector3f color) {
        return new Vector3f(color.getX() * 255f, color.getY() * 255f,color.getZ() * 255f);
    }
}

