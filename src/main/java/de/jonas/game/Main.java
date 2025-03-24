package de.jonas.game;

import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.graphics.Renderer;
import de.jonas.engine.graphics.Vertex;
import de.jonas.engine.io.Input;
import de.jonas.engine.io.Window;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.utils.Console;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable{
    public Thread game;
    public Window window;
    public Renderer renderer;
    public final int WIDTH = 1280, HEIGHT = 760;

    public Mesh mesh = new Mesh(new Vertex[]{
            new Vertex(new Vector3f(-0.5f,0.5f,0.0f)),
            new Vertex(new Vector3f(0.5f,0.5f,0.0f)),
            new Vertex(new Vector3f(0.5f,-0.5f,0.0f)),
            new Vertex(new Vector3f(-0.5f,-0.5f,0.0f))
    }, new int[] {
            0, 1, 2,
            0, 3, 2
    });

    private int dayLightCycleCount = 0;
    @SuppressWarnings("FieldCanBeLocal") private int dayLightCycleColorOffset = 0;
    @SuppressWarnings("FieldCanBeLocal") private final int dayLightCycleColorRange = 220;

    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void init() {
        Console.printDebug("Initializing Game!",null);
        renderer = new Renderer();
        window = new Window(WIDTH, HEIGHT, "Game");
        window.setBackgroundColor(0, 0.5f, 1.0f);
        window.create();
        mesh.create();
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

        renderer.renderMesh(mesh);


        window.swapBuffers();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
