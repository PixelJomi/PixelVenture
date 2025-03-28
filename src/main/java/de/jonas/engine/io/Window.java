package de.jonas.engine.io;

import de.jonas.engine.math.Vector3f;
import de.jonas.engine.utils.Console;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {

    private int width, height;
    private int monitorWidth,monitorHeight;
    private int[] windowPosX = new int[1],windowPosY = new int[1];

    private long window;
    private String title;
    private Vector3f bgColor = new Vector3f(0f);

    public Input input;

    private GLFWWindowSizeCallback sizeCallback;
    private boolean isFullscreen;
    private boolean isResized;

    public static long time;
    public int frames;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create(int swapInterval) {
        //Try to initialize GLFW if not working return and output fatal Error!
        Console.printDebug("Initializing GLFW...",null);
        if (!GLFW.glfwInit()) {
            Console.printFatal("GLFW wasn't initialized!",false);
            return;
        } else {
            Console.printSucc("GLFW initialized!",true);
        }

        //Create a new Input Instance to handle Input!
        Console.printDebug("Creating input instance...",null);
        input = new Input();
        Console.printSucc("Input instance created!", true);


        //Try to create the GLFW window!
        Console.printDebug("Creating GLFW window...",null);
        window = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);
        if (window == 0) {
            Console.printFatal("GLFW window wasn't created!",window);
            return;
        } else {
            Console.printSucc("GLFW window created!",window);
        }

        //Get Monitor Data and store width and height in monitorWidth / monitorHeight!
        Console.printDebug("Getting monitor data...",null);
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        if (videoMode != null) {
            monitorWidth = videoMode.width();
            monitorHeight = videoMode.height();
            Console.printSucc("Monitor data acquired!",monitorWidth + " | " + monitorHeight);
        } else {
            Console.printWarn("Could not get videoMode for monitor!",false);
        }

        //Calculate and store the pos for the Window to be in the middle!
        windowPosX[0] = (int) (monitorWidth - width) / 2;
        windowPosY[0] = (int) (monitorHeight - height) / 2;

        //Set the Window pos to the previously calculated pos!
        Console.printDebug("Setting GLFW window position...",null);
        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
        Console.printSucc("Set GLFW window position!",windowPosX[0] + " | " + windowPosY[0]);

        //Make the GLFW Content be the created Window!
        Console.printDebug("Setting GLFW content to current...",null);
        GLFW.glfwMakeContextCurrent(window);
        Console.printSucc("Set GLFW content to current!",true);

        //Enable GL commands on GLFW window!
        Console.printDebug("Creating GL capabilities...",null);
        GL.createCapabilities();
        Console.printSucc("Created GL capabilities!",true);

        //Enable the DEPTH_TEST for the window!
        Console.printDebug("Enabling GL_DEPTH_TEST...",null);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        Console.printSucc("Enabled GL_DEPTH_TEST!",true);

        //Create Input callbacks!
        Console.printDebug("Creating input callbacks...",null);
        createCallbacks();
        Console.printSucc("Created input callbacks!",true);

        //Set FPS to V-Sync (60) if val is "1"!
        Console.printDebug("Setting buffer swap interval...",swapInterval);
        GLFW.glfwSwapInterval(swapInterval);
        Console.printSucc("Set buffer swap interval!",swapInterval);

        //Save the current time in milliseconds!
        Console.printDebug("Getting time in ms...",null);
        time = System.currentTimeMillis();
        Console.printSucc("Got time in ms!",time);

        //Show the current GLFW window to the USER!
        Console.printDebug("Trying to show window...",window);
        GLFW.glfwShowWindow(window);
        Console.printSucc("Window shown!",window);
    }

    private void createCallbacks() {
        //Creates a callback for the resizing of the window!
        Console.printDebug("Configuring resize callback...",null);
        sizeCallback = new GLFWWindowSizeCallback() {
            public void invoke(long window, int newWidth, int newHeight) {
                if (!isFullscreen) {
                    //Stores the new width into the current width var.
                    width = newWidth;
                    height = newHeight;
                }
                isResized = true;
            }
        };
        Console.printSucc("Configured resize callback!",true);

        //Create all other Callbacks from the Input Instance!
        Console.printDebug("Setting keyboard callback...",null);
        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        Console.printSucc("Set keyboard callback!",true);
        Console.printDebug("Setting mouse move callback...",null);
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        Console.printSucc("Set mouse move callback!",true);
        Console.printDebug("Setting mouse button callback...",null);
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        Console.printSucc("Set mouse button callback!",true);
        Console.printDebug("Setting mouse scroll callback...",null);
        GLFW.glfwSetScrollCallback(window, input.getMouseScrollCallback());
        Console.printSucc("Set mouse scroll callback!",true);
        Console.printDebug("Setting resize callback...",null);
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
        Console.printSucc("Set resize callback!",true);

    }

    public void update() {
        //Update the GL11 Viewport if the window is Resized!
        if (isResized) {
            if (isFullscreen) {
                GL11.glViewport(0, 0, monitorWidth, monitorHeight);
            } else {
                GL11.glViewport(0, 0, width, height);
            }
            isResized = false;
        }

        //Set the current Clear Color!
        GL11.glClearColor(bgColor.getX(), bgColor.getY(), bgColor.getZ(), 1.0f);

        //Clear the Color and Depth Buffer!
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        //Processes events that it already happened and does not wait! Other Option could be waitig
        //GLFW.glfwWaitEvents();
        GLFW.glfwPollEvents();

        //Increments the frames value by one!
        frames++;

        //Calls every second!
        if (System.currentTimeMillis() > time + 1000) {
            //Sets the Window title to be title + FPS: + frames
            GLFW.glfwSetWindowTitle(window, title + " | FPS: " + frames);
            //Updates the Time var to the current time in milliseconds!
            time = System.currentTimeMillis();
            //Sets the Frames back to zero!
            frames = 0;
        }
    }

    public void swapBuffers() {
        //Swaps the buffers!
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose() {
        //Returns a boolean that says if the window should be closed!
        return GLFW.glfwWindowShouldClose(window);
    }

    public void destroy() {
        Console.printDebug("Destroying window...",window);

        //Destroys any Data that isn't needed after the windows closes!
        input.destroy();
        sizeCallback.free();

        //Completely closes the window and destroys it!
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();

        Console.printSucc("Window was destroyed!",window);
    }

    public void setBackgroundColor(float r,float g,float b) {
        bgColor.set(r, g, b);
    }

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public String getTitle() {return title;}

    public long getWindow() {return window;}

    public boolean isIsFullscreen() {return isFullscreen;}

    public void setIsFullscreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        isResized = true;
        if (isFullscreen) {
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, monitorWidth, monitorHeight, 0); // LAST ONE IS THE REFRESH RATE!!!!
        } else {
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0); // LAST ONE IS THE REFRESH RATE!!!!
        }
    }


}

