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
    public Vector2f add(float value) {
        this.x += value;
        this.y += value;
        return this;
    }
    public Vector2f add(float valX,float valY) {
        this.x += valX;
        this.y += valY;
        return this;
    }
    public Vector2f add(Vector2f valVec) {
        this.x += valVec.getX();
        this.y += valVec.getY();
        return this;
    }
    public static Vector2f add(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() + vector2.getX(),vector1.getY() + vector2.getY());
    }
    //Subtraction
    public Vector2f sub(float value) {
        this.x -= value;
        this.y -= value;
        return this;
    }
    public Vector2f sub(float valX,float valY) {
        this.x -= valX;
        this.y -= valY;
        return this;
    }
    public Vector2f sub(Vector2f valVec) {
        this.x -= valVec.getX();
        this.y -= valVec.getY();
        return this;
    }
    public static Vector2f sub(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() - vector2.getX(),vector1.getY() + vector2.getY());
    }
    //Multiplication
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
