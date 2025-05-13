package de.jonas.main;

import de.jonas.engine.graphics.*;
import de.jonas.engine.io.Input;
import de.jonas.engine.io.Window;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.Camera;
import de.jonas.engine.objects.GameObject;
import de.jonas.engine.utils.Console;
import de.jonas.main.data.PV10;
import de.jonas.main.data.UserData;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable{
    public Thread game;
    public Window window;
    public Renderer renderer;
    public Shader shader;
    public final int WIDTH = 1280, HEIGHT = 760;


    public double tDELTA = 0;
    public double tLAST_UPDATE = 0;
    public double tACCUMULATOR = 0;
    public double tSLICE = 0.05;
    public double tNOW = 0;
    public int ticks = 0;
    public int TPS = 0;
    public long time = 0;

    public int gameTime = 0;

    public Mesh mesh = new Mesh(new Vertex[]{
            new Vertex(new Vector3f(-0.5f,0.5f,0.0f), PV10.DEFAULT_COLOR, new Vector2f(0.0f,0.0f)),
            new Vertex(new Vector3f(-0.5f,-0.5f,0.0f), PV10.DEFAULT_COLOR, new Vector2f(0.0f,1.0f)),
            new Vertex(new Vector3f(0.5f,-0.5f,0.0f), PV10.DEFAULT_COLOR, new Vector2f(1.0f,1.0f)),
            new Vertex(new Vector3f(0.5f,0.5f,0.0f), PV10.DEFAULT_COLOR, new Vector2f(1.0f,0.0f))
    }, new int[] {
            0,1,2,
            0,3,2
    }, new Material("/textures/testPic.png"));

    public GameObject object = new GameObject(new Vector3f(0,0,0),new Vector3f(0,0,0),new Vector3f(1,1,1),mesh);

    public Camera camera = new Camera(new Vector3f(0,0,1),new Vector3f(0,0,0));

    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void init() {
        Console.printDebug("Initializing values...",null);
        UserData.loadData();
        Console.printSucc("Values initialized!",null);
        Console.printDebug("Initializing game...",null);
        window = new Window(WIDTH, HEIGHT, PV10.GAME_NAME);
        shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
        renderer = new Renderer(window, shader);
        //window.setBackgroundColor(0, 0.5f, 1.0f);

        //bedingung ? wert_wenn_wahr : wert_wenn_falsch
        window.create(UserData.VSYNC ? 1 : 0);

        mesh.create();
        shader.create();

        Console.printSucc("Game initialized!",null);
    }

    public void run() {
        init();
        while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            tNOW = GLFW.glfwGetTime();
            tDELTA = tNOW - tLAST_UPDATE;
            tLAST_UPDATE += tDELTA;
            tACCUMULATOR += tDELTA;

            checkInput();
            while (tACCUMULATOR > tSLICE) {
                if (gameTime >= 24000) {gameTime = 0;}
                update();
                tACCUMULATOR -= tSLICE;
                gameTime += 1;
            }
            render();
            if (!UserData.VSYNC) {window.sync(tNOW,UserData.FPS);}
        }
        close();
    }

    private void update() {
        camera.update();

        calcTPS();
        GLFW.glfwSetWindowTitle(window.getWindow(),window.getTitle() + " | FPS: " + window.getCURRENT_FPS() + " | TPS: " + TPS);
    }

    private void checkInput() {
        if (Input.isKeyToggledUp(GLFW.GLFW_KEY_F11)) window.setIsFullscreen(!window.isIsFullscreen());
    }

    private void render() {
        window.update();
        renderer.renderGameObject(object, camera);
        window.swapBuffers();
    }

    private void calcTPS() {
        ticks++;
        if (System.currentTimeMillis() > time + 1000) {
            time = System.currentTimeMillis();
            TPS = ticks;
            ticks = 0;
        }
    }

    private void close() {
        window.destroy();
        mesh.destroy();
        shader.destroy();
    }

    public static void main(String[] args) {new Main().start();}
}