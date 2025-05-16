package de.jonas.engine.utils;

import de.jonas.engine.data.RunningData;

public class PerformanceUtils {
    private static int ticks = 0;
    private static int frames = 0;
    private static long TPSTime = 0;
    private static long FPSTime = 0;

    public static void updateTPS() {
        ticks++;
        if (System.currentTimeMillis() > TPSTime + 1000) {
            TPSTime = System.currentTimeMillis();
            RunningData.CURRENT_TPS = ticks;
            ticks = 0;
        }
    }

    public static void updateFPS() {
        //Increments the frames value by one!
        frames++;
        //Calls every second!
        if (System.currentTimeMillis() > FPSTime + 1000) {
            //Updates the Time var to the current time in milliseconds!
            FPSTime = System.currentTimeMillis();
            RunningData.CURRENT_FPS = frames;
            //Sets the Frames back to zero!
            frames = 0;
        }
    }
}
