package de.jonas.engine.data;

import de.jonas.engine.math.Vector3f;
import de.jonas.engine.utils.ColorUtils;

public class PVData {

    public static final Vector3f DEFAULT_BACKGROUND_COLOR = ColorUtils.format01(21,21,23);
    public static final Vector3f DEFAULT_VERTEX_COLOR = new Vector3f(0.247f,0.227f,0.259f);
    public static final String GAME_NAME = "Pixel Venture";
    public static final int SECTION_SIZE = 16;
    public static final int CHUNK_SECTION_AMOUNT = 16;
}
