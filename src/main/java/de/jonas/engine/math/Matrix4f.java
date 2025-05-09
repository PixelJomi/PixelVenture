package de.jonas.engine.math;

public class Matrix4f {
    public static final int SIZE = 4;
    private float[] elements = new float[SIZE * SIZE];

    public static Matrix4f identity() {
        Matrix4f result = new Matrix4f();
        /*
        1000
        0100
        0010
        0001
         */
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result.set(i,j,0);
            }
        }
        for (int i = 0; i < SIZE; i++) {
            result.set(i,i,1);
        }

        return  result;
    }

    public static Matrix4f translate(Vector3f translate) {
        Matrix4f result = Matrix4f.identity();
        /*
        100x
        010y
        001z
        0001
         */
        result.set(3,0,translate.getX());
        result.set(3,1,translate.getY());
        result.set(3,2,translate.getZ());

        return  result;
    }

    public static Matrix4f rotate(float angle,Vector3f axis) {
        Matrix4f result = Matrix4f.identity();

        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        float versedSin = 1 - cos;

        result.set(0,0,axis.getX() * axis.getX() * versedSin + cos);
        result.set(0,1,axis.getX() * axis.getY() * versedSin - axis.getZ() * sin);
        result.set(0,2,axis.getX() * axis.getZ() * versedSin + axis.getY() * sin);
        result.set(1,0,axis.getX() * axis.getY() * versedSin + axis.getZ() * sin);
        result.set(1,1,axis.getY() * axis.getY() * versedSin + cos);
        result.set(1,2,axis.getY() * axis.getZ() * versedSin - axis.getX() * sin);
        result.set(2,0,axis.getX() * axis.getZ() * versedSin - axis.getY() * sin);
        result.set(2,1,axis.getY() * axis.getZ() * versedSin + axis.getX() * sin);
        result.set(2,2,axis.getZ() * axis.getZ() * versedSin + cos);

        return  result;
    }

    public static Matrix4f scale(Vector3f scalar) {
        Matrix4f result = Matrix4f.identity();
        /*
        x000
        0y00
        00z0
        0001
         */
        result.set(0,0,scalar.getX());
        result.set(1,1,scalar.getY());
        result.set(2,2,scalar.getZ());

        return result;
    }

    public static Matrix4f projection(float fov,float aspect, float near, float far) {
        Matrix4f result = Matrix4f.identity();

        float tanFov = (float) Math.tan(Math.toRadians(fov / 2));
        float range = far - near;

        result.set(0,0,1.0f / (aspect * tanFov));
        result.set(1,1,1.0f / tanFov);
        result.set(2,2,-((far+near) / range));
        result.set(2,3,-1.0f);
        result.set(3,2,-((2 * far  * near) / range));
        result.set(3,3,0.0f);

        return result;
    }

    public static Matrix4f view(Vector3f position, Vector3f rotation) {

        Vector3f negativePos = new Vector3f(-position.getX(), -position.getY(), -position.getZ());
        Matrix4f translationMatrix = Matrix4f.translate(negativePos);
        Matrix4f rotationXMatrix = Matrix4f.rotate(rotation.getX(),new Vector3f(1.0f,0.0f,0.0f));
        Matrix4f rotationYMatrix = Matrix4f.rotate(rotation.getY(),new Vector3f(0.0f,1.0f,0.0f));
        Matrix4f rotationZMatrix = Matrix4f.rotate(rotation.getZ(),new Vector3f(0.0f,0.0f,1.0f));

        Matrix4f rotationMatrix = Matrix4f.multiply(rotationZMatrix,Matrix4f.multiply(rotationYMatrix,rotationXMatrix));

        return Matrix4f.multiply(translationMatrix,rotationMatrix);
    }

    public static Matrix4f multiply(Matrix4f matrix, Matrix4f matrix2) {
        Matrix4f result = Matrix4f.identity();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result.set(i,j, matrix.get(i,0) * matrix2.get(0,j) + matrix.get(i,1) * matrix2.get(1,j) +
                                      matrix.get(i,2) * matrix2.get(2,j) + matrix.get(i,3) * matrix2.get(3,j));
            }
        }

        return result;
    }

    public static Matrix4f transform(Vector3f position, Vector3f rotation, Vector3f scale) {
        Matrix4f translationMatrix = Matrix4f.translate(position);
        Matrix4f rotationXMatrix = Matrix4f.rotate(rotation.getX(),new Vector3f(1.0f,0.0f,0.0f));
        Matrix4f rotationYMatrix = Matrix4f.rotate(rotation.getY(),new Vector3f(0.0f,1.0f,0.0f));
        Matrix4f rotationZMatrix = Matrix4f.rotate(rotation.getZ(),new Vector3f(0.0f,0.0f,1.0f));
        Matrix4f scaleMatrix = Matrix4f.scale(scale);

        Matrix4f rotationMatrix = Matrix4f.multiply(rotationXMatrix,Matrix4f.multiply(rotationYMatrix,rotationZMatrix));

        return Matrix4f.multiply(translationMatrix,Matrix4f.multiply(rotationMatrix,scaleMatrix));
    }

    public float get(int x, int y) {return elements[y * SIZE + x];}

    public void set(int x, int y, float value) {elements[y * SIZE + x] = value;}

    public float[] getAll() {return elements;}
}
