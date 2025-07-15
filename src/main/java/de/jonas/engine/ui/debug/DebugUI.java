package de.jonas.engine.ui.debug;

import de.jonas.engine.utils.Console;
import de.jonas.main.Main;
import org.lwjgl.BufferUtils;
import org.lwjgl.nuklear.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.nuklear.Nuklear.*;
import static org.lwjgl.system.MemoryStack.*;

public class DebugUI {

    public NkColorf background = NkColorf.create()
            .r(0.10f)
            .g(0.18f)
            .b(0.24f)
            .a(1.0f);

    public boolean wireframe = false;

    private final IntBuffer compression = BufferUtils.createIntBuffer(1).put(0, 20);

    public DebugUI() {
    }

    public void layout(NkContext ctx, int x, int y, Main main) {
        try (MemoryStack stack = stackPush()) {
            NkRect rect = NkRect.malloc(stack);

            if (nk_begin(
                    ctx,
                    "Render",
                    nk_rect(x, y, 350, 250, rect),
                    NK_WINDOW_BORDER | NK_WINDOW_MOVABLE | NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_TITLE
            )) {
                nk_layout_row_static(ctx, 30, 80, 1);
                if (nk_button_label(ctx, "ReloadChunks")) {
                    Console.printDebug("TestButton pressed!", true);
                }

                nk_layout_row_dynamic(ctx, 30, 3);
                nk_label(ctx, "RenderMode: ", NK_TEXT_LEFT);
                if (nk_option_label(ctx, "Normal", !wireframe)) {
                    wireframe = false;
                }
                if (nk_option_label(ctx, "Wireframe", wireframe)) {
                    wireframe = true;
                }
            }

            nk_end(ctx);
        }
    }
}
