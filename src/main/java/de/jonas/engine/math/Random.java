package de.jonas.engine.math;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unused") public class Random {
    /**
     * Generates a random int value given a certain range! It uses the ThreadLocalRandom util provided by java!
     * @param minVal The minimal value it can generate!
     * @param maxVal The maximum value it can generate!
     * @return Returns an int randomly generated given a certain range!
     */
    public static int randInt(int minVal, int maxVal) {
        return ThreadLocalRandom.current().nextInt(minVal, maxVal + 1);
    }

    /**
     * Generates a random float value given a certain range! It uses the ThreadLocalRandom util provided by java!
     * @param minVal The minimal value it can generate!
     * @param maxVal The maximum value it can generate!
     * @return Returns a float randomly generated given a certain range!
     */
    public static float randFloat(float minVal, float maxVal) {
        return ThreadLocalRandom.current().nextFloat() * (maxVal - minVal + Float.MIN_VALUE) + minVal;
    }

    /**
     *
     * @param pct The percentage used to get the output!
     * @return Given a percentage returns a boolean true if hit and false if no hit.
     */
    public static boolean chance(double pct) {
        BigDecimal randVal = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.0, 100.0));
        BigDecimal threshold = new BigDecimal(pct);
        return randVal.compareTo(threshold) <= 0;
    }
}
