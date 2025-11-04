package de.jonas.engine.io;

import java.nio.IntBuffer;

import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_DEBUG_CONTEXT;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetFramebufferSize;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import static org.lwjgl.opengl.ARBDebugOutput.GL_DEBUG_SEVERITY_LOW_ARB;
import static org.lwjgl.opengl.ARBDebugOutput.GL_DEBUG_SOURCE_API_ARB;
import static org.lwjgl.opengl.ARBDebugOutput.GL_DEBUG_TYPE_OTHER_ARB;
import static org.lwjgl.opengl.ARBDebugOutput.glDebugMessageControlARB;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11C.glEnable;
import org.lwjgl.opengl.GL33;
import org.lwjgl.opengl.GL43;
import static org.lwjgl.opengl.GL43.GL_DEBUG_OUTPUT;
import static org.lwjgl.opengl.GL43.glDebugMessageCallback;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.opengl.GLDebugMessageCallback;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.opengl.KHRDebug;
import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryStack;
import static org.lwjgl.system.MemoryStack.stackPush;

import de.jonas.engine.data.DynamicData;
import de.jonas.engine.data.StaticData;
import de.jonas.engine.data.UserData;
import de.jonas.engine.math.Matrix4f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.NuklearUtils;

public class Window {
    private long window;
    private int monitorWidth;
    private int monitorHeight;
    private int width;
    private int height;
    private String title;

    private int[] windowPosX = new int[1];
    private int[] windowPosY = new int[1];

    private Vector3f backgroundColor = StaticData.DEFAULT_BACKGROUND_COLOR;
    private GLFWWindowSizeCallback sizeCallback;
    private Matrix4f projectionMatrix;

    private boolean isFullscreen;
    private boolean isResized;

