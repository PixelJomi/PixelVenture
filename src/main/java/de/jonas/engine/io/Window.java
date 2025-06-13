package de.jonas.engine.io;

import de.jonas.engine.math.Matrix4f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.utils.Console;
import de.jonas.engine.data.PVData;
import de.jonas.engine.data.RunningData;
import de.jonas.engine.data.UserData;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.system.MemoryStack.stackPush;

/**
 * Manages the GLFW window, its properties, and its lifecycle within the engine.
 * This class handles window creation, resizing, input binding, OpenGL context setup,
 * and background operations like buffer swapping and event polling.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
public class Window {
    /**
     * The GLFW window handle. This is a unique identifier for the window.
     */
    private long window;
    /**
     * The callback for handling window resize events.
     */
    private GLFWWindowSizeCallback sizeCallback;

    /**
     * The width of the primary monitor in pixels.
     */
    private int monitorWidth;
    /**
     * The height of the primary monitor in pixels.
     */
    private int monitorHeight;
    /**
     * The current width of the window in pixels.
     */
    private int width;
    /**
     * The current height of the window in pixels.
     */
    private int height;
    /**
     * The title of the window, displayed in the window's title bar.
     */
    private String title;

    /**
     * An array to store the window's X position when exiting fullscreen mode.
     */
    private int[] windowPosX = new int[1];
    /**
     * An array to store the window's Y position when exiting fullscreen mode.
     */
    private int[] windowPosY = new int[1];

    /**
     * The background color of the window, cleared on each frame.
     * Uses {@link Vector3f} for RGB values (0.0-1.0 range).
     */
    private Vector3f backgroundColor = PVData.DEFAULT_BACKGROUND_COLOR;
    /**
     * The projection matrix used for rendering, updated on window resize or FOV changes.
     */
    private Matrix4f projectionMatrix;
    /**
     * A flag indicating whether the window is currently in fullscreen mode.
     */
    private boolean isFullscreen;
    /**
     * A flag indicating if the window has been resized and the viewport needs updating.
     */
    private boolean isResized;

    /**
     * The {@link Input} instance associated with this window, handling keyboard and mouse events.
     */
    public Input input;

    /**
     * Constructs a new Window instance with specified dimensions and title.
     * The projection matrix is initialized based on the given dimensions.
     *
     * @param width  The initial width of the window.
     * @param height The initial height of the window.
     * @param title  The initial title of the window.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        updateProjectionMatrix();
    }

    /**
     * Creates and initializes the GLFW window and its associated OpenGL context.
     * This method handles GLFW initialization, window creation, monitor data retrieval,
     * window positioning, OpenGL capability setup (DEPTH_TEST, CULL_FACE, BLEND),
     * input callback registration, and buffer swap interval.
     * If GLFW or the window cannot be created, a fatal error is logged.
     *
     * @param swapInterval The swap interval (V-Sync setting). 0 for no V-Sync, 1 for V-Sync.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void create(int swapInterval) {
        Console.printDebug("Creating Window Class...", null);
        //Try to initialize GLFW if not working return and output fatal Error!
        Console.printDebug("Initializing GLFW...", null);
        if (!GLFW.glfwInit()) {
            Console.printFatal("GLFW wasn't initialized!", false);
            return;
        }
        Console.printSucc("GLFW successfully initialized!", true);

        //Create a new Input Instance to handle Input!
        Console.printDebug("Creating Input instance...", null);
        input = new Input();
        Console.printSucc("Successfully created Input instance!", true);

        //Try to create the GLFW window!
        Console.printDebug("Creating window handle...", null);
        window = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);
        if (window == 0) {
            Console.printFatal("GLFW window wasn't created!", window);
            return;
        }
        Console.printSucc("Window handle created!", window);

        //Get Monitor Data and store width and height in monitorWidth / monitorHeight!
        Console.printDebug("Getting videoMode for monitor...", null);
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        if (videoMode != null) {
            monitorWidth = videoMode.width();
            monitorHeight = videoMode.height();
            Console.printSucc("Successfully got videoMode for monitor!", monitorWidth + "|" + monitorHeight);
        } else {
            Console.printWarn("Could not get videoMode for monitor!", false);
        }

        //Calculate and store the pos for the Window to be in the middle!
        windowPosX[0] = (int) (monitorWidth - width) / 2;
        windowPosY[0] = (int) (monitorHeight - height) / 2;

        //Set the Window pos to the previously calculated pos!
        Console.printDebug("Setting window position to the middle of the screen...", windowPosX[0] + "|" + windowPosY[0]);
        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
        Console.printSucc("Set window position to the middle of the screen!", windowPosX[0] + "|" + windowPosY[0]);

        //Make the GLFW Content be the created Window!
        Console.printDebug("Setting the GLFW Context to created window...", window);
        GLFW.glfwMakeContextCurrent(window);
        Console.printSucc("Set the GLFW Context to created window!", window);

        //Enable GL commands on GLFW window!
        Console.printDebug("Enabling OpenGL commands on GLFW window...", window);
        GL.createCapabilities();
        Console.printSucc("Enabled OpenGL commands on GLFW window!", window);

        //Enable the DEPTH_TEST for the window!
        Console.printDebug("Enabling DEPTH_TEST...", GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        Console.printSucc("Enabled DEPTH_TEST!", GL11.GL_DEPTH_TEST);
        //Enable the CULL_FACE for the window!
        Console.printDebug("Enabling CULL_FACE...", GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_CULL_FACE);
        Console.printSucc("Enabled CULL_FACE!", GL11.GL_CULL_FACE);
        //Enable the GL_BLEND for the window!
        Console.printDebug("Enabling GL_BLEND...", GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_BLEND);
        Console.printSucc("Enabled GL_BLEND!", GL11.GL_BLEND);

        //Create Input callbacks!
        Console.printDebug("Creating callbacks...", null);
        createCallbacks();
        Console.printSucc("Callbacks created!", true);

        //Set FPS to V-Sync (60) if val is "1"!
        Console.printDebug("Setting the Buffer swap interval...", swapInterval);
        GLFW.glfwSwapInterval(swapInterval);
        Console.printSucc("Set the Buffer swap interval!", swapInterval);

        //Show the current GLFW window to the USER!
        Console.printDebug("Setting window to show on screen...", window);
        GLFW.glfwShowWindow(window);
        Console.printSucc("Window Class successfully created!", true);
    }

    /**
     * Sets up and registers all necessary GLFW callbacks for the window.
     * This includes a custom {@link GLFWWindowSizeCallback} to handle window resizing
     * and delegates other input callbacks to the {@link Input} instance.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    private void createCallbacks() {
        //Creates a callback for the resizing of the window!
        sizeCallback = new GLFWWindowSizeCallback() {
            /**
             * Invoked when the window is resized. Updates the window's dimensions and
             * marks it as resized if not in fullscreen mode.
             * @param window The window that was resized.
             * @param newWidth The new width of the window.
             * @param newHeight The new height of the window.
             */
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
    }

    /**
     * Updates the projection matrix based on the current window dimensions and
     * camera field of view (FOV), near plane, and far plane values from {@link UserData}.
     * This method is called upon window creation and every time the window is resized.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    private void updateProjectionMatrix() {
        projectionMatrix = Matrix4f.projection(UserData.FOV, (float) width / (float) height, UserData.CAMERA_NEAR, UserData.CAMERA_FAR);
    }

    /**
     * Performs per-frame updates for the window. This includes updating the projection matrix,
     * adjusting the OpenGL viewport if the window was resized, setting the clear color,
     * clearing the color and depth buffers, and polling GLFW events.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void update() {
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
        GLFW.glfwPollEvents();
    }

    /**
     * Swaps the front and back buffers of the window. This makes the content rendered
     * to the back buffer visible on the screen. This should be called after all
     * rendering commands for the current frame have been issued.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void swapBuffers() {
        //Swaps the buffers!
        GLFW.glfwSwapBuffers(window);
    }

    /**
     * Checks if the window should close. This flag is typically set when the user
     * clicks the close button or an application-defined exit condition is met.
     *
     * @return {@code true} if the window close flag is set, {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public boolean shouldClose() {
        //Returns a boolean that says if the window should be closed!
        return GLFW.glfwWindowShouldClose(window);
    }

    /**
     * Destroys all resources associated with the window and GLFW.
     * This includes freeing input callbacks, destroying the GLFW window itself,
     * and terminating the GLFW library. This method should be called during
     * application shutdown to prevent resource leaks.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void destroy() {
        //Destroys any Data that isn't needed after the windows closes!
        input.destroy();
        sizeCallback.free();

        //Completely closes the window and destroys it!
        GLFW.glfwWindowShouldClose(window); // This line is redundant, glfwDestroyWindow handles closing.
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    /**
     * Attempts to synchronize the game loop to a target Frames Per Second (FPS).
     * It introduces a small delay to ensure the loop does not run faster than the specified FPS.
     *
     * @param loopStartTime The system time (e.g., from {@link GLFW#glfwGetTime()}) at the start of the current loop iteration.
     * @param fps           The target Frames Per Second to synchronize to.
     * @author PixelJomi (Jomicraft) / Jonas
     */
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

    /**
     * Sets the background clear color for the window. This color will be used
     * when {@link GL11#glClear(int)} is called with {@link GL11#GL_COLOR_BUFFER_BIT}.
     *
     * @param r The red component (0.0-1.0).
     * @param g The green component (0.0-1.0).
     * @param b The blue component (0.0-1.0).
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void setBackgroundColor(float r,float g,float b) {backgroundColor.set(r, g, b);}

    /**
     * Retrieves the current background clear color of the window.
     *
     * @return A {@link Vector3f} representing the RGB background color (0.0-1.0).
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f getBackgroundColor() {return backgroundColor;}

    /**
     * Retrieves the current width of the window.
     *
     * @return The width of the window in pixels.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public int getWidth() {return width;}

    /**
     * Retrieves the current height of the window.
     *
     * @return The height of the window in pixels.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public int getHeight() {return height;}

    /**
     * Retrieves the title of the window.
     *
     * @return The string title of the window.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public String getTitle() {return title;}

    /**
     * Retrieves the raw GLFW window handle.
     *
     * @return The long GLFW window handle.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public long getWindow() {return window;}

    /**
     * Checks if the window is currently in fullscreen mode.
     *
     * @return {@code true} if the window is in fullscreen, {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public boolean isIsFullscreen() {return isFullscreen;}

    /**
     * Sets the fullscreen mode of the window.
     * When entering fullscreen, the window's current position is saved, and it's moved
     * to the monitor with the largest overlap. When exiting fullscreen, it's restored
     * to its original position and size. This also flags the window as resized.
     *
     * @param isFullscreen {@code true} to enter fullscreen, {@code false} to exit fullscreen.
     * @author PixelJomi (Jomicraft) / Jonas
     */
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

    /**
     * Determines the primary monitor that the window currently overlaps with the most.
     * This is useful for placing the window in fullscreen mode on the most relevant display.
     * It iterates through all available monitors and calculates the overlap area.
     *
     * @return The GLFW monitor handle of the monitor with the largest overlap, or 0 if no monitors are found or an error occurs.
     * @author PixelJomi (Jomicraft) / Jonas
     */
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
            } catch (Error e) { // Catch java.lang.Error for issues like stack overflow from JNI calls
                Console.printError("Could not get monitor overlap data!", monitor);
            }
        }
        return bestMonitor;
    }


    /**
     * Retrieves the current Frames Per Second (FPS) as reported by {@link RunningData}.
     *
     * @return The current FPS value.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public int getCURRENT_FPS() {return RunningData.CURRENT_FPS;}

    /**
     * Retrieves the current projection matrix used for rendering.
     * This matrix is updated whenever the window is resized.
     *
     * @return The {@link Matrix4f} representing the projection matrix.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Matrix4f getProjectionMatrix() {return projectionMatrix;}
}