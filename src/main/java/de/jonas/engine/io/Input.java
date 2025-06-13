package de.jonas.engine.io;

//Import of Libs used.
import de.jonas.engine.utils.Console;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

/**
 * Handles all user input (keyboard, mouse movement, mouse buttons, and scroll)
 * for the engine using GLFW callbacks. This class provides static methods to
 * query the current state of keys and mouse buttons, as well as their previous states
 * for "toggled" detection.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
@SuppressWarnings("all") // Suppress all warnings for this utility class
public class Input {
    /**
     * An array storing the current state of all keyboard keys.
     * {@code true} if pressed, {@code false} if released.
     */
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    /**
     * An array storing the previous state of all keyboard keys, used for toggle detection.
     */
    private static boolean[] prevKeys = new boolean[GLFW.GLFW_KEY_LAST];
    /**
     * An array storing the current state of all mouse buttons.
     * {@code true} if pressed, {@code false} if released.
     */
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    /**
     * An array storing the previous state of all mouse buttons, used for toggle detection.
     */
    private static boolean[] prevButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    /**
     * The current X-coordinate of the mouse cursor.
     */
    private static double mouseX,mouseY;
    /**
     * The accumulated scroll amount on the X-axis.
     */
    private static double scrollX,scrollY;

    /**
     * GLFW callback for keyboard events.
     */
    private GLFWKeyCallback keyboard;
    /**
     * GLFW callback for mouse cursor position events.
     */
    private GLFWCursorPosCallback mouseMove;
    /**
     * GLFW callback for mouse button events.
     */
    private GLFWMouseButtonCallback mouseButtons;
    /**
     * GLFW callback for mouse scroll events.
     */
    private GLFWScrollCallback mouseScroll;

    /**
     * Initializes the Input handler by setting up all necessary GLFW input callbacks.
     * This includes callbacks for keyboard presses, mouse movement, mouse button clicks,
     * and mouse scrolling. Debug messages are printed to the console during setup.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Input() {
        // Input handler for keyboard presses!
        Console.printDebug("Setting up input handler for keyboard input...", null);
        keyboard = new GLFWKeyCallback() {
            /**
             * Called when a key is pressed, repeated, or released. Updates the internal {@code keys} array.
             * @param window The window that received the event.
             * @param key The keyboard key that was pressed or released.
             * @param scancode The system-specific scancode of the key.
             * @param action {@link GLFW#GLFW_PRESS}, {@link GLFW#GLFW_RELEASE} or {@link GLFW#GLFW_REPEAT}.
             * @param mods Bitfield describing which modifier keys were held down.
             */
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key >= 0 && key < keys.length) {
                    keys[key] = (action != GLFW.GLFW_RELEASE);
                } else {
                    Console.printError("Key is out of bounds! ", key);
                }
            }
        };
        Console.printSucc("Finished setting up input handler for keyboard input!", true);

        // Input handler for mouse movement.
        Console.printDebug("Setting up input handler for mouse movement...", null);
        mouseMove = new GLFWCursorPosCallback() {
            /**
             * Called when the cursor is moved. Updates the internal {@code mouseX} and {@code mouseY} coordinates.
             * @param window The window that received the event.
             * @param xPos The new cursor x-coordinate, relative to the left edge of the content area.
             * @param yPos The new cursor y-coordinate, relative to the top edge of the content area.
             */
            @Override
            public void invoke(long window, double xPos, double yPos) {
                mouseX = xPos;
                mouseY = yPos;
            }
        };
        Console.printSucc("Finished setting up input handler for mouse movement!", true);

        // Input handler for mouse buttons!
        Console.printDebug("Setting up input handler for mouse buttons...", null);
        mouseButtons = new GLFWMouseButtonCallback() {
            /**
             * Called when a mouse button is pressed or released. Updates the internal {@code buttons} array.
             * @param window The window that received the event.
             * @param button The mouse button that was pressed or released.
             * @param action {@link GLFW#GLFW_PRESS} or {@link GLFW#GLFW_RELEASE}.
             * @param mods Bitfield describing which modifier keys were held down.
             */
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };
        Console.printSucc("Finished setting up input handler for mouse buttons!", true);

        // Input handler for mouse scroll!
        Console.printDebug("Setting up input handler for mouse scroll...", null);
        mouseScroll = new GLFWScrollCallback() {
            /**
             * Called when a scrolling device is used. Updates the internal {@code scrollX} and {@code scrollY} values.
             * @param window The window that received the event.
             * @param offsetX The scroll offset along the X-axis.
             * @param offsetY The scroll offset along the Y-axis.
             */
            @Override
            public void invoke(long window, double offsetX, double offsetY) {
                scrollX += offsetX;
                scrollY += offsetY;
            }
        };
        Console.printSucc("Finished setting up input handler for mouse scroll!", true);
    }

    /**
     * Checks if a specific mouse button is currently pressed down.
     *
     * @param button The GLFW mouse button code (e.g., {@link GLFW#GLFW_MOUSE_BUTTON_LEFT}).
     * @return {@code true} if the button is currently pressed, {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static boolean isButtonDown(int button) {
        return buttons[button];
    }

    /**
     * Checks if a specific mouse button is currently not pressed (i.e., released).
     *
     * @param button The GLFW mouse button code.
     * @return {@code true} if the button is currently released, {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static boolean isButtonUp(int button) {
        return !buttons[button];
    }

    /**
     * Checks if a specific mouse button's state has changed (either from pressed to released,
     * or from released to pressed) since the last check. This method updates the previous state.
     *
     * @param button The GLFW mouse button code.
     * @return {@code true} if the button's state has changed, {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static boolean isButtonToggled(int button) {
        boolean buttonNow = buttons[button];
        boolean buttonPrev = prevButtons[button];
        if (buttonNow != buttonPrev) {
            prevButtons[button] = buttonNow;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a specific mouse button has just been pressed down (i.e., its state
     * changed from released to pressed) since the last check. This method updates the previous state.
     *
     * @param button The GLFW mouse button code.
     * @return {@code true} if the button was just pressed down, {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static boolean isButtonToggledDown(int button) {
        boolean buttonNow = buttons[button];
        boolean buttonPrev = prevButtons[button];
        if (buttonNow != buttonPrev) {
            if (buttonNow) { // Only return true if it's now down
                prevButtons[button] = buttonNow;
                return true;
            }
            prevButtons[button] = buttonNow; // Update prev state even if not 'toggled down'
            return false;
        } else {
            return false;
        }
    }

    /**
     * Checks if a specific mouse button has just been released (i.e., its state
     * changed from pressed to released) since the last check. This method updates the previous state.
     *
     * @param button The GLFW mouse button code.
     * @return {@code true} if the button was just released, {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static boolean isButtonToggledUp(int button) {
        boolean buttonNow = buttons[button];
        boolean buttonPrev = prevButtons[button];
        if (buttonNow != buttonPrev) {
            if (!buttonNow) { // Only return true if it's now up
                prevButtons[button] = buttonNow;
                return true;
            }
            prevButtons[button] = buttonNow; // Update prev state even if not 'toggled up'
            return false;
        } else {
            return false;
        }
    }

    /**
     * Checks if a specific keyboard key is currently pressed down.
     *
     * @param key The GLFW keyboard key code (e.g., {@link GLFW#GLFW_KEY_SPACE}).
     * @return {@code true} if the key is currently pressed, {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static boolean isKeyDown(int key) {
        return keys[key];
    }

    /**
     * Checks if a specific keyboard key is currently not pressed (i.e., released).
     *
     * @param key The GLFW keyboard key code.
     * @return {@code true} if the key is currently released, {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static boolean isKeyUp(int key) {
        return !keys[key];
    }

    /**
     * Checks if a specific keyboard key's state has changed (either from pressed to released,
     * or from released to pressed) since the last check. This method updates the previous state.
     *
     * @param key The GLFW keyboard key code.
     * @return {@code true} if the key's state has changed, {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static boolean isKeyToggled(int key) {
        boolean keyNow = keys[key];
        boolean keyPrev = prevKeys[key];
        if (keyNow != keyPrev) {
            prevKeys[key] = keyNow;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a specific keyboard key has just been pressed down (i.e., its state
     * changed from released to pressed) since the last check. This method updates the previous state.
     *
     * @param key The GLFW keyboard key code.
     * @return {@code true} if the key was just pressed down, {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static boolean isKeyToggledDown(int key) {
        boolean keyNow = keys[key];
        boolean keyPrev = prevKeys[key];
        if (keyNow != keyPrev) {
            if (keyNow) { // Only return true if it's now down
                prevKeys[key] = keyNow;
                return true;
            }
            prevKeys[key] = keyNow; // Update prev state even if not 'toggled down'
            return false;
        } else {
            return false;
        }
    }

    /**
     * Checks if a specific keyboard key has just been released (i.e., its state
     * changed from pressed to released) since the last check. This method updates the previous state.
     *
     * @param key The GLFW keyboard key code.
     * @return {@code true} if the key was just released, {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static boolean isKeyToggledUp(int key) {
        boolean keyNow = keys[key];
        boolean keyPrev = prevKeys[key];
        if (keyNow != keyPrev) {
            if (!keyNow) { // Only return true if it's now up
                prevKeys[key] = keyNow;
                return true;
            }
            prevKeys[key] = keyNow; // Update prev state even if not 'toggled up'
            return false;
        } else {
            return false;
        }
    }

    /**
     * Destroys and frees all allocated GLFW input callback resources.
     * This should be called when the input handler is no longer needed, typically
     * during application shutdown, to prevent memory leaks.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void destroy() {
        keyboard.free();
        mouseMove.free();
        mouseButtons.free();
        mouseScroll.free();
    }

    /**
     * Retrieves the current X-coordinate of the mouse cursor.
     *
     * @return The current X-coordinate of the mouse cursor, relative to the content area of the window.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static double getMouseX() {
        return mouseX;
    }

    /**
     * Retrieves the current Y-coordinate of the mouse cursor.
     *
     * @return The current Y-coordinate of the mouse cursor, relative to the content area of the window.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static double getMouseY() {
        return mouseY;
    }

    /**
     * Retrieves the accumulated scroll offset along the X-axis.
     * This value is cumulative until reset or the application ends.
     *
     * @return The total horizontal scroll amount.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static double getScrollX() {
        return scrollX;
    }

    /**
     * Retrieves the accumulated scroll offset along the Y-axis.
     * This value is cumulative until reset or the application ends.
     *
     * @return The total vertical scroll amount.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static double getScrollY() {
        return scrollY;
    }

    /**
     * Returns the GLFW keyboard callback instance used by this input handler.
     * This can be used to set the callback for a GLFW window.
     *
     * @return The {@link GLFWKeyCallback} instance.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    /**
     * Returns the GLFW mouse cursor position callback instance used by this input handler.
     * This can be used to set the callback for a GLFW window.
     *
     * @return The {@link GLFWCursorPosCallback} instance.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public GLFWCursorPosCallback getMouseMoveCallback() {
        return mouseMove;
    }

    /**
     * Returns the GLFW mouse button callback instance used by this input handler.
     * This can be used to set the callback for a GLFW window.
     *
     * @return The {@link GLFWMouseButtonCallback} instance.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public GLFWMouseButtonCallback getMouseButtonsCallback() {
        return mouseButtons;
    }

    /**
     * Returns the GLFW mouse scroll callback instance used by this input handler.
     * This can be used to set the callback for a GLFW window.
     *
     * @return The {@link GLFWScrollCallback} instance.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public GLFWScrollCallback getMouseScrollCallback() {
        return mouseScroll;
    }
}