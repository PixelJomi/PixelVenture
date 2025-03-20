package de.jonas.game;

import de.jonas.engine.io.Input;
import de.jonas.engine.io.Window;
import de.jonas.engine.utils.Console;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable{
    public Thread game;
    public Window window;
    public final int WIDTH = 1280, HEIGHT = 760;

    private int dayLightCycleCount = 0;
    @SuppressWarnings("FieldCanBeLocal") private int dayLightCycleColorOffset = 0;
    @SuppressWarnings("FieldCanBeLocal") private final int dayLightCycleColorRange = 220;

    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void init() {
        Console.printDebug("Initializing Game!",null);
        window = new Window(WIDTH, HEIGHT, "Game");
        window.setBackgroundColor(0, 0.5f, 1.0f);
        window.create();
    }

    public void run() {
        init();
        while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            update();
            render();
            if (Input.isKeyToggledUp(GLFW.GLFW_KEY_F11)) window.setIsFullscreen(!window.isIsFullscreen());
        }
        window.destroy();
    }

    private void update() {
        window.update();
        if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) System.out.println("X: " + Input.getMouseX() + ", Y: " + Input.getMouseY() + " SCROLLX: " + Input.getScrollX() + " SCROLLY: " + Input.getScrollY());
    }

    private void render() {
        dayLightCycleCount++;
        int dayCount = dayLightCycleCount / 4;
        if (dayCount > (dayLightCycleColorRange * 2)) {
            dayLightCycleCount = 0;
        }
        if (dayCount > dayLightCycleColorRange) {
            dayLightCycleColorOffset = (dayCount - (dayCount - dayLightCycleColorRange) * 2);
        } else {
            dayLightCycleColorOffset = dayCount;
        }
        window.setBackgroundColor(0, 0.5f - dayLightCycleColorOffset / 255.0f, 1.0f - dayLightCycleColorOffset / 255.0f);

        window.swapBuffers();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
