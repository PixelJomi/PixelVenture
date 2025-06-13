package de.jonas.engine.objects;

import de.jonas.engine.io.Input;
import de.jonas.engine.math.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera {
    private Vector3f position,rotation;
    private float moveSpeed = 0.05f;
    //TODO Add proper mouse sensitivity handling via JSON
    private float mouseSensitivity = 0.05f;
    private double oldMouseX,oldMouseY = 0.0d;
    private double newMouseX,newMouseY;

    public Camera(Vector3f position, Vector3f rotation) {
            this.position = position;
            this.rotation = rotation;
    }

    public void update() {
        newMouseX = Input.getMouseX();
        newMouseY = Input.getMouseY();

        float x = (float) Math.sin(Math.toRadians(rotation.getY())) * moveSpeed;
        float z = (float) Math.cos(Math.toRadians(rotation.getY())) * moveSpeed;


        if (Input.isKeyDown(GLFW.GLFW_KEY_A)) {
            position = Vector3f.add(position, new Vector3f(-z, 0, x));
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_D)) {
            position = Vector3f.add(position, new Vector3f(z, 0, -x));
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_W)) {
            position = Vector3f.add(position, new Vector3f(-x, 0, -z));
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_S)) {
            position = Vector3f.add(position, new Vector3f(x, 0, z));
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
            position = Vector3f.add(position, new Vector3f(0, moveSpeed, 0));
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            position = Vector3f.add(position, new Vector3f(0, -moveSpeed, 0));
        }

        float dx = (float) (newMouseX - oldMouseX);
        float dy = (float) (newMouseY - oldMouseY);

        rotation = Vector3f.add(rotation,new Vector3f(-dy * mouseSensitivity,-dx * mouseSensitivity,0));

        oldMouseX = newMouseX;
        oldMouseY = newMouseY;
    }

    public void setPosition(Vector3f position) {this.position = position;}
    public void setRotation(Vector3f rotation) {this.rotation = rotation;}
    public Vector3f getPosition() {return position;}
    public Vector3f getRotation() {return rotation;}

    public float getMouseSensitivity() {
        return mouseSensitivity;
    }

    public void setMouseSensitivity(float mouseSensitivity) {
        this.mouseSensitivity = mouseSensitivity;
    }
}
