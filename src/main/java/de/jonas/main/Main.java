package de.jonas.main;

import de.jonas.engine.data.JSONData;
import de.jonas.engine.graphics.*;
import de.jonas.engine.io.Input;
import de.jonas.engine.io.Window;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.Scene;
import de.jonas.engine.objects.world.World;
import de.jonas.engine.ui.debug.DebugUI;
import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.MainThreadExecutor;
import de.jonas.engine.utils.PerformanceUtils;
import de.jonas.engine.data.StaticData;
import de.jonas.engine.data.DynamicData;
import de.jonas.engine.data.UserData;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import static org.lwjgl.nuklear.Nuklear.*;

public class Main implements Runnable{
    public Thread game;
    public Window window;
    public Shader shader;

    public Scene scene;

    private final DebugUI debugUI = new DebugUI();

    private static final int MAX_VERTEX_BUFFER  = 512 * 1024;
    private static final int MAX_ELEMENT_BUFFER = 128 * 1024;

    //Variables for TPS!
    public double tDELTA = 0;
    public double tLAST_UPDATE = 0;
    public double tACCUMULATOR = 0;
    public double tSLICE = 0.05;
    public double tNOW = 0;

    public World world;

    public static void main(String[] args) {
        new Main().start();
    }
    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void run() {
        GLFWErrorCallback.createPrint().set();

        //TODO Add a proper chat setting

        init();
        while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            tNOW = GLFW.glfwGetTime();
            tDELTA = tNOW - tLAST_UPDATE;
            tLAST_UPDATE += tDELTA;
            tACCUMULATOR += tDELTA;
            checkInput();
            while (tACCUMULATOR > tSLICE) {
                if (DynamicData.GAME_TIME >= 24000) {
                    DynamicData.GAME_TIME = 0;}
                update();
                tACCUMULATOR -= tSLICE;
                DynamicData.GAME_TIME += 1;
            }
            //TODO Update Player movement to be consistent.
            scene.update();
            render();
            if (!UserData.VSYNC && UserData.FPS > 0) {window.sync(tNOW,UserData.FPS);}
        }
        close();
    }

    public void init() {
        UserData.loadData(new JSONData());
        Console.printDebug("Initializing game...",null);
        window = new Window(UserData.START_WIDTH, UserData.START_HEIGHT, StaticData.GAME_NAME);
        shader = new Shader("shader/mainVertex.glsl", "shader/mainFragment.glsl");

        window.create(UserData.VSYNC);
        shader.create();

        scene = new Scene(new Vector3f(0,32,-2),window,shader);
        scene.reload();

        //TODO Remove once main menu is in place!
        window.mouseState(true);
        Console.printSucc("Game initialized!",null);
    }

    private void checkInput() {
        if (Input.isKeyToggledUp(GLFW.GLFW_KEY_F11)) window.setIsFullscreen(!window.isIsFullscreen());
        if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT) && !DynamicData.DEBUG_GUI) window.mouseState(true);
        if (Input.isKeyToggledUp(GLFW.GLFW_KEY_RIGHT_SHIFT)) scene.test();
        if (Input.isKeyToggledUp(GLFW.GLFW_KEY_LEFT_ALT) && UserData.DEBUG) {
            DynamicData.DEBUG_GUI = !DynamicData.DEBUG_GUI;
            window.mouseState(DynamicData.DEBUG_GUI ? false : true);
        }
    }

    private void update() {
        PerformanceUtils.updateTPS();
        GLFW.glfwSetWindowTitle(window.getWindow(),window.getTitle() + " | FPS: " + DynamicData.CURRENT_FPS + " | TPS: " + DynamicData.CURRENT_TPS);
        MainThreadExecutor.executeAll();
        scene.reload();
    }

    private void render() {
        PerformanceUtils.updateFPS();
        window.update();

        scene.render();

        if (DynamicData.DEBUG_GUI) {
            debugUI.layout(window.nuklearUtils.getCtx(), 50, 50,this);
            UserData.WIREFRAME = debugUI.wireframe;
        }

        window.getNuklearUtils().render(NK_ANTI_ALIASING_ON, MAX_VERTEX_BUFFER, MAX_ELEMENT_BUFFER);
        window.swapBuffers();
    }

    private void close() {
        window.destroy();
        scene.destroy();
        shader.destroy();
    }
}