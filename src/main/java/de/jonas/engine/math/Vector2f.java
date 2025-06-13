package de.jonas.engine.math;

import java.util.Objects;

/**
 * Represents a 2D vector with float components for X and Y.
 * This class provides basic vector operations such as addition, subtraction,
 * multiplication, division, normalization, dot product, and cross product.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
public class Vector2f {
    //Variables
    private float x, y;

    //Constructors
    /**
     * Constructs a new Vector2f where both X and Y components are set to the same given value.
     *
     * @param value The float value to set for both the X and Y components of the vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f(float value) {
        this.x = value;
        this.y = value;
    }

    /**
     * Constructs a new Vector2f with specified X and Y components.
     *
     * @param x The float value for the X component of the vector.
     * @param y The float value for the Y component of the vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    //Getters
    /**
     * Returns the current Vector2f instance.
     * This can be useful for method chaining or explicit retrieval.
     *
     * @return The current Vector2f object.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f get() {
        return this;
    }

    /**
     * Returns the X component of this vector.
     *
     * @return The float value of the X component.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public float getX() {
        return this.x;
    }

    /**
     * Returns the Y component of this vector.
     *
     * @return The float value of the Y component.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public float getY() {
        return this.y;
    }

    //Setters
    /**
     * Sets both the X and Y components of this vector to a single given value.
     *
     * @param value The float value to set for both X and Y components.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void set(float value) {
        this.x = value;
        this.y = value;
    }

    /**
     * Sets the X and Y components of this vector to specific given values.
     *
     * @param x The float value to set for the X component.
     * @param y The float value to set for the Y component.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the X component of this vector to a specific given value.
     *
     * @param x The float value to set for the X component.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Sets the Y component of this vector to a specific given value.
     *
     * @param y The float value to set for the Y component.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void setY(float y) {
        this.y = y;
    }

    //Addition

    /**
     * Adds a single float value to both the X and Y components of this vector.
     * This method modifies the current vector and returns itself.
     *
     * @param value The float value to add to both X and Y components.
     * @return The current Vector2f instance after addition.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f add(float value) {
        this.x += value;
        this.y += value;
        return this;
    }

    /**
     * Adds separate float values to the X and Y components of this vector.
     * This method modifies the current vector and returns itself.
     *
     * @param valX The float value to add to the X component.
     * @param valY The float value to add to the Y component.
     * @return The current Vector2f instance after addition.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f add(float valX, float valY) {
        this.x += valX;
        this.y += valY;
        return this;
    }

    /**
     * Adds the components of another Vector2f to the components of this vector.
     * This method modifies the current vector and returns itself.
     *
     * @param valVec The Vector2f whose components will be added to this vector.
     * @return The current Vector2f instance after addition.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f add(Vector2f valVec) {
        this.x += valVec.getX();
        this.y += valVec.getY();
        return this;
    }

    /**
     * Creates a new Vector2f that is the sum of two given Vector2f objects.
     * This is a static method and does not modify the input vectors.
     *
     * @param vector1 The first Vector2f to add.
     * @param vector2 The second Vector2f to add.
     * @return A new Vector2f representing the sum of `vector1` and `vector2`.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector2f add(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() + vector2.getX(), vector1.getY() + vector2.getY());
    }

    //Subtraction

    /**
     * Subtracts a single float value from both the X and Y components of this vector.
     * This method modifies the current vector and returns itself.
     *
     * @param value The float value to subtract from both X and Y components.
     * @return The current Vector2f instance after subtraction.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f sub(float value) {
        this.x -= value;
        this.y -= value;
        return this;
    }

    /**
     * Subtracts separate float values from the X and Y components of this vector.
     * This method modifies the current vector and returns itself.
     *
     * @param valX The float value to subtract from the X component.
     * @param valY The float value to subtract from the Y component.
     * @return The current Vector2f instance after subtraction.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f sub(float valX, float valY) {
        this.x -= valX;
        this.y -= valY;
        return this;
    }

    /**
     * Subtracts the components of another Vector2f from the components of this vector.
     * This method modifies the current vector and returns itself.
     *
     * @param valVec The Vector2f whose components will be subtracted from this vector.
     * @return The current Vector2f instance after subtraction.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f sub(Vector2f valVec) {
        this.x -= valVec.getX();
        this.y -= valVec.getY();
        return this;
    }

    /**
     * Creates a new Vector2f that is the result of subtracting `vector2` from `vector1`.
     * This is a static method and does not modify the input vectors.
     *
     * @param vector1 The Vector2f to subtract from.
     * @param vector2 The Vector2f to subtract.
     * @return A new Vector2f representing the difference between `vector1` and `vector2`.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector2f sub(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() - vector2.getX(), vector1.getY() - vector2.getY());
    }

    //Multiplication

    /**
     * Multiplies both the X and Y components of this vector by a single float value.
     * This method modifies the current vector and returns itself.
     *
     * @param value The float value to multiply both X and Y components by.
     * @return The current Vector2f instance after multiplication.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f mult(float value) {
        this.x *= value;
        this.y *= value;
        return this;
    }

    /**
     * Multiplies the X and Y components of this vector by separate float values.
     * This method modifies the current vector and returns itself.
     *
     * @param valX The float value to multiply the X component by.
     * @param valY The float value to multiply the Y component by.
     * @return The current Vector2f instance after multiplication.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f mult(float valX, float valY) {
        this.x *= valX;
        this.y *= valY;
        return this;
    }

    /**
     * Multiplies the components of this vector by the corresponding components of another Vector2f.
     * This method modifies the current vector and returns itself.
     *
     * @param valVec The Vector2f whose components will be multiplied with this vector's components.
     * @return The current Vector2f instance after multiplication.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f mult(Vector2f valVec) {
        this.x *= valVec.getX();
        this.y *= valVec.getY();
        return this;
    }

    /**
     * Creates a new Vector2f that is the component-wise product of two given Vector2f objects.
     * This is a static method and does not modify the input vectors.
     *
     * @param vector1 The first Vector2f to multiply.
     * @param vector2 The second Vector2f to multiply.
     * @return A new Vector2f representing the component-wise product of `vector1` and `vector2`.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector2f mult(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() * vector2.getX(), vector1.getY() * vector2.getY());
    }

    //Division

    /**
     * Divides both the X and Y components of this vector by a single float value.
     * This method modifies the current vector and returns itself.
     *
     * @param value The float value to divide both X and Y components by.
     * @return The current Vector2f instance after division.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f dev(float value) {
        this.x /= value;
        this.y /= value;
        return this;
    }

    /**
     * Divides the X and Y components of this vector by separate float values.
     * This method modifies the current vector and returns itself.
     *
     * @param valX The float value to divide the X component by.
     * @param valY The float value to divide the Y component by.
     * @return The current Vector2f instance after division.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f dev(float valX, float valY) {
        this.x /= valX;
        this.y /= valY;
        return this;
    }

    /**
     * Divides the components of this vector by the corresponding components of another Vector2f.
     * This method modifies the current vector and returns itself.
     *
     * @param valVec The Vector2f whose components will divide this vector's components.
     * @return The current Vector2f instance after division.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f dev(Vector2f valVec) {
        this.x /= valVec.getX();
        this.y /= valVec.getY();
        return this;
    }

    /**
     * Creates a new Vector2f that is the component-wise division of `vector1` by `vector2`.
     * This is a static method and does not modify the input vectors.
     *
     * @param vector1 The Vector2f to be divided.
     * @param vector2 The Vector2f to divide by.
     * @return A new Vector2f representing the component-wise division of `vector1` by `vector2`.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector2f dev(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() / vector2.getX(), vector1.getY() / vector2.getY());
    }

    //Vector math

    /**
     * Normalizes this vector, converting it into a unit vector (length of 1).
     * If the vector has a length of zero, it remains unchanged to avoid division by zero.
     * This method modifies the current vector and returns itself.
     *
     * @return The current Vector2f instance after normalization.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f normalize() {
        float length = length();
        return (length != 0) ? this.dev(length) : this;
    }

    /**
     * Calculates the dot product of this vector and another given Vector2f.
     * The dot product is a single float value representing the scalar projection of one vector onto another.
     *
     * @param otherVec The other Vector2f to calculate the dot product with.
     * @return The float value of the dot product.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public float dotProduct(Vector2f otherVec) {
        return this.x * otherVec.getX() + this.y * otherVec.getY();
    }

    /**
     * Calculates the cross product of this vector and another given Vector2f.
     * For 2D vectors, the "cross product" is a scalar value representing the magnitude of the 3D cross product if
     * the vectors were extended to 3D with a Z-component of 0.
     * This method returns a new Vector2f for consistency, where the Z-component would be the scalar result.
     *
     * @param otherVec The other Vector2f to calculate the cross product with.
     * @return A new Vector2f representing the cross product, where the X and Y components might represent the scalar result.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector2f crossProduct(Vector2f otherVec) {
        // In 2D, the cross product usually returns a scalar (the Z component of the 3D cross product).
        // Returning a Vector2f here might imply extending the concept.
        // The standard 2D cross product (magnitude) would be (this.x * otherVec.getY() - this.y * otherVec.getX())
        // The current implementation seems to return a vector where components are derived from this calculation.
        // It's good to clarify the intended use of the returned Vector2f.
        return new Vector2f(
                this.x * otherVec.getY() - this.y * otherVec.getX(),
                this.y * otherVec.getX() - this.x * otherVec.getY()
        );
    }

    /**
     * Calculates the length (magnitude) of this vector.
     * The length is the square root of the sum of the squares of its components.
     *
     * @return The float value representing the length of this vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Creates a new normalized Vector2f from a given vector, converting it into a unit vector (length of 1).
     * If the input vector has a length of zero, the original vector is returned to avoid division by zero.
     * This is a static method and does not modify the input vector.
     *
     * @param vector The Vector2f to normalize.
     * @return A new Vector2f representing the normalized version of the input vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector2f normalize(Vector2f vector) {
        float length = vector.length();
        return (length != 0) ? vector.dev(length) : vector;
    }

    /**
     * Calculates the dot product of two given Vector2f objects.
     * The dot product is a single float value representing the scalar projection of one vector onto another.
     * This is a static method and does not modify the input vectors.
     *
     * @param vector1 The first Vector2f for the dot product calculation.
     * @param vector2 The second Vector2f for the dot product calculation.
     * @return The float value of the dot product.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static float dotProduct(Vector2f vector1, Vector2f vector2) {
        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY();
    }

    /**
     * Calculates the cross product of two given Vector2f objects.
     * For 2D vectors, the "cross product" is a scalar value representing the magnitude of the 3D cross product if
     * the vectors were extended to 3D with a Z-component of 0.
     * This static method returns a new Vector2f for consistency, where the X and Y components might represent the scalar result.
     *
     * @param vector1 The first Vector2f for the cross product calculation.
     * @param vector2 The second Vector2f for the cross product calculation.
     * @return A new Vector2f representing the cross product, where the X and Y components might represent the scalar result.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector2f crossProduct(Vector2f vector1, Vector2f vector2) {
        // As in the instance method, this 2D cross product usually yields a scalar.
        // The return of a Vector2f here implies a specific interpretation of the result.
        return new Vector2f(
                vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX(),
                vector1.getY() * vector2.getX() - vector1.getX() * vector2.getY()
        );
    }

    /**
     * Calculates the length (magnitude) of a given Vector2f.
     * The length is the square root of the sum of the squares of its components.
     * This is a static method and does not modify the input vector.
     *
     * @param vector The Vector2f for which to calculate the length.
     * @return The float value representing the length of the given vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static float length(Vector2f vector) {
        return (float) Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
    }

    //Format out (String)

    /**
     * Returns a string representation of this Vector2f in the format "Vector2f(x, y)".
     *
     * @return A string representing the X and Y components of this vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    @Override
    public String toString() {
        return "Vector2f(" + this.x + ", " + this.y + ")";
    }

    /**
     * Returns a string representation of a given Vector2f in the format "Vector2f(x, y)".
     * This is a static method and does not modify the input vector.
     *
     * @param vector The Vector2f to convert to a string.
     * @return A string representing the X and Y components of the given vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static String toString(Vector2f vector) {
        return "Vector2f(" + vector.getX() + ", " + vector.getY() + ")";
    }

    //Hash map
    /**
     * Compares this Vector2f to the specified object. The result is true if and only if
     * the argument is not null and is a Vector2f object that has the same X and Y components
     * as this object, allowing for small floating-point inaccuracies.
     *
     * @param o The object to compare this Vector2f against.
     * @return True if the given object represents a Vector2f equivalent to this vector, false otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vector2f vector2f = (Vector2f) o;
        return Float.compare(x, vector2f.x) == 0 && Float.compare(y, vector2f.y) == 0;
    }

    /**
     * Returns a hash code value for this Vector2f.
     * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return A hash code value for this object.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}