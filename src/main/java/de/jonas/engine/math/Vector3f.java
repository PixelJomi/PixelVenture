package de.jonas.engine.math;

@SuppressWarnings("all") public class Vector3f {
    //Variables
    private float x, y, z;
    //Constructors
    public Vector3f(float value) {
        this.x = value;
        this.y = value;
        this.z = value;
    }
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    //Getters
    public Vector3f get() {
        return this;
    }
    public float getX() {return x;}
    public float getY() {return y;}
    public float getZ() {return z;}
    //Setters
    public void set(float value) {
        this.x = value;
        this.y = value;
        this.z = value;
    }
    public void set(float x,float y,float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}
    public void setZ(float z) {this.z = z;}
    //Addition
    public Vector3f add(float value) {
        this.x += value;
        this.y += value;
        this.z += value;
        return this;
    }
    public Vector3f add(float valX,float valY, float valZ) {
        this.x += valX;
        this.y += valY;
        this.z += valZ;
        return this;
    }
    public Vector3f add(Vector3f valVec) {
        this.x += valVec.x;
        this.y += valVec.y;
        this.z += valVec.z;
        return this;
    }
    //Subtraction
    public Vector3f sub(float value) {
        this.x -= value;
        this.y -= value;
        this.z -= value;
        return this;
    }
    public Vector3f sub(float valX,float valY, float valZ) {
        this.x -= valX;
        this.y -= valY;
        this.z -= valZ;
        return this;
    }
    public Vector3f sub(Vector3f valVec) {
        this.x -= valVec.x;
        this.y -= valVec.y;
        this.z -= valVec.z;
        return this;
    }
    //Multiplication
    public Vector3f mult(float value) {
        this.x *= value;
        this.y *= value;
        this.z *= value;
        return this;
    }
    public Vector3f mult(float valX,float valY, float valZ) {
        this.x *= valX;
        this.y *= valY;
        this.z *= valZ;
        return this;
    }
    public Vector3f mult(Vector3f valVec) {
        this.x *= valVec.x;
        this.y *= valVec.y;
        this.z *= valVec.z;
        return this;
    }
    //Division
    public Vector3f dev(float value) {
        this.x /= value;
        this.y /= value;
        this.z /= value;
        return this;
    }
    public Vector3f dev(float valX,float valY, float valZ) {
        this.x /= valX;
        this.y /= valY;
        this.z /= valZ;
        return this;
    }
    public Vector3f dev(Vector3f valVec) {
        this.x /= valVec.x;
        this.y /= valVec.y;
        this.z /= valVec.z;
        return this;
    }
    //Vector math
    public Vector3f normalize() {
        float length = length();
        //If true then output: "this.dev(length)" if false then output: "this"
        return (length != 0) ? this.dev(length) : this;
    }
    public float dotProduct(Vector3f otherVec) {
        return this.x * otherVec.x + this.y * otherVec.y + this.z * otherVec.z;
    }
    public Vector3f crossProduct(Vector3f otherVec) {
        return new Vector3f(
                this.y * otherVec.z - this.z * otherVec.y,
                this.z * otherVec.x - this.x * otherVec.z,
                this.x * otherVec.y - this.y * otherVec.x
        );
    }
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }
    //Format out (String)
    @Override
    public String toString() {
        return "Vector3f(" + x + ", " + y + ", " + z + ")";
    }
}