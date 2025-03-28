package de.jonas.engine.io;

//Import of Libs used.
import de.jonas.engine.utils.Console;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

@SuppressWarnings("all")
public class Input {
    //Create Arrays and vars for saving data and info about Input!
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] prevKeys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static boolean[] prevButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX,mouseY;
    private static double scrollX,scrollY;

    //Setup of all Input Callbacks.
    private GLFWKeyCallback keyboard;
    private GLFWCursorPosCallback mouseMove;
    private GLFWMouseButtonCallback mouseButtons;
    private GLFWScrollCallback mouseScroll;

    public Input() {
        //Input handler for keyboard presses!
        Console.printDebug("Configuring keyboard callback...",null);
        keyboard = new GLFWKeyCallback() {
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key >= 0 && key < keys.length) {
                    keys[key] = (action != GLFW.GLFW_RELEASE);
                } else {
                    Console.printError("Key is out of bounds! ",key);
                }
            }
        };
        Console.printSucc("Configured keyboard callback!",true);
        //Input handler for mouse movement.
        Console.printDebug("Configuring mouse move callback...",null);
        mouseMove = new GLFWCursorPosCallback() {
            public void invoke(long window, double xPos, double yPos) {
                mouseX = xPos;
                mouseY = yPos;
            }
        };
        Console.printSucc("Configured mouse move callback!",true);
        //Input handler for mouse buttons!
        Console.printDebug("Configuring mouse button callback...",null);
        mouseButtons = new GLFWMouseButtonCallback() {
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };
        Console.printSucc("Configured mouse button callback!",true);
        //Input handler for mouse scroll!
        Console.printDebug("Configuring mouse scroll callback...",null);
        mouseScroll = new GLFWScrollCallback() {
            public void invoke(long window, double offsetX, double offsetY) {
                scrollX += offsetX;
                scrollY += offsetY;
            }
        };
        Console.printSucc("Configured mouse scroll callback!",true);
    }
    //Checking if a mouse button is pressed
    public static boolean isButtonDown(int button) {
        return buttons[button];
    }
    //Checking if a mouse button is not pressed
    public static boolean isButtonUp(int button) {
        return !buttons[button];
    }
    //Checking if a mouse button is toggled.
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
    //Checking if a mouse button is toggled but only on the down motion.
    public static boolean isButtonToggledDown(int button) {
        boolean buttonNow = buttons[button];
        boolean buttonPrev = prevButtons[button];
        if (buttonNow != buttonPrev) {
            if (buttonNow) {
                prevButtons[button] = buttonNow;
                return true;
            }
            prevButtons[button] = buttonNow;
            return false;
        } else {
            return false;
        }
    }
    //Checking if a mouse button is toggled but only on the up motion.
    public static boolean isButtonToggledUp(int button) {
        boolean buttonNow = buttons[button];
        boolean buttonPrev = prevButtons[button];
        if (buttonNow != buttonPrev) {
            if (!buttonNow) {
                prevButtons[button] = buttonNow;
                return true;
            }
            prevButtons[button] = buttonNow;
            return false;
        } else {
            return false;
        }
    }
    //Checking if a key is pressed.
    public static boolean isKeyDown(int key) {
        return keys[key];
    }
    //Checking if a key is not pressed.
    public static boolean isKeyUp(int key) {
        return !keys[key];
    }
    //Checking if a key is toggled!
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
    //Checking if a key is toggled but only on the down motion!
    public static boolean isKeyToggledDown(int key) {
        boolean keyNow = keys[key];
        boolean keyPrev = prevKeys[key];
        if (keyNow != keyPrev) {
            if (keyNow) {
                prevKeys[key] = keyNow;
                return true;
            }
            prevKeys[key] = keyNow;
            return false;
        } else {
            return false;
        }
    }
    //Checking if a key is toggled but only on the up motion!
    public static boolean isKeyToggledUp(int key) {
        boolean keyNow = keys[key];
        boolean keyPrev = prevKeys[key];
        if (keyNow != keyPrev) {
            if (!keyNow) {
                prevKeys[key] = keyNow;
                return true;
            }
            prevKeys[key] = keyNow;
            return false;
        } else {
            return false;
        }
    }
    //Destroying all the Input handlers.
    public void destroy() {
        keyboard.free();
        mouseMove.free();
        mouseButtons.free();
        mouseScroll.free();
    }
    //Get the mouse pos on the X axis.
    public static double getMouseX() {
        return mouseX;
    }
    //Get the mouse pos on the Y axis.
    public static double getMouseY() {
        return mouseY;
    }
    //Get the mouse scroll pos on the X axis.
    public static double getScrollX() {
        return scrollX;
    }
    //Get the mouse scroll pos on the Y axis.
    public static double getScrollY() {
        return scrollY;
    }
    //Get the keyboardCallback.
    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }
    //Get the mouse movement callback.
    public GLFWCursorPosCallback getMouseMoveCallback() {
        return mouseMove;
    }
    //Get the mouse button callback.
    public GLFWMouseButtonCallback getMouseButtonsCallback() {
        return mouseButtons;
    }
    //Get the mouse scroll callback.
    public GLFWScrollCallback getMouseScrollCallback() {
        return mouseScroll;
    }
}