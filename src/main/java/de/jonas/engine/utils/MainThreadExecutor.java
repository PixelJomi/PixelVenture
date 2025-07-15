package de.jonas.engine.utils;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MainThreadExecutor {
    private static final ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

    public static void runLater(Runnable task) {
        queue.add(task);
    }

    public static void executeAll() {
        while (!queue.isEmpty()) {
            queue.poll().run();
        }
    }
}

