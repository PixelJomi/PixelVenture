package de.jonas.engine.io;

//Import of Libs used.
import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.NuklearUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.nuklear.NkContext;
import org.lwjgl.nuklear.NkVec2;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.nuklear.Nuklear.*;
import static org.lwjgl.nuklear.Nuklear.nk_input_button;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.*;


@SuppressWarnings("all") // Suppress all warnings for this utility class
public class Input {

    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] prevKeys = new boolean[GLFW.GLFW_KEY_LAST];

    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static boolean[] prevButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    private static double mouseX,mouseY;
    private float scaleX = 1.0f, scaleY = 1.0f;
    private static double scrollX,scrollY;

    private GLFWKeyCallback keyboard;
    private GLFWCursorPosCallback mouseMove;
    private GLFWMouseButtonCallback mouseButtons;
    private GLFWScrollCallback mouseScroll;
    private GLFWCharCallback charCallback;

    public Input(NuklearUtils nuklearUtils) {
        // Input handler for keyboard presses!
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key >= 0 && key < keys.length) {
                    keys[key] = (action != GLFW.GLFW_RELEASE);
                } else {
                    Console.printError("Key is out of bounds! ", key);
                }

                boolean press = action == GLFW_PRESS;
                switch (key) {
                    case GLFW_KEY_ESCAPE:
                        glfwSetWindowShouldClose(window, true);
                        break;
                    case GLFW_KEY_DELETE:
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_DEL, press);
                        break;
                    case GLFW_KEY_ENTER:
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_ENTER, press);
                        break;
                    case GLFW_KEY_TAB:
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_TAB, press);
                        break;
                    case GLFW_KEY_BACKSPACE:
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_BACKSPACE, press);
                        break;
                    case GLFW_KEY_UP:
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_UP, press);
                        break;
                    case GLFW_KEY_DOWN:
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_DOWN, press);
                        break;
                    case GLFW_KEY_HOME:
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_TEXT_START, press);
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_SCROLL_START, press);
                        break;
                    case GLFW_KEY_END:
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_TEXT_END, press);
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_SCROLL_END, press);
                        break;
                    case GLFW_KEY_PAGE_DOWN:
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_SCROLL_DOWN, press);
                        break;
                    case GLFW_KEY_PAGE_UP:
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_SCROLL_UP, press);
                        break;
                    case GLFW_KEY_LEFT_SHIFT:
                    case GLFW_KEY_RIGHT_SHIFT:
                        nk_input_key(nuklearUtils.getCtx(), NK_KEY_SHIFT, press);
                        break;
                    case GLFW_KEY_LEFT_CONTROL:
                    case GLFW_KEY_RIGHT_CONTROL:
                        if (press) {
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_COPY, glfwGetKey(window, GLFW_KEY_C) == GLFW_PRESS);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_PASTE, glfwGetKey(window, GLFW_KEY_P) == GLFW_PRESS);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_CUT, glfwGetKey(window, GLFW_KEY_X) == GLFW_PRESS);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_TEXT_UNDO, glfwGetKey(window, GLFW_KEY_Z) == GLFW_PRESS);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_TEXT_REDO, glfwGetKey(window, GLFW_KEY_R) == GLFW_PRESS);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_TEXT_WORD_LEFT, glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_TEXT_WORD_RIGHT, glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_TEXT_LINE_START, glfwGetKey(window, GLFW_KEY_B) == GLFW_PRESS);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_TEXT_LINE_END, glfwGetKey(window, GLFW_KEY_E) == GLFW_PRESS);
                        } else {
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_LEFT, glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_RIGHT, glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_COPY, false);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_PASTE, false);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_CUT, false);
                            nk_input_key(nuklearUtils.getCtx(), NK_KEY_SHIFT, false);
                        }
                        break;
                }
            }
        };

        charCallback = new GLFWCharCallback() {
            @Override
            public void invoke(long window, int codepoint) {
                nk_input_unicode(nuklearUtils.getCtx(), codepoint);
            }
        };

        // Input handler for mouse movement.
        mouseMove = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xPos, double yPos) {
                mouseX = xPos;
                mouseY = yPos;
                nk_input_motion(nuklearUtils.getCtx(), (int) xPos, (int) yPos);
            }
        };

        // Input handler for mouse buttons!
        mouseButtons = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                try (MemoryStack stack = stackPush()) {
                    DoubleBuffer cx = stack.mallocDouble(1);
                    DoubleBuffer cy = stack.mallocDouble(1);

                    glfwGetCursorPos(window, cx, cy);

                    int x = (int)cx.get(0);
                    int y = (int)cy.get(0);

                    int nkButton;
                    switch (button) {
                        case GLFW_MOUSE_BUTTON_RIGHT:
                            nkButton = NK_BUTTON_RIGHT;
                            break;
                        case GLFW_MOUSE_BUTTON_MIDDLE:
                            nkButton = NK_BUTTON_MIDDLE;
                            break;
                        default:
                            nkButton = NK_BUTTON_LEFT;
                    }
                    nk_input_button(nuklearUtils.getCtx(), nkButton, x, y, action == GLFW_PRESS);
                }
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };

        // Input handler for mouse scroll!
        mouseScroll = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double offsetX, double offsetY) {
                scrollX += offsetX;
                scrollY += offsetY;
                try (MemoryStack stack = stackPush()) {
                    NkVec2 scroll = NkVec2.malloc(stack)
                            .x((float)offsetX)
                            .y((float)offsetY);
                    nk_input_scroll(nuklearUtils.getCtx(), scroll);
                }
            }

        };

        //Setup clipboard for Nuklear
        nuklearUtils.getCtx().clip()
                .copy((handle, text, len) -> {
                    if (len == 0) {
                        return;
                    }

                    try (MemoryStack stack = stackPush()) {
                        ByteBuffer str = stack.malloc(len + 1);
                        memCopy(text, memAddress(str), len);
                        str.put(len, (byte)0);

                        glfwSetClipboardString(nuklearUtils.getWindow().getWindow(), str);
                    }
                })
                .paste((handle, edit) -> {
                    long text = nglfwGetClipboardString(nuklearUtils.getWindow().getWindow());
                    if (text != NULL) {
                        nnk_textedit_paste(edit, text, nnk_strlen(text));
                    }
                });
    }

    public static boolean isButtonDown(int button) {
        return buttons[button];
    }

    public static boolean isButtonUp(int button) {
        return !buttons[button];
    }

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

    public static boolean isKeyDown(int key) {
        return keys[key];
    }

    public static boolean isKeyUp(int key) {
        return !keys[key];
    }

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

    public void destroy() {
        keyboard.free();
        mouseMove.free();
        mouseButtons.free();
        mouseScroll.free();
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    public static double getScrollX() {
        return scrollX;
    }

    public static double getScrollY() {
        return scrollY;
    }

    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    public GLFWCursorPosCallback getMouseMoveCallback() {
        return mouseMove;
    }

    public GLFWMouseButtonCallback getMouseButtonsCallback() {
        return mouseButtons;
    }

    public GLFWScrollCallback getMouseScrollCallback() {
        return mouseScroll;
    }

    public GLFWCharCallback getCharCallback() {return charCallback;}
}