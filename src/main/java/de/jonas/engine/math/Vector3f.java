package de.jonas.engine.math;

import java.util.Objects;

/**
 * Represents a 3D vector with float components for X, Y, and Z.
 * This class provides fundamental vector operations such as addition, subtraction,
 * multiplication, division, normalization, dot product, and cross product,
 * essential for 3D graphics and physics.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
@SuppressWarnings("all")
public class Vector3f {
    //Variables
    private float x, y, z;

    //Constructors
    /**
     * Constructs a new Vector3f where all X, Y, and Z components are set to the same given value.
     *
     * @param value The float value to set for all three components (X, Y, and Z) of the vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f(float value) {
        this.x = value;
        this.y = value;
        this.z = value;
    }

    /**
     * Constructs a new Vector3f with specified X, Y, and Z components.
     *
     * @param x The float value for the X component of the vector.
     * @param y The float value for the Y component of the vector.
     * @param z The float value for the Z component of the vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //Getters
    /**
     * Returns the current Vector3f instance.
     * This can be useful for method chaining or explicit retrieval of the object itself.
     *
     * @return The current Vector3f object.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f get() {
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

    /**
     * Returns the Z component of this vector.
     *
     * @return The float value of the Z component.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public float getZ() {
        return this.z;
    }

    //Setters
    /**
     * Sets all three components (X, Y, and Z) of this vector to a single given value.
     *
     * @param value The float value to set for X, Y, and Z components.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void set(float value) {
        this.x = value;
        this.y = value;
        this.z = value;
    }

    /**
     * Sets the X, Y, and Z components of this vector to specific given values.
     *
     * @param x The float value to set for the X component.
     * @param y The float value to set for the Y component.
     * @param z The float value to set for the Z component.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    /**
     * Sets the Z component of this vector to a specific given value.
     *
     * @param z The float value to set for the Z component.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void setZ(float z) {
        this.z = z;
    }

    //Addition
    /**
     * Adds a single float value to all X, Y, and Z components of this vector.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @param value The float value to add to all three components.
     * @return The current Vector3f instance after addition.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f add(float value) {
        this.x += value;
        this.y += value;
        this.z += value;
        return this;
    }

    /**
     * Adds separate float values to the X, Y, and Z components of this vector.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @param valX The float value to add to the X component.
     * @param valY The float value to add to the Y component.
     * @param valZ The float value to add to the Z component.
     * @return The current Vector3f instance after addition.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f add(float valX, float valY, float valZ) {
        this.x += valX;
        this.y += valY;
        this.z += valZ;
        return this;
    }

    /**
     * Adds the components of another Vector3f to the components of this vector.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @param valVec The Vector3f whose components will be added to this vector.
     * @return The current Vector3f instance after addition.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f add(Vector3f valVec) {
        this.x += valVec.getX();
        this.y += valVec.getY();
        this.z += valVec.getZ();
        return this;
    }

    /**
     * Creates a new Vector3f that is the sum of two given Vector3f objects.
     * This is a static method and does not modify the input vectors.
     *
     * @param vector1 The first Vector3f to add.
     * @param vector2 The second Vector3f to add.
     * @return A new Vector3f representing the sum of `vector1` and `vector2`.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector3f add(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.getX() + vector2.getX(), vector1.getY() + vector2.getY(), vector1.getZ() + vector2.getZ());
    }

    //Subtraction
    /**
     * Subtracts a single float value from all X, Y, and Z components of this vector.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @param value The float value to subtract from all three components.
     * @return The current Vector3f instance after subtraction.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f sub(float value) {
        this.x -= value;
        this.y -= value;
        this.z -= value;
        return this;
    }

    /**
     * Subtracts separate float values from the X, Y, and Z components of this vector.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @param valX The float value to subtract from the X component.
     * @param valY The float value to subtract from the Y component.
     * @param valZ The float value to subtract from the Z component.
     * @return The current Vector3f instance after subtraction.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f sub(float valX, float valY, float valZ) {
        this.x -= valX;
        this.y -= valY;
        this.z -= valZ;
        return this;
    }

    /**
     * Subtracts the components of another Vector3f from the components of this vector.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @param valVec The Vector3f whose components will be subtracted from this vector.
     * @return The current Vector3f instance after subtraction.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f sub(Vector3f valVec) {
        this.x -= valVec.getX();
        this.y -= valVec.getY();
        this.z -= valVec.getZ();
        return this;
    }

    /**
     * Creates a new Vector3f that is the result of subtracting `vector2` from `vector1`.
     * This is a static method and does not modify the input vectors.
     *
     * @param vector1 The Vector3f to subtract from.
     * @param vector2 The Vector3f to subtract.
     * @return A new Vector3f representing the difference between `vector1` and `vector2`.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector3f sub(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.getX() - vector2.getX(), vector1.getY() - vector2.getY(), vector1.getZ() - vector2.getZ());
    }

    //Multiplication
    /**
     * Multiplies all X, Y, and Z components of this vector by a single float value.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @param value The float value to multiply all three components by.
     * @return The current Vector3f instance after multiplication.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f mult(float value) {
        this.x *= value;
        this.y *= value;
        this.z *= value;
        return this;
    }

    /**
     * Multiplies the X, Y, and Z components of this vector by separate float values.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @param valX The float value to multiply the X component by.
     * @param valY The float value to multiply the Y component by.
     * @param valZ The float value to multiply the Z component by.
     * @return The current Vector3f instance after multiplication.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f mult(float valX, float valY, float valZ) {
        this.x *= valX;
        this.y *= valY;
        this.z *= valZ;
        return this;
    }

    /**
     * Multiplies the components of this vector by the corresponding components of another Vector3f.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @param valVec The Vector3f whose components will be multiplied with this vector's components.
     * @return The current Vector3f instance after multiplication.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f mult(Vector3f valVec) {
        this.x *= valVec.getX();
        this.y *= valVec.getY();
        this.z *= valVec.getZ();
        return this;
    }

    /**
     * Creates a new Vector3f that is the component-wise product of two given Vector3f objects.
     * This is a static method and does not modify the input vectors.
     *
     * @param vector1 The first Vector3f to multiply.
     * @param vector2 The second Vector3f to multiply.
     * @return A new Vector3f representing the component-wise product of `vector1` and `vector2`.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector3f mult(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.getX() * vector2.getX(), vector1.getY() * vector2.getY(), vector1.getZ() * vector2.getZ());
    }

    //Division
    /**
     * Divides all X, Y, and Z components of this vector by a single float value.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @param value The float value to divide all three components by.
     * @return The current Vector3f instance after division.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f dev(float value) {
        this.x /= value;
        this.y /= value;
        this.z /= value;
        return this;
    }

    /**
     * Divides the X, Y, and Z components of this vector by separate float values.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @param valX The float value to divide the X component by.
     * @param valY The float value to divide the Y component by.
     * @param valZ The float value to divide the Z component by.
     * @return The current Vector3f instance after division.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f dev(float valX, float valY, float valZ) {
        this.x /= valX;
        this.y /= valY;
        this.z /= valZ;
        return this;
    }

    /**
     * Divides the components of this vector by the corresponding components of another Vector3f.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @param valVec The Vector3f whose components will divide this vector's components.
     * @return The current Vector3f instance after division.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f dev(Vector3f valVec) {
        this.x /= valVec.getX();
        this.y /= valVec.getY();
        this.z /= valVec.getZ();
        return this;
    }

    /**
     * Creates a new Vector3f that is the component-wise division of `vector1` by `vector2`.
     * This is a static method and does not modify the input vectors.
     *
     * @param vector1 The Vector3f to be divided.
     * @param vector2 The Vector3f to divide by.
     * @return A new Vector3f representing the component-wise division of `vector1` by `vector2`.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector3f dev(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.getX() / vector2.getX(), vector1.getY() / vector2.getY(), vector1.getZ() / vector2.getZ());
    }

    //Vector math
    /**
     * Normalizes this vector, converting it into a unit vector (a vector with a length of 1).
     * If the vector has a length of zero, it remains unchanged to prevent division by zero errors.
     * This method modifies the current vector and returns itself for chaining.
     *
     * @return The current Vector3f instance after normalization.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f normalize() {
        float length = length();
        // If the length is not zero, divide components by length; otherwise, return the original vector.
        return (length != 0) ? this.dev(length) : this;
    }

    /**
     * Calculates the dot product of this vector and another given Vector3f.
     * The dot product is a single float value that represents the scalar projection of one vector onto another.
     * It's useful for determining the angle between two vectors or if they are orthogonal.
     *
     * @param otherVec The other Vector3f to calculate the dot product with.
     * @return The float value of the dot product.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public float dotProduct(Vector3f otherVec) {
        return this.x * otherVec.getX() + this.y * otherVec.getY() + this.z * otherVec.getZ();
    }

    /**
     * Calculates the cross product of this vector and another given Vector3f.
     * The cross product results in a new vector that is perpendicular to both input vectors.
     * Its direction is determined by the right-hand rule.
     *
     * @param otherVec The other Vector3f to calculate the cross product with.
     * @return A new Vector3f representing the cross product of this vector and `otherVec`.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public Vector3f crossProduct(Vector3f otherVec) {
        return new Vector3f(
                this.y * otherVec.getZ() - this.z * otherVec.getY(),
                this.z * otherVec.getX() - this.x * otherVec.getZ(),
                this.x * otherVec.getY() - this.y * otherVec.getX()
        );
    }

    /**
     * Calculates the length (magnitude) of this vector.
     * The length is the square root of the sum of the squares of its X, Y, and Z components.
     *
     * @return The float value representing the length of this vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * Creates a new normalized Vector3f from a given vector, converting it into a unit vector (length of 1).
     * If the input vector has a length of zero, the original vector is returned to prevent division by zero errors.
     * This is a static method and does not modify the input vector.
     *
     * @param vector The Vector3f to normalize.
     * @return A new Vector3f representing the normalized version of the input vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector3f normalize(Vector3f vector) {
        float length = vector.length();
        return (length != 0) ? vector.dev(length) : vector;
    }

    /**
     * Calculates the dot product of two given Vector3f objects.
     * The dot product is a single float value representing the scalar projection of one vector onto another.
     * This is a static method and does not modify the input vectors.
     *
     * @param vector1 The first Vector3f for the dot product calculation.
     * @param vector2 The second Vector3f for the dot product calculation.
     * @return The float value of the dot product.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static float dotProduct(Vector3f vector1, Vector3f vector2) {
        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY() + vector1.getZ() * vector2.getZ();
    }

    /**
     * Calculates the cross product of two given Vector3f objects.
     * The cross product results in a new vector that is perpendicular to both input vectors.
     * Its direction is determined by the right-hand rule.
     * This is a static method and does not modify the input vectors.
     *
     * @param vector1 The first Vector3f for the cross product calculation.
     * @param vector2 The second Vector3f for the cross product calculation.
     * @return A new Vector3f representing the cross product of `vector1` and `vector2`.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Vector3f crossProduct(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(
                vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY(),
                vector1.getZ() * vector2.getX() - vector1.getX() * vector2.getZ(),
                vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX()
        );
    }

    /**
     * Calculates the length (magnitude) of a given Vector3f.
     * The length is the square root of the sum of the squares of its X, Y, and Z components.
     * This is a static method and does not modify the input vector.
     *
     * @param vector The Vector3f for which to calculate the length.
     * @return The float value representing the length of the given vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static float length(Vector3f vector) {
        return (float) Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY() + vector.getZ() * vector.getZ());
    }

    //Format out (String)
    /**
     * Returns a string representation of this Vector3f in the format "Vector3f(x, y, z)".
     *
     * @return A string representing the X, Y, and Z components of this vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    @Override
    public String toString() {
        return "Vector3f(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    /**
     * Returns a string representation of a given Vector3f in the format "Vector3f(x, y, z)".
     * This is a static method and does not modify the input vector.
     *
     * @param vector The Vector3f to convert to a string.
     * @return A string representing the X, Y, and Z components of the given vector.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static String toString(Vector3f vector) {
        return "Vector3f(" + vector.getX() + ", " + vector.getY() + ", " + vector.getZ() + ")";
    }

    //Hash map
    /**
     * Compares this Vector3f to the specified object. The result is true if and only if
     * the argument is not null and is a Vector3f object that has the same X, Y, and Z components
     * as this object, allowing for small floating-point inaccuracies.
     *
     * @param o The object to compare this Vector3f against.
     * @return True if the given object represents a Vector3f equivalent to this vector, false otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vector3f vector3f = (Vector3f) o;
        return Float.compare(x, vector3f.x) == 0 && Float.compare(y, vector3f.y) == 0 && Float.compare(z, vector3f.z) == 0;
    }

    /**
     * Returns a hash code value for this Vector3f.
     * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return A hash code value for this object.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}