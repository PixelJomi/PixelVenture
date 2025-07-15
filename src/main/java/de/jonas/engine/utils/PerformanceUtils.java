package de.jonas.engine.utils;

import de.jonas.engine.data.DynamicData;

/**
 * A utility class for tracking and managing engine performance metrics,
 * specifically Ticks Per Second (TPS) and Frames Per Second (FPS).
 * This class provides methods to update these counters, which are then
 * exposed via the {@link DynamicData} class.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
public class PerformanceUtils {
    /**
     * Counter for the number of ticks processed within the current second.
     * This value is reset every second.
     */
    private static int ticks = 0;
    /**
     * Counter for the number of frames rendered within the current second.
     * This value is reset every second.
     */
    private static int frames = 0;
    /**
     * The timestamp (in milliseconds) when the TPS counter was last reset or updated.
     * Used to determine when a second has passed for TPS calculation.
     */
    private static long TPSTime = 0;
    /**
     * The timestamp (in milliseconds) when the FPS counter was last reset or updated.
     * Used to determine when a second has passed for FPS calculation.
     */
    private static long FPSTime = 0;

    /**
     * Increments the internal tick counter. Every second, this method calculates
     * the current Ticks Per Second (TPS) and stores it in {@link DynamicData#CURRENT_TPS},
     * then resets the tick counter.
     * This method should be called once per game tick.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static void updateTPS() {
        ticks++;
        if (System.currentTimeMillis() > TPSTime + 1000) {
            TPSTime = System.currentTimeMillis();
            DynamicData.CURRENT_TPS = ticks;
            ticks = 0;
        }
    }

    /**
     * Increments the internal frame counter. Every second, this method calculates
     * the current Frames Per Second (FPS) and stores it in {@link DynamicData#CURRENT_FPS},
     * then resets the frame counter.
     * This method should be called once per frame rendered.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static void updateFPS() {
        // Increments the frames value by one!
        frames++;
        // Calls every second!
        if (System.currentTimeMillis() > FPSTime + 1000) {
            // Updates the Time var to the current time in milliseconds!
            FPSTime = System.currentTimeMillis();
            DynamicData.CURRENT_FPS = frames;
            // Sets the Frames back to zero!
            frames = 0;
        }
    }
}