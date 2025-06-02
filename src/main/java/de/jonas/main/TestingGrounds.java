package de.jonas.main;

import de.jonas.engine.data.UserData;
import de.jonas.engine.math.QGC;
import de.jonas.engine.math.Random;
import de.jonas.engine.utils.Console;

public class TestingGrounds {
    public static void main(String[] args) {
        UserData.loadData();
            // Example usage with arbitrary parameters
            // Note: Choosing good parameters for QCGs is complex and crucial for randomness quality.
            // These are just illustrative values.

        long initialSeed = (long) Random.randInt(0,2147483646);;
        long d_coeff = 4;
        long a_coeff = 5;
        long c_coeff = 1;
        long maxValue = 2000; // Small modulus for demonstration

        QGC qcg = new QGC(initialSeed, d_coeff, a_coeff, c_coeff, maxValue);

        System.out.println("Generating numbers using QCG:");
        long maxResult = 0L;
        for (int i = 0; i < 50000; i++) {
            long result = qcg.next();
            if (result > maxResult) {
                Console.printDebug("New biggest number found on step: " + i,result);
                maxResult = result;
            }
        }
    }
}