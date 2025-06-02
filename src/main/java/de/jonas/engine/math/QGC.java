package de.jonas.engine.math;

public class QGC {
    private long currentSeed; // Xn
    private long d;           // quadratic coefficient
    private long a;           // linear coefficient
    private long c;           // increment
    private long m;           // modulus
    private long minValue;    // Minimum value

    /**
     * Constructor for the Quadratic Congruential Generator.
     * @param seed The starting point number used to further calculate all other ones.
     * @param d The higher "d" the more it cares about the previous number times squared. "d * pow(previous number, 2)"
     * @param a The higher "a" the more the next number cares about the previous number directly (in a linear way). "a * previous number"
     * @param c It increases the step size of the program. For example the sequence would be 32891 and with "c" set to 2 it`s 381...
     * @param maxValue The max value it can generate (inclusive).
     */
    public QGC(long seed, long d, long a, long c, long maxValue) {
        this.currentSeed = seed;
        this.d = d;
        this.a = a;
        this.c = c;
        this.m = maxValue + 1;
        this.minValue = 0;
    }

    /**
     * Constructor for the Quadratic Congruential Generator.
     * @param seed The starting point number used to further calculate all other ones.
     * @param d The higher "d" the more it cares about the previous number times squared. "d * pow(previous number, 2)"
     * @param a The higher "a" the more the next number cares about the previous number directly (in a linear way). "a * previous number"
     * @param c It increases the step size of the program. For example the sequence would be 32891 and with "c" set to 2 it`s 381...
     * @param maxValue The max value it can generate (inclusive).
     * @param minValue The min value it can generate (inclusive).
     */
    public QGC(long seed, long d, long a, long c, long maxValue, long minValue) {
        this.currentSeed = seed;
        this.d = d;
        this.a = a;
        this.c = c;
        this.m = (maxValue + 1) - minValue;
        this.minValue = minValue;
    }

    /**
     * Generates the next pseudorandom number in the sequence.
     * @return The next pseudorandom number (long).
     */
    public long next() {
        // Calculate (d * Xn^2 + a * Xn + c) mod m
        // Use long for intermediate calculations to prevent overflow before the modulo operation,
        // especially if m is large.
        long nextValue = ((d * currentSeed * currentSeed + a * currentSeed + c) % m) + minValue;
        // Ensure the result is non-negative, as Java's % operator can return negative for negative dividends.
        if (nextValue < 0) {
            nextValue += m;
        }
        this.currentSeed = nextValue;
        return nextValue;
    }

    /**
     * Resets the generator to a new seed.
     * @param newSeed The new seed value.
     * */
    public void setSeed(long newSeed) {
        this.currentSeed = newSeed;
    }
}
