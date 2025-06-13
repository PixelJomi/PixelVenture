package de.jonas.engine.graphics;

import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;

/**
 * Represents a single vertex in 3D space, encapsulating its position and texture coordinates.
 * This class is fundamental for defining the geometry and appearance of objects rendered in the engine.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
public class Vertex {
    /**
     * The 3D position of the vertex (X, Y, Z coordinates).
     */
    private Vector3f pos;
    /**
     * The 2D texture coordinates of the vertex (U, V coordinates).
     */
    private Vector2f textureCord;

    /**
     * Constructs a new {@code Vertex} with the specified position and texture coordinates.
     *
     * @param pos         A {@link Vector3f} representing the 3D position of the vertex.
     * @param textureCord A {@link Vector2f} representing the 2D texture coordinates of the vertex.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vertex(Vector3f pos, Vector2f textureCord) {
        this.pos = pos;
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
     * Sets the 2D texture coordinates of this vertex.
     *
     * @param textureCord The new {@link Vector2f} for the texture coordinates.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void setTextureCord(Vector2f textureCord) {
        this.textureCord = textureCord;
    }
}