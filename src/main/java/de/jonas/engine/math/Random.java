package de.jonas.engine.math;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A utility class for generating various types of random numbers and probabilities.
 * This class leverages Java's {@link java.util.concurrent.ThreadLocalRandom} for efficient
 * and thread-safe random number generation.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
@SuppressWarnings("unused")
public class Random {
    /**
     * Generates a random integer within a specified inclusive range.
     * This method uses {@link ThreadLocalRandom} for performance and thread safety.
     *
     * @param minVal The minimum inclusive value that can be generated.
     * @param maxVal The maximum inclusive value that can be generated.
     * @return A randomly generated integer within the given range (inclusive of both `minVal` and `maxVal`).
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static int randInt(int minVal, int maxVal) {
        return ThreadLocalRandom.current().nextInt(minVal, maxVal + 1);
    }

    /**
     * Generates a random float value within a specified inclusive range.
     * This method uses {@link ThreadLocalRandom} for performance and thread safety.
     * The `Float.MIN_VALUE` is added to ensure the `maxVal` is potentially reachable in floating-point math.
     *
     * @param minVal The minimum inclusive value that can be generated.
     * @param maxVal The maximum inclusive value that can be generated.
     * @return A randomly generated float within the given range (inclusive of `minVal` and up to `maxVal`).
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static float randFloat(float minVal, float maxVal) {
        return ThreadLocalRandom.current().nextFloat() * (maxVal - minVal + Float.MIN_VALUE) + minVal;
    }

    /**
     * Determines if a random event occurs based on a given percentage chance.
     * For example, a `pct` of 75.0 means there's a 75% chance this method returns `true`.
     * It uses {@link ThreadLocalRandom} for generation and {@link BigDecimal} for precise comparison
     * with the percentage threshold.
     *
     * @param pct The percentage (0.0 to 100.0) representing the chance of returning `true`.
     * @return `true` if the randomly generated value falls within the specified percentage, `false` otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static boolean chance(double pct) {
        BigDecimal randVal = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.0, 100.0));
        BigDecimal threshold = new BigDecimal(pct);
        return randVal.compareTo(threshold) <= 0;
    }
}