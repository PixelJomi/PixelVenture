package de.jonas.game;

import de.jonas.engine.io.math.Vector3f;
import de.jonas.engine.utils.Console;

public class Test {
    public static void main(String[] args) {

        Vector3f data = new Vector3f(32f,2f,1f);
        Vector3f dataTwo = new Vector3f(2f);

        Console.printDebug(data.toString(),false);

        data.dev(dataTwo);

        Console.printDebug(data.toString(),false);
    }



}
