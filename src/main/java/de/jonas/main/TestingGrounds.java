package de.jonas.main;

import de.jonas.engine.data.UserData;
import de.jonas.engine.math.QGC;
import de.jonas.engine.math.Random;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.objects.world.Scene;
import de.jonas.engine.utils.Console;

public class TestingGrounds {
    public static int amount = 0;
    public static void main(String[] args) {

        UserData.loadData();
        Scene scene = new Scene();
        Console.printDebug("Amount: ",amount);
        System.out.println(scene.getChunkIndex(new Vector2f(1,-1)));
    }

    public static void add() {
        amount++;
    }
}