    public Input input;
    public NuklearUtils nuklearUtils;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        updateProjectionMatrix();
    }

    public void create(boolean vsync) {
        //Try to initialize GLFW if not working return and output fatal Error!
        Console.printDebug("Initializing GLFW...", null);
        if (!GLFW.glfwInit()) {
            Console.printFatal("GLFW wasn't initialized!", false);
            return;
        }

        glfwDefaultWindowHints();
        if (UserData.DEBUG) glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);


        //Try to create the GLFW window!
        window = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);
        if (window == 0) {
            Console.printFatal("GLFW window wasn't created!", window);
            return;
        }

        //Get Monitor Data and store width and height in monitorWidth / monitorHeight!
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        if (videoMode != null) {
            monitorWidth = videoMode.width();
            monitorHeight = videoMode.height();
        } else {
            Console.printWarn("Could not get videoMode for monitor!", false);
        }

        //Calculate and store the pos for the Window to be in the middle!
        windowPosX[0] = (int) (monitorWidth - width) / 2;
        windowPosY[0] = (int) (monitorHeight - height) / 2;

        //Set the Window pos to the previously calculated pos!
        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);

        //Make the GLFW Content be the created Window!
        GLFW.glfwMakeContextCurrent(window);

        //Enable GL commands on GLFW window!
        GL.createCapabilities();
        GLCapabilities caps = GL.createCapabilities();

        try {
            if (UserData.DEBUG) {
                Callback debugProc = GLUtil.setupDebugMessageCallback();
            }
        } catch (Exception e) {
            Console.printError("A error accrued while setting up GLDebugging.",false);
        }

        if (caps.OpenGL43) {
            GL43.glDebugMessageControl(GL43.GL_DEBUG_SOURCE_API, GL43.GL_DEBUG_TYPE_OTHER, GL43.GL_DEBUG_SEVERITY_NOTIFICATION, (IntBuffer)null, false);
        } else if (caps.GL_KHR_debug) {
            KHRDebug.glDebugMessageControl(
                    KHRDebug.GL_DEBUG_SOURCE_API,
                    KHRDebug.GL_DEBUG_TYPE_OTHER,
                    KHRDebug.GL_DEBUG_SEVERITY_NOTIFICATION,
                    (IntBuffer)null,
                    false
            );
        } else if (caps.GL_ARB_debug_output) {
            glDebugMessageControlARB(GL_DEBUG_SOURCE_API_ARB, GL_DEBUG_TYPE_OTHER_ARB, GL_DEBUG_SEVERITY_LOW_ARB, (IntBuffer)null, false);
        }

        glEnable(GL_DEBUG_OUTPUT);
        glDebugMessageCallback((source, type, id, severity, length, message, userParam) -> {
            Console.printError("OpenGL Error: " + GLDebugMessageCallback.getMessage(length, message),false);
        }, 0);

        //Enable the DEPTH_TEST for the window!
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        //Enable the CULL_FACE for the window!
        GL11.glEnable(GL11.GL_CULL_FACE);
        //Enable the GL_BLEND for the window!
        GL11.glEnable(GL11.GL_BLEND);

        GL33.glBlendFunc(GL33.GL_SRC_ALPHA, GL33.GL_ONE_MINUS_SRC_ALPHA);

        //Create a new NuklearUtils Object!
        nuklearUtils = new NuklearUtils(this);

        //Create a new Input Instance to handle Input!
        input = new Input(nuklearUtils);

        nuklearUtils.setupContext();
        nuklearUtils.setupFont();

        //Create Input callbacks!
        createCallbacks();

        //Set FPS to V-Sync (60 fps)
        GLFW.glfwSwapInterval(vsync ? 1 : 0);

        //Show the current GLFW window to the USER!
        GLFW.glfwShowWindow(window);
    }

    private void createCallbacks() {
        //Creates a callback for the resizing of the window!
        sizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int newWidth, int newHeight) {
                if (!isFullscreen) {
                    //Stores the new width into the current width var.
                    width = newWidth;
                    height = newHeight;
                    updateProjectionMatrix(); // Update projection matrix on resize
                }
                isResized = true;
            }
        };

        //Create all other Callbacks from the Input Instance!
        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetScrollCallback(window, input.getMouseScrollCallback());
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
        GLFW.glfwSetCharCallback(window, input.getCharCallback());
    }

    private void updateProjectionMatrix() {
        projectionMatrix = Matrix4f.projection(UserData.FOV, (float) width / (float) height, UserData.CAMERA_NEAR, UserData.CAMERA_FAR);
    }

    public void update() {
        try (MemoryStack stack = stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);

            glfwGetWindowSize(window, w, h);
            width = w.get(0);
            height = h.get(0);

            glfwGetFramebufferSize(window, w, h);
            monitorWidth = w.get(0);
            monitorHeight = h.get(0);
        }
        //Update the GL11 Viewport if the window is Resized!
        updateProjectionMatrix(); // Re-calculate projection matrix, even if not explicitly resized, for consistency
        if (isResized) {
            if (isFullscreen) {
                GL11.glViewport(0, 0, monitorWidth, monitorHeight);
            } else {
                GL11.glViewport(0, 0, width, height);
            }
            isResized = false;
        }

        //Set the current Clear Color!
        GL11.glClearColor(backgroundColor.getX(), backgroundColor.getY(), backgroundColor.getZ(), 1.0f);

        //Clear the Color and Depth Buffer!
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        //Processes events that it already happened and does not wait! Other Option could be waiting
        //GLFW.glfwWaitEvents();

        nuklearUtils.newFrame();
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
        //Destroys any Data that isn't needed after the windows closes!
        input.destroy();
        sizeCallback.free();

        //Completely closes the window and destroys it!
        GLFW.glfwWindowShouldClose(window); // This line is redundant, glfwDestroyWindow handles closing.
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public void sync(double loopStartTime, int fps) {
        float loopSlot = 1.0f / fps;
        double endTime = loopStartTime + loopSlot;
        while(GLFW.glfwGetTime() < endTime) {
            try {
                Thread.sleep(1); // Sleep for 1 millisecond
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
        }
    }

    public void mouseState(boolean lock) {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, lock ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
    }

    public void setBackgroundColor(float r,float g,float b) {backgroundColor.set(r, g, b);}

    public Vector3f getBackgroundColor() {return backgroundColor;}

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public String getTitle() {return title;}

    public long getWindow() {return window;}

    public boolean isIsFullscreen() {return isFullscreen;}

    public void setIsFullscreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        isResized = true; // Always flag as resized to ensure viewport update
        if (isFullscreen) {
            // Save current window position before going fullscreen
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
            // Set window to fullscreen on the monitor with the most overlap
            GLFW.glfwSetWindowMonitor(window, getCurrentOverlapMonitor(), 0, 0, monitorWidth, monitorHeight, 0);
        } else {
            // Restore window to previous position and size
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
        }
    }

    public long getCurrentOverlapMonitor() {
        PointerBuffer monitors = GLFW.glfwGetMonitors();
        if (monitors == null) {
            Console.printError("No monitors found!", false);
            return 0;
        }

        int maxOverlap = 0;
        long bestMonitor = 0;

        for (int i = 0; i < monitors.capacity(); i++) {
            long monitor = monitors.get(i);

            GLFWVidMode vidMode = GLFW.glfwGetVideoMode(monitor);
            if (vidMode == null) {
                continue;
            }
            // Use monitor's current resolution for overlap calculation
            int monitorWidth = vidMode.width();
            int monitorHeight = vidMode.height();

            try (MemoryStack stack = stackPush()) {
                IntBuffer monitorXPos = stack.mallocInt(1);
                IntBuffer monitorYPos = stack.mallocInt(1);
                GLFW.glfwGetMonitorPos(monitor, monitorXPos, monitorYPos);
                int monitorX = monitorXPos.get(0);
                int monitorY = monitorYPos.get(0);

                IntBuffer windowXPos = stack.mallocInt(1);
                IntBuffer windowYPos = stack.mallocInt(1);
                GLFW.glfwGetWindowPos(window, windowXPos, windowYPos);
                int windowX = windowXPos.get(0);
                int windowY = windowYPos.get(0);

                IntBuffer widthBuffer = stack.mallocInt(1);
                IntBuffer heightBuffer = stack.mallocInt(1);
                GLFW.glfwGetWindowSize(window, widthBuffer, heightBuffer);
                int windowWidth = widthBuffer.get(0);
                int windowHeight = heightBuffer.get(0);

                // Calculate overlap area
                int overlapArea = Math.max(0, Math.min(windowX + windowWidth, monitorX + monitorWidth) - Math.max(windowX, monitorX)) *
                        Math.max(0, Math.min(windowY + windowHeight, monitorY + monitorHeight) - Math.max(windowY, monitorY));

                if (overlapArea > maxOverlap) {
                    maxOverlap = overlapArea;
                    bestMonitor = monitor;
                }
            } catch (Error e) {
                Console.printError("Could not get monitor overlap data!", monitor);
            }
        }
        return bestMonitor;
    }

    public int getCURRENT_FPS() {return DynamicData.CURRENT_FPS;}

    public int getMonitorWidth() {return monitorWidth;}

    public int getMonitorHeight() {return monitorHeight;}

    public NuklearUtils getNuklearUtils() {return nuklearUtils;}

    public Matrix4f getProjectionMatrix() {return projectionMatrix;}
}