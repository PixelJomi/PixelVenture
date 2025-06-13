package de.jonas.engine.math;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a 4x4 matrix of single-precision floating-point numbers, commonly used for
 * 3D transformations such as translation, rotation, scaling, and projection in computer graphics.
 * The matrix stores its elements in a 1D array in column-major order for efficient operations.
 *
 * @author PixelJomi (Jomicraft) / Jonas
 */
public class Matrix4f {
    /**
     * The dimension of the square matrix, representing a 4x4 matrix.
     */
    public static final int SIZE = 4;
    private float[] elements = new float[SIZE * SIZE]; // Stores the 16 elements of the 4x4 matrix

    //Matrices
    /**
     * Creates and returns a new 4x4 identity matrix.
     * An identity matrix has ones on its main diagonal (top-left to bottom-right) and zeros everywhere else.
     * Applying an identity matrix to a vector or object does not change its position, rotation, or scale.
     *
     * <pre>
     * 1 0 0 0
     * 0 1 0 0
     * 0 0 1 0
     * 0 0 0 1
     * </pre>
     *
     * @return A new {@code Matrix4f} instance representing the identity matrix.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Matrix4f identity() {
        Matrix4f result = new Matrix4f();
        // Initialize all elements to 0
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result.set(i, j, 0);
            }
        }
        // Set diagonal elements to 1
        for (int i = 0; i < SIZE; i++) {
            result.set(i, i, 1);
        }
        return result;
    }

    /**
     * Creates and returns a new 4x4 translation matrix based on a given 3D translation vector.
     * This matrix can be used to move objects in 3D space.
     *
     * <pre>
     * 1 0 0 Tx
     * 0 1 0 Ty
     * 0 0 1 Tz
     * 0 0 0 1
     * </pre>
     * Where Tx, Ty, Tz are the X, Y, Z components of the translate vector.
     *
     * @param translate A {@code Vector3f} representing the translation amounts along the X, Y, and Z axes.
     * @return A new {@code Matrix4f} instance representing the translation matrix.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Matrix4f translate(Vector3f translate) {
        Matrix4f result = Matrix4f.identity();
        result.set(3, 0, translate.getX());
        result.set(3, 1, translate.getY());
        result.set(3, 2, translate.getZ());
        return result;
    }

    /**
     * Creates and returns a new 4x4 rotation matrix around a specified axis by a given angle.
     * This matrix can be used to rotate objects in 3D space. The angle is expected in degrees.
     *
     * @param angle The angle of rotation in degrees.
     * @param axis  A {@code Vector3f} representing the axis of rotation. This vector should ideally be normalized.
     * @return A new {@code Matrix4f} instance representing the rotation matrix.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Matrix4f rotate(float angle, Vector3f axis) {
        Matrix4f result = Matrix4f.identity();

        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        float versedSin = 1 - cos; // Also known as (1 - cos(theta)) or versin(theta)

        // Rotation matrix elements based on axis-angle formula (Rodrigues' rotation formula)
        result.set(0, 0, axis.getX() * axis.getX() * versedSin + cos);
        result.set(0, 1, axis.getX() * axis.getY() * versedSin - axis.getZ() * sin);
        result.set(0, 2, axis.getX() * axis.getZ() * versedSin + axis.getY() * sin);

        result.set(1, 0, axis.getX() * axis.getY() * versedSin + axis.getZ() * sin);
        result.set(1, 1, axis.getY() * axis.getY() * versedSin + cos);
        result.set(1, 2, axis.getY() * axis.getZ() * versedSin - axis.getX() * sin);

        result.set(2, 0, axis.getX() * axis.getZ() * versedSin - axis.getY() * sin);
        result.set(2, 1, axis.getY() * axis.getZ() * versedSin + axis.getX() * sin);
        result.set(2, 2, axis.getZ() * axis.getZ() * versedSin + cos);

        return result;
    }

    /**
     * Creates and returns a new 4x4 scaling matrix based on a given 3D scalar vector.
     * This matrix can be used to resize objects along the X, Y, and Z axes.
     *
     * <pre>
     * Sx 0 0 0
     * 0 Sy 0 0
     * 0 0 Sz 0
     * 0 0 0 1
     * </pre>
     * Where Sx, Sy, Sz are the X, Y, Z components of the scalar vector.
     *
     * @param scalar A {@code Vector3f} representing the scaling factors along the X, Y, and Z axes.
     * @return A new {@code Matrix4f} instance representing the scaling matrix.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Matrix4f scale(Vector3f scalar) {
        Matrix4f result = Matrix4f.identity();
        result.set(0, 0, scalar.getX());
        result.set(1, 1, scalar.getY());
        result.set(2, 2, scalar.getZ());
        return result;
    }

    /**
     * Creates and returns a new 4x4 perspective projection matrix.
     * This matrix transforms 3D points into 2D points on the screen, creating the illusion of depth.
     *
     * @param fov    The field of view angle in degrees. This determines how "wide" the camera's view is.
     * @param aspect The aspect ratio of the viewport (width / height).
     * @param near   The distance to the near clipping plane. Objects closer than this are clipped.
     * @param far    The distance to the far clipping plane. Objects farther than this are clipped.
     * @return A new {@code Matrix4f} instance representing the perspective projection matrix.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Matrix4f projection(float fov, float aspect, float near, float far) {
        Matrix4f result = Matrix4f.identity();

        float tanFov = (float) Math.tan(Math.toRadians(fov / 2));
        float range = far - near;

        result.set(0, 0, 1.0f / (aspect * tanFov));
        result.set(1, 1, 1.0f / tanFov);
        result.set(2, 2, -((far + near) / range));
        result.set(2, 3, -1.0f);
        result.set(3, 2, -((2 * far * near) / range));
        result.set(3, 3, 0.0f);

        return result;
    }

    /**
     * Creates and returns a new 4x4 view matrix.
     * A view matrix effectively simulates moving and rotating the world around the camera.
     * It is typically the inverse of the camera's transformation.
     *
     * @param position A {@code Vector3f} representing the camera's position in world space.
     * The view matrix will use the negative of this position for translation.
     * @param rotation A {@code Vector3f} representing the camera's rotation (in degrees) around
     * its X, Y, and Z axes.
     * @return A new {@code Matrix4f} instance representing the view matrix.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Matrix4f view(Vector3f position, Vector3f rotation) {
        // Translate the world by the negative of the camera's position
        Vector3f negativePos = new Vector3f(-position.getX(), -position.getY(), -position.getZ());
        Matrix4f translationMatrix = Matrix4f.translate(negativePos);

        // Create rotation matrices for each axis
        Matrix4f rotationXMatrix = Matrix4f.rotate(rotation.getX(), new Vector3f(1.0f, 0.0f, 0.0f));
        Matrix4f rotationYMatrix = Matrix4f.rotate(rotation.getY(), new Vector3f(0.0f, 1.0f, 0.0f));
        Matrix4f rotationZMatrix = Matrix4f.rotate(rotation.getZ(), new Vector3f(0.0f, 0.0f, 1.0f));

        // Combine rotations (order typically Z * Y * X for view matrices)
        Matrix4f rotationMatrix = Matrix4f.multiply(rotationZMatrix, Matrix4f.multiply(rotationYMatrix, rotationXMatrix));

        // Combine translation and rotation to form the final view matrix
        return Matrix4f.multiply(translationMatrix, rotationMatrix);
    }

    /**
     * Creates and returns a new 4x4 transformation matrix that combines translation, rotation, and scaling.
     * This matrix is used to position, orient, and size an object in 3D space.
     * The operations are typically applied in the order: Scale -> Rotate -> Translate.
     *
     * @param position A {@code Vector3f} representing the translation (position) of the object.
     * @param rotation A {@code Vector3f} representing the rotation (in degrees) of the object
     * around its X, Y, and Z axes.
     * @param scale    A {@code Vector3f} representing the scaling factors of the object along
     * its X, Y, and Z axes.
     * @return A new {@code Matrix4f} instance representing the combined transformation matrix.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Matrix4f transform(Vector3f position, Vector3f rotation, Vector3f scale) {
        Matrix4f translationMatrix = Matrix4f.translate(position);
        Matrix4f rotationXMatrix = Matrix4f.rotate(rotation.getX(), new Vector3f(1.0f, 0.0f, 0.0f));
        Matrix4f rotationYMatrix = Matrix4f.rotate(rotation.getY(), new Vector3f(0.0f, 1.0f, 0.0f));
        Matrix4f rotationZMatrix = Matrix4f.rotate(rotation.getZ(), new Vector3f(0.0f, 0.0f, 1.0f));
        Matrix4f scaleMatrix = Matrix4f.scale(scale);

        // Rotation matrices are typically multiplied in the order X * Y * Z for object transformations.
        // (This order might vary depending on the desired coordinate system/convention.)
        Matrix4f rotationMatrix = Matrix4f.multiply(rotationXMatrix, Matrix4f.multiply(rotationYMatrix, rotationZMatrix));

        // Combined transformation: Translate * Rotate * Scale
        return Matrix4f.multiply(translationMatrix, Matrix4f.multiply(rotationMatrix, scaleMatrix));
    }

    //Math
    /**
     * Multiplies two 4x4 matrices and returns a new matrix representing the product.
     * Matrix multiplication is not commutative (A * B is generally not equal to B * A).
     * This method performs standard matrix multiplication, where the resulting element
     * at (row, column) is the dot product of the row from the first matrix and the column
     * from the second matrix.
     *
     * @param matrix  The first {@code Matrix4f} (left-hand side of the multiplication).
     * @param matrix2 The second {@code Matrix4f} (right-hand side of the multiplication).
     * @return A new {@code Matrix4f} instance representing the result of the multiplication.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public static Matrix4f multiply(Matrix4f matrix, Matrix4f matrix2) {
        Matrix4f result = Matrix4f.identity(); // Start with an identity matrix as a placeholder

        for (int i = 0; i < SIZE; i++) { // Iterate through rows of the result matrix
            for (int j = 0; j < SIZE; j++) { // Iterate through columns of the result matrix
                // Calculate the dot product of row i from matrix and column j from matrix2
                result.set(i, j, matrix.get(i, 0) * matrix2.get(0, j) +
                        matrix.get(i, 1) * matrix2.get(1, j) +
                        matrix.get(i, 2) * matrix2.get(2, j) +
                        matrix.get(i, 3) * matrix2.get(3, j));
            }
        }
        return result;
    }

    //Getter
    /**
     * Retrieves the float value at a specific (row, column) position within the matrix.
     * The elements are stored in a 1D array, and this method calculates the correct
     * index based on the row and column.
     *
     * @param x The column index (0-3) of the element to retrieve.
     * @param y The row index (0-3) of the element to retrieve.
     * @return The float value at the specified (row, column) position.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public float get(int x, int y) {
        return elements[y * SIZE + x];
    }

    /**
     * Returns a copy of the underlying 1D array of elements that make up this matrix.
     * The elements are stored in column-major order (meaning elements of the first column
     * are contiguous, then the second, etc.).
     *
     * @return A new float array containing all 16 elements of the matrix.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public float[] getAll() {
        return elements;
    }

    //Setters
    /**
     * Sets the float value at a specific (row, column) position within the matrix.
     * The elements are stored in a 1D array, and this method calculates the correct
     * index based on the row and column.
     *
     * @param x     The column index (0-3) where the value will be set.
     * @param y     The row index (0-3) where the value will be set.
     * @param value The float value to set at the specified position.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    public void set(int x, int y, float value) {
        elements[y * SIZE + x] = value;
    }

    //Hash map
    /**
     * Compares this {@code Matrix4f} to the specified object. The result is {@code true} if and only if
     * the argument is not {@code null} and is a {@code Matrix4f} object that has the same
     * floating-point elements as this object. It uses {@link Objects#deepEquals(Object, Object)}
     * for array comparison, which is robust for floating-point arrays.
     *
     * @param o The object to compare this {@code Matrix4f} against.
     * @return {@code true} if the given object represents a {@code Matrix4f} equivalent to this matrix,
     * {@code false} otherwise.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Matrix4f matrix4f = (Matrix4f) o;
        return Objects.deepEquals(elements, matrix4f.elements);
    }

    /**
     * Returns a hash code value for this {@code Matrix4f}.
     * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
     * It uses {@link Arrays#hashCode(float[])} to generate a hash code based on the contents of the
     * matrix's elements array.
     *
     * @return A hash code value for this object.
     * @author PixelJomi (Jomicraft) / Jonas
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }
}