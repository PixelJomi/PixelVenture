package de.jonas.engine.utils;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unused") public class Random {
    public static int randInt(int minVal, int maxVal) {
        return ThreadLocalRandom.current().nextInt(minVal, maxVal + 1);
    }
    public static float randFloat(float min, float max) {
        return ThreadLocalRandom.current().nextFloat() * (max - min + Float.MIN_VALUE) + min;
    }
    public static boolean chance(double pct) {
        BigDecimal randVal = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.0, 100.0));
        BigDecimal threshold = new BigDecimal(pct);
        return randVal.compareTo(threshold) <= 0;
    }
}
