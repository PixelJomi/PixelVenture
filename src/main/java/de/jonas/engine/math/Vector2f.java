package de.jonas.engine.math;

import java.util.Objects;

/**
 * @author PixelJomi / Jomi_Craft / Jonas
 */
public class Vector2f {
    //Variables
    private float x, y;
    //Constructors
    public Vector2f(float value) {
        this.x = value;
        this.y = value;
    }
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    //Getters
    public Vector2f get() {
        return this;
    }
    public float getX() {return this.x;}
    public float getY() {return this.y;}
    //Setters
    public void set(float value) {
        this.x = value;
        this.y = value;
    }
    public void set(float x,float y) {
        this.x = x;
        this.y = y;
    }
    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}

    //Addition

    /**
     * Adds all the current Vector2f given components to the given float!
     * @param value float | Will be added to every component of given Vector2f!
     * @return Vector2f | The result of the addition described above!
     */
    public Vector2f add(float value) {
        this.x += value;
        this.y += value;
        return this;
    }

    /**
     * Adds the current Vector2f x and y components to the given x and y values respectively!
     * @param valX float | Will be added to the x component of the current vector!
     * @param valY float | Will be added to the y component of the current vector!
     * @return Vector2f | The result of the addition of the current Vector2f x/y components and the x/y values given!
     */
    public Vector2f add(float valX,float valY) {
        this.x += valX;
        this.y += valY;
        return this;
    }

    /**
     * Adds the current Vector2f to the given Vector2f!
     * @param valVec Vector2f | Will be added to the current Vector2f!
     * @return Vector2f | The result of the addition of the current Vector2f and the given Vector2f!
     */
    public Vector2f add(Vector2f valVec) {
        this.x += valVec.getX();
        this.y += valVec.getY();
        return this;
    }

    /**
     * Adds vector1 to vector2!
     * @param vector1 float | Will be added with "vector2"!
     * @param vector2 float | Will be added to "vector1"!
     * @return Vector2f | The result of the addition of vector1 and vector2!
     */
    public static Vector2f add(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() + vector2.getX(),vector1.getY() + vector2.getY());
    }

    //Subtraction

    /**
     * Subtracts all the current Vector2f given components with the given float!
     * @param value float | Will be subtracted from every component of given Vector2f!
     * @return Vector2f | The result of the subtraction described above!
     */
    public Vector2f sub(float value) {
        this.x -= value;
        this.y -= value;
        return this;
    }

    /**
     * Subtracts the current Vector2f x and y components with the given x and y values respectively!
     * @param valX float | Will be subtracted from the x component of the current vector!
     * @param valY float | Will be subtracted from the y component of the current vector!
     * @return Vector2f | The result of the subtraction of the current Vector2f x/y components and the x/y values given!
     */
    public Vector2f sub(float valX,float valY) {
        this.x -= valX;
        this.y -= valY;
        return this;
    }

    /**
     * Subtracts the current Vector2f with the given Vector2f!
     * @param valVec Vector2f | Will be subtracted from the current Vector2f!
     * @return Vector2f | The result of the subtraction of the current Vector2f and the given Vector2f!
     */
    public Vector2f sub(Vector2f valVec) {
        this.x -= valVec.getX();
        this.y -= valVec.getY();
        return this;
    }

    /**
     * Subtracts vector1 with vector2!
     * @param vector1 float | Will be subtracted with "vector2"!
     * @param vector2 float | Will be subtracted from "vector1"!
     * @return Vector2f | The result of the subtraction of vector1 and vector2!
     */
    public static Vector2f sub(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() - vector2.getX(),vector1.getY() + vector2.getY());
    }

    //Multiplication

    /**
     * Multiplies all the current Vector2f given components with the given float!
     * @param value float | Will be multiplied with every component of given Vector2f!
     * @return Vector2f | The result of the multiplication described above!
     */
    public Vector2f mult(float value) {
        this.x *= value;
        this.y *= value;
        return this;
    }

    /**
     * Multiplies the current Vector2f x and y components with the given x and y values respectively!
     * @param valX float | Will be multiplied with the x component of the current vector!
     * @param valY float | Will be multiplied with the y component of the current vector!
     * @return Vector2f | The result of the multiplication of the current Vector2f x/y components with the x/y values given!
     */
    public Vector2f mult(float valX,float valY) {
        this.x *= valX;
        this.y *= valY;
        return this;
    }

    /**
     * Multiplies the current Vector2f with the given Vector2f!
     * @param valVec Vector2f | Will be multiplied with the current Vector2f!
     * @return Vector2f | The result of the multiplication of the current Vector2f with the given Vector2f!
     */
    public Vector2f mult(Vector2f valVec) {
        this.x *= valVec.getX();
        this.y *= valVec.getY();
        return this;
    }

    /**
     * Multiplies vector1 with vector2!
     * @param vector1 float | Will be multiplied with "vector2"!
     * @param vector2 float | Will be multiplied with "vector1"!
     * @return Vector2f | The result of the multiplication of vector1 with vector2!
     */
    public static Vector2f mult(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() * vector2.getX(),vector1.getY() * vector2.getY());
    }

    //Division

    /**
     * Divides all the current Vector2f given components by the given float!
     * @param value float | Each component will be divided by the value.
     * @return Vector2f | The result of the division described above!
     */
    public Vector2f dev(float value) {
        this.x /= value;
        this.y /= value;
        return this;
    }

    /**
     * Divides the current Vector2f x and y components by the given x and y values respectively!
     * @param valX float | The x component of current Vector2f will be divided by it!
     * @param valY float | The y component of current Vector2f will be divided by it!
     * @return Vector2f | The result of the division of the current Vector2f x/y components by the x/y values given!
     */
    public Vector2f dev(float valX,float valY) {
        this.x /= valX;
        this.y /= valY;
        return this;
    }

    /**
     * Divides the current Vector2f by the given Vector2f!
     * @param valVec Vector2f | The current Vector2f will be divided by it!
     * @return Vector2f | The result of the division of the current Vector2f by the given Vector2f!
     */
    public Vector2f dev(Vector2f valVec) {
        this.x /= valVec.getX();
        this.y /= valVec.getY();
        return this;
    }

    /**
     * Divides vector1 by vector2!
     * @param vector1 float | Will be divided by "vector2"!
     * @param vector2 float | Will be divided by "vector1"!
     * @return Vector2f | The result of the division of vector1 by vector2!
     */
    public static Vector2f dev(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() / vector2.getX(),vector1.getY() / vector2.getY());
    }

    //Vector math

    /**
     * Normalizes the current Vector2f!
     * @return Vector2f | The normalized current Vector2f!
     */
    public Vector2f normalize() {
        float length = length();
        //If true then output: "this.dev(length)" if false then output: "this"
        return (length != 0) ? this.dev(length) : this;
    }

    /**
     * Calculates the dot product of the current Vector2f and the given Vector2f!
     * @param otherVec Vector2f | The second Vector2f for the dot product calculation!
     * @return float | The dot product of current Vector2f and the given Vector2f!
     */
    public float dotProduct(Vector2f otherVec) {
        return this.x * otherVec.getX() + this.y * otherVec.getY();
    }

    /**
     * Calculates the cross product of the current Vector2f and the given Vector2f!
     * @param otherVec Vector2f | The second Vector2f for the cross product calculation!
     * @return Vector2f | The cross product of the current Vector2f and the given Vector2f!
     */
    public Vector2f crossProduct(Vector2f otherVec) {
        return new Vector2f(
                this.x * otherVec.getY() - this.y * otherVec.getX(),
                this.y * otherVec.getX() - this.x * otherVec.getY()
        );
    }

    /**
     * Calculates the length of the current Vector2f!
     * @return float | The length of the current Vector2f!
     */
    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Normalizes the given Vector2f!
     * @return Vector2f | The normalized given Vector2f!
     */
    public static Vector2f normalize(Vector2f vector) {
        float length = vector.length();
        return (length != 0) ? vector.dev(length) : vector;
    }

    /**
     * Calculates the dot product of vector1 and vector2!
     * @param vector1 Vector2f | The first Vector2f for the dot product calculation!
     * @param vector2 Vector2f | The second Vector2f for the dot product calculation!
     * @return float | The dot product of vector1 and vector2!
     */
    public static float dotProduct(Vector2f vector1, Vector2f vector2) {
        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY();
    }

    /**
     * Calculates the cross product of vector1 and vector2!
     * @param vector1 Vector2f | The first Vector2f for the cross product calculation!
     * @param vector2 Vector2f | The second Vector2f for the cross product calculation!
     * @return float | The cross product of vector1 and vector2!
     */
    public static Vector2f crossProduct(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(
                vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX(),
                vector1.getY() * vector2.getX() - vector1.getX() * vector2.getY()
        );
    }

    /**
     * Calculates the length of the given Vector2f!
     * @return float | The length of the given Vector2f!
     */
    public static float length(Vector2f vector) {
        return (float) Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
    }

    //Format out (String)

    @Override
    public String toString() {
        return "Vector2f(" + this.x + ", " + this.y + ")";
    }
    public static String toString(Vector2f vector) {
        return "Vector2f(" + vector.getX() + ", " + vector.getY() + ")";
    }
    //Hash map
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vector2f vector2f = (Vector2f) o;
        return Float.compare(x, vector2f.x) == 0 && Float.compare(y, vector2f.y) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
