package de.jonas.engine.io.math;

public class Vector3f {
    public float x,y,z;

    public Vector3f(float x,float y,float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void mult(float multAll) {
        this.x *= multAll;
        this.y *= multAll;
        this.z *= multAll;
    }
    public void mult(float multX,float multY,float multZ) {
        this.x *= multX;
        this.y *= multY;
        this.z *= multZ;
    }
    public void add(float sumAll) {
        this.x += sumAll;
        this.y += sumAll;
        this.z += sumAll;
    }
    public void add(float sumX,float sumY,float sumZ) {
        this.x += sumX;
        this.y += sumY;
        this.z += sumZ;
    }
    public void sub(float subAll) {
        this.x += subAll;
        this.y += subAll;
        this.z += subAll;
    }
    public void sub(float subX,float subY,float subZ) {
        this.x += subX;
        this.y += subY;
        this.z += subZ;
    }
    public void dev(float devAll) {
        this.x += devAll;
        this.y += devAll;
        this.z += devAll;
    }
    public void dev(float devX,float devY,float devZ) {
        this.x += devX;
        this.y += devY;
        this.z += devZ;
    }
}