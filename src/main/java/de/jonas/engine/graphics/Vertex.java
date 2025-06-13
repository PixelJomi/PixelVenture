package de.jonas.engine.graphics;

import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;

/**
 * Represents a single vertex in 3D space, encapsulating its position, color,
 * and texture coordinates. This class is fundamental for defining the geometry
 * and appearance of objects rendered in the engine.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
public class Vertex {
    /**
     * The 3D position of the vertex (X, Y, Z coordinates).
     */
    private Vector3f pos;
    /**
     * The color of the vertex in RGB format (0.0-1.0 for R, G, B components).
     */
    private Vector3f color;
    /**
     * The 2D texture coordinates of the vertex (U, V coordinates).
     */
    private Vector2f textureCord;

    /**
     * Constructs a new {@code Vertex} with the specified position, color, and texture coordinates.
     *
     * @param pos         A {@link Vector3f} representing the 3D position of the vertex.
     * @param color       A {@link Vector3f} representing the RGB color of the vertex.
     * @param textureCord A {@link Vector2f} representing the 2D texture coordinates of the vertex.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vertex(Vector3f pos, Vector3f color, Vector2f textureCord) {
        this.pos = pos;
        this.color = color;
        this.textureCord = textureCord;
    }

    /**
     * Retrieves the 3D position of this vertex.
     *
     * @return A {@link Vector3f} representing the position.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f getPos() {
        return pos;
    }

    /**
     * Retrieves the color of this vertex.
     *
     * @return A {@link Vector3f} representing the RGB color.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f getColor() {
        return color;
    }

    /**
     * Retrieves the 2D texture coordinates of this vertex.
     *
     * @return A {@link Vector2f} representing the texture coordinates.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f getTextureCord() {
        return textureCord;
    }

    /**
     * Sets the 3D position of this vertex.
     *
     * @param pos The new {@link Vector3f} for the position.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    /**
     * Sets the color of this vertex.
     *
     * @param color The new {@link Vector3f} for the RGB color.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void setColor(Vector3f color) {
        this.color = color;
    }

    /**
     * Sets the 2D texture coordinates of this vertex.
     *
     * @param textureCord The new {@link Vector2f} for the texture coordinates.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void setTextureCord(Vector2f textureCord) {
        this.textureCord = textureCord;
    }
}