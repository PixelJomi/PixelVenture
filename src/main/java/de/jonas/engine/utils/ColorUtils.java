package de.jonas.engine.utils;

import de.jonas.engine.math.Vector3f;

/**
 * A utility class providing methods for color format conversions and
 * constants for applying colors to console output.
 * It facilitates converting between different common color representations.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
public class ColorUtils {
    /**
     * Converts RGB color components from a 0-255 integer range to a 0.0-1.0 float range.
     * This format is commonly used in graphics APIs (e.g., OpenGL).
     *
     * @param r The red component, an integer between 0 and 255.
     * @param g The green component, an integer between 0 and 255.
     * @param b The blue component, an integer between 0 and 255.
     * @return A {@link Vector3f} where X, Y, and Z components represent the R, G, and B
     * values normalized to the 0.0-1.0 range, respectively.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector3f format01(int r, int g, int b) {
        return new Vector3f(r / 255f, g / 255f, b / 255f);
    }

    /**
     * Converts a color represented by a {@link Vector3f} (with components in the 0-255 range)
     * to a 0.0-1.0 float range. Each component of the input vector is divided by 255.0.
     *
     * @param color A {@link Vector3f} where X, Y, and Z components represent the R, G, and B
     * values, each expected to be in the 0-255 integer range.
     * @return A new {@link Vector3f} where X, Y, and Z components represent the R, G, and B
     * values normalized to the 0.0-1.0 range, respectively.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector3f format01(Vector3f color) {
        return new Vector3f(color.getX() / 255f, color.getY() / 255f, color.getZ() / 255f);
    }

    /**
     * Converts RGB color components from a 0.0-1.0 float range to a 0-255 integer range.
     * This is useful when preparing color values for display or APIs that expect integer RGB.
     *
     * @param r The red component, a float between 0.0 and 1.0.
     * @param g The green component, a float between 0.0 and 1.0.
     * @param b The blue component, a float between 0.0 and 1.0.
     * @return A {@link Vector3f} where X, Y, and Z components represent the R, G, and B
     * values scaled back to the 0-255 integer range, respectively.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector3f format255(int r, int g, int b) {
        return new Vector3f(r * 255f, g * 255f, b * 255f);
    }

    /**
     * Converts a color represented by a {@link Vector3f} (with components in the 0.0-1.0 range)
     * to a 0-255 integer range. Each component of the input vector is multiplied by 255.0.
     *
     * @param color A {@link Vector3f} where X, Y, and Z components represent the R, G, and B
     * values, each expected to be in the 0.0-1.0 float range.
     * @return A new {@link Vector3f} where X, Y, and Z components represent the R, G, and B
     * values scaled back to the 0-255 integer range, respectively.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector3f format255(Vector3f color) {
        return new Vector3f(color.getX() * 255f, color.getY() * 255f, color.getZ() * 255f);
    }

    /**
     * A nested utility class providing ANSI escape codes for coloring and styling console text.
     * These constants can be concatenated with strings to change text properties in compatible terminals.
     *
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static class consoleColor {
        /** Resets all text formatting to default. */
        public static final String RESET                    = "\033[0m";        // Text Reset
        /** Black foreground color. */
        public static final String BLACK                    = "\033[0;30m";     // BLACK
        /** Red foreground color. */
        public static final String RED                      = "\033[0;31m";     // RED
        /** Green foreground color. */
        public static final String GREEN                    = "\033[0;32m";     // GREEN
        /** Yellow foreground color. */
        public static final String YELLOW                   = "\033[0;33m";     // YELLOW
        /** Blue foreground color. */
        public static final String BLUE                     = "\033[0;34m";     // BLUE
        /** Purple foreground color. */
        public static final String PURPLE                   = "\033[0;35m";     // PURPLE
        /** Cyan foreground color. */
        public static final String CYAN                     = "\033[0;36m";     // CYAN
        /** White foreground color. */
        public static final String WHITE                    = "\033[0;37m";     // WHITE
        /** Bold black foreground color. */
        public static final String BLACK_BOLD               = "\033[1;30m";     // BLACK
        /** Bold red foreground color. */
        public static final String RED_BOLD                 = "\033[1;31m";     // RED
        /** Bold green foreground color. */
        public static final String GREEN_BOLD               = "\033[1;32m";     // GREEN
        /** Bold yellow foreground color. */
        public static final String YELLOW_BOLD              = "\033[1;33m";     // YELLOW
        /** Bold blue foreground color. */
        public static final String BLUE_BOLD                = "\033[1;34m";     // BLUE
        /** Bold purple foreground color. */
        public static final String PURPLE_BOLD              = "\033[1;35m";     // PURPLE
        /** Bold cyan foreground color. */
        public static final String CYAN_BOLD                = "\033[1;36m";     // CYAN
        /** Bold white foreground color. */
        public static final String WHITE_BOLD               = "\033[1;37m";     // WHITE
        /** Underlined black foreground color. */
        public static final String BLACK_UNDERLINED         = "\033[4;30m";     // BLACK
        /** Underlined red foreground color. */
        public static final String RED_UNDERLINED           = "\033[4;31m";     // RED
        /** Underlined green foreground color. */
        public static final String GREEN_UNDERLINED         = "\033[4;32m";     // GREEN
        /** Underlined yellow foreground color. */
        public static final String YELLOW_UNDERLINED        = "\033[4;33m";     // YELLOW
        /** Underlined blue foreground color. */
        public static final String BLUE_UNDERLINED          = "\033[4;34m";     // BLUE
        /** Underlined purple foreground color. */
        public static final String PURPLE_UNDERLINED        = "\033[4;35m";     // PURPLE
        /** Underlined cyan foreground color. */
        public static final String CYAN_UNDERLINED          = "\033[4;36m";     // CYAN
        /** Underlined white foreground color. */
        public static final String WHITE_UNDERLINED         = "\033[4;37m";     // WHITE
        /** Black background color. */
        public static final String BLACK_BACKGROUND         = "\033[40m";       // BLACK
        /** Red background color. */
        public static final String RED_BACKGROUND           = "\033[41m";       // RED
        /** Green background color. */
        public static final String GREEN_BACKGROUND         = "\033[42m";       // GREEN
        /** Yellow background color. */
        public static final String YELLOW_BACKGROUND        = "\033[43m";       // YELLOW
        /** Blue background color. */
        public static final String BLUE_BACKGROUND          = "\033[44m";       // BLUE
        /** Purple background color. */
        public static final String PURPLE_BACKGROUND        = "\033[45m";       // PURPLE
        /** Cyan background color. */
        public static final String CYAN_BACKGROUND          = "\033[46m";       // CYAN
        /** White background color. */
        public static final String WHITE_BACKGROUND         = "\033[47m";       // WHITE
        /** Bright black (gray) foreground color. */
        public static final String BLACK_BRIGHT             = "\033[0;90m";     // BLACK
        /** Bright red foreground color. */
        public static final String RED_BRIGHT               = "\033[0;91m";     // RED
        /** Bright green foreground color. */
        public static final String GREEN_BRIGHT             = "\033[0;92m";     // GREEN
        /** Bright yellow foreground color. */
        public static final String YELLOW_BRIGHT            = "\033[0;93m";     // YELLOW
        /** Bright blue foreground color. */
        public static final String BLUE_BRIGHT              = "\033[0;94m";     // BLUE
        /** Bright purple foreground color. */
        public static final String PURPLE_BRIGHT            = "\033[0;95m";     // PURPLE
        /** Bright cyan foreground color. */
        public static final String CYAN_BRIGHT              = "\033[0;96m";     // CYAN
        /** Bright white foreground color. */
        public static final String WHITE_BRIGHT             = "\033[0;97m";     // WHITE
        /** Bold bright black (gray) foreground color. */
        public static final String BLACK_BOLD_BRIGHT        = "\033[1;90m";     // BLACK
        /** Bold bright red foreground color. */
        public static final String RED_BOLD_BRIGHT          = "\033[1;91m";     // RED
        /** Bold bright green foreground color. */
        public static final String GREEN_BOLD_BRIGHT        = "\033[1;92m";     // GREEN
        /** Bold bright yellow foreground color. */
        public static final String YELLOW_BOLD_BRIGHT       = "\033[1;93m";     // YELLOW
        /** Bold bright blue foreground color. */
        public static final String BLUE_BOLD_BRIGHT         = "\033[1;94m";     // BLUE
        /** Bold bright purple foreground color. */
        public static final String PURPLE_BOLD_BRIGHT       = "\033[1;95m";     // PURPLE
        /** Bold bright cyan foreground color. */
        public static final String CYAN_BOLD_BRIGHT         = "\033[1;96m";     // CYAN
        /** Bold bright white foreground color. */
        public static final String WHITE_BOLD_BRIGHT        = "\033[1;97m";     // WHITE
        /** Bright black background color. */
        public static final String BLACK_BACKGROUND_BRIGHT  = "\033[0;100m";    // BLACK
        /** Bright red background color. */
        public static final String RED_BACKGROUND_BRIGHT    = "\033[0;101m";    // RED
        /** Bright green background color. */
        public static final String GREEN_BACKGROUND_BRIGHT  = "\033[0;102m";    // GREEN
        /** Bright yellow background color. */
        public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";    // YELLOW
        /** Bright blue background color. */
        public static final String BLUE_BACKGROUND_BRIGHT   = "\033[0;104m";    // BLUE
        /** Bright purple background color. */
        public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m";    // PURPLE
        /** Bright cyan background color. */
        public static final String CYAN_BACKGROUND_BRIGHT   = "\033[0;106m";    // CYAN
        /** Bright white background color. */
        public static final String WHITE_BACKGROUND_BRIGHT  = "\033[0;107m";    // WHITE
    }
}