package de.jonas.main;

import de.jonas.engine.data.blocks.Default;
import de.jonas.engine.graphics.*;
import de.jonas.engine.io.Input;
import de.jonas.engine.io.Window;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.game.player.Camera;
import de.jonas.engine.objects.game.GameObject;
import de.jonas.engine.objects.game.player.Player;
import de.jonas.engine.objects.world.ChunkSection;
import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.PerformanceUtils;
import de.jonas.engine.data.PVData;
import de.jonas.engine.data.RunningData;
import de.jonas.engine.data.UserData;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable{
    public Thread game;
    public Window window;
    public Renderer renderer;
    public Shader shader;

    //Variables for TPS!
    public double tDELTA = 0;
    public double tLAST_UPDATE = 0;
    public double tACCUMULATOR = 0;
    public double tSLICE = 0.05;
    public double tNOW = 0;

    //TODO Make faces render individually and add "Block" as gameObject


    public Mesh mesh = new ChunkSection((short) 16).generateMesh(new Vector2f(0f,0f));

    public GameObject object = new GameObject(new Vector3f(0,0,0),new Vector3f(0,0,0),new Vector3f(1,1,1),mesh);

    public Player player = new Player(new Vector3f(0, 0, 1),new Vector3f(0,0,0));

    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void init() {
        UserData.loadData();
        Console.printDebug("Initializing game...",null);
        window = new Window(UserData.START_WIDTH, UserData.START_HEIGHT, PVData.GAME_NAME);
        shader = new Shader("shader/mainVertex.glsl", "shader/mainFragment.glsl");
        renderer = new Renderer(window, shader);

        //bedingung ? wert_wenn_wahr : wert_wenn_falsch
        window.create(UserData.VSYNC ? 1 : 0);

        mesh.create();
        shader.create();

        //TODO Remove once main menu is in place!
        window.mouseState(true);
        Console.printSucc("Game initialized!",null);
    }

    public void run() {

        //TODO Add a proper chat setting

        init();
        while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            tNOW = GLFW.glfwGetTime();
            tDELTA = tNOW - tLAST_UPDATE;
            tLAST_UPDATE += tDELTA;
            tACCUMULATOR += tDELTA;

            checkInput();

            while (tACCUMULATOR > tSLICE) {
                if (RunningData.GAME_TIME >= 24000) {RunningData.GAME_TIME = 0;}

                update();

                tACCUMULATOR -= tSLICE;
                RunningData.GAME_TIME += 1;
            }

            //TODO Update Player movement to be consistent.
            player.update();
            render();

            if (!UserData.VSYNC && UserData.FPS > 0) {window.sync(tNOW,UserData.FPS);}
        }
        close();
    }

    private void update() {
        PerformanceUtils.updateTPS();
        GLFW.glfwSetWindowTitle(window.getWindow(),window.getTitle() + " | FPS: " + RunningData.CURRENT_FPS + " | TPS: " + RunningData.CURRENT_TPS);
    }

    private void checkInput() {
        if (Input.isKeyToggledUp(GLFW.GLFW_KEY_F11)) window.setIsFullscreen(!window.isIsFullscreen());
        if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) window.mouseState(true);
    }

    private void render() {
        PerformanceUtils.updateFPS();
        window.update();

        renderer.renderGameObject(object, player.getCamera());

        window.swapBuffers();
    }

    private void close() {
        window.destroy();
        mesh.destroy();
        shader.destroy();
    }

    public static void main(String[] args) {new Main().start();}

}