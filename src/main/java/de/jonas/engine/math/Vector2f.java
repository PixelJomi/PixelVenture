package de.jonas.engine.math;

import java.util.Objects;

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
     * Adds all the current Vector2f given components to the given float resulting in an Vector2f!
     * @param value The float that will be added to every component of given Vector2f!
     * @return The result of the addition described above in form of a Vector2f!
     */
    public Vector2f add(float value) {
        this.x += value;
        this.y += value;
        return this;
    }

    /**
     * Adds the current Vector2f to 2 given x and y values!
     * @param valX The float added to the x component of the current vector!
     * @param valY The float added to the y component of the current vector!
     * @return The result of the addition of the current Vector2f and the x / y values in form of a Vector2f!
     */
    public Vector2f add(float valX,float valY) {
        this.x += valX;
        this.y += valY;
        return this;
    }

    /**
     * Adds the current Vector2f to 2 given components of the given Vector2f!
     * @param valVec The Vector2f added to the current Vector2f!
     * @return The result of the addition of the current Vector2f and the given Vector2f in form of a Vector2f!
     */
    public Vector2f add(Vector2f valVec) {
        this.x += valVec.getX();
        this.y += valVec.getY();
        return this;
    }

    /**
     * Adds the x component of vector1 to the x component of vector2 and the same with the y components, resulting in a new Vector2f!
     * @param vector1 The first Vector2f for the addition!
     * @param vector2 The second Vector2f for the addition!
     * @return Returns the result of the addition of vector1 and vector2 in form of a Vector2f!
     */
    public static Vector2f add(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() + vector2.getX(),vector1.getY() + vector2.getY());
    }

    //Subtraction

    /**
     * Subtracts all the current Vector2f given components from the given float resulting in an Vector2f!
     * @param value The float that will be subtracted from every component of given Vector2f!
     * @return The result of the subtraction described above in form of a Vector2f!
     */
    public Vector2f sub(float value) {
        this.x -= value;
        this.y -= value;
        return this;
    }

    /**
     * Subtracts the current Vector2f from 2 given x and y values!
     * @param valX The float subtracted from the x component of the current vector!
     * @param valY The float subtracted from the y component of the current vector!
     * @return The result of the subtraction of the current Vector2f with the x / y values in form of a Vector2f!
     */
    public Vector2f sub(float valX,float valY) {
        this.x -= valX;
        this.y -= valY;
        return this;
    }

    /**
     * Subtracts the current Vector2f from 2 given components of the given Vector2f!
     * @param valVec The Vector2f subtracted from the current Vector2f!
     * @return The result of the subtraction of the current Vector2f with the given Vector2f in form of a Vector2f!
     */
    public Vector2f sub(Vector2f valVec) {
        this.x -= valVec.getX();
        this.y -= valVec.getY();
        return this;
    }

    /**
     * Subtracts the x component of vector1 with the x component of vector2 and the same with the y components, resulting in a new Vector2f!
     * @param vector1 The first Vector2f for the subtraction!
     * @param vector2 The second Vector2f for the subtraction!
     * @return Returns the result of the subtraction of vector1 with vector2 in form of a Vector2f!
     */
    public static Vector2f sub(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() - vector2.getX(),vector1.getY() + vector2.getY());
    }

    //Multiplication

    // TODO: Add the javadoc comments for the last Vector2f Methods!

    public Vector2f mult(float value) {
        this.x *= value;
        this.y *= value;
        return this;
    }
    public Vector2f mult(float valX,float valY) {
        this.x *= valX;
        this.y *= valY;
        return this;
    }
    public Vector2f mult(Vector2f valVec) {
        this.x *= valVec.getX();
        this.y *= valVec.getY();
        return this;
    }
    public static Vector2f mult(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() * vector2.getX(),vector1.getY() * vector2.getY());
    }
    //Division
    public Vector2f dev(float value) {
        this.x /= value;
        this.y /= value;
        return this;
    }
    public Vector2f dev(float valX,float valY) {
        this.x /= valX;
        this.y /= valY;
        return this;
    }
    public Vector2f dev(Vector2f valVec) {
        this.x /= valVec.getX();
        this.y /= valVec.getY();
        return this;
    }
    public static Vector2f dev(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() / vector2.getX(),vector1.getY() / vector2.getY());
    }
    //Vector math
    public Vector2f normalize() {
        float length = length();
        //If true then output: "this.dev(length)" if false then output: "this"
        return (length != 0) ? this.dev(length) : this;
    }
    public float dotProduct(Vector2f otherVec) {
        return this.x * otherVec.getX() + this.y * otherVec.getY();
    }
    public Vector2f crossProduct(Vector2f otherVec) {
        return new Vector2f(
                this.x * otherVec.getY() - this.y * otherVec.getX(),
                this.y * otherVec.getX() - this.x * otherVec.getY()
        );
    }
    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }
    public static Vector2f normalize(Vector2f vector) {
        float length = vector.length();
        return (length != 0) ? vector.dev(length) : vector;
    }
    public static float dotProduct(Vector2f vector1, Vector2f vector2) {
        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY();
    }
    public static Vector2f crossProduct(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(
                vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX(),
                vector1.getY() * vector2.getX() - vector1.getX() * vector2.getY()
        );
    }
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
