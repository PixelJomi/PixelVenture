package de.jonas.engine.objects.game.player;

import de.jonas.engine.data.UserData;
import de.jonas.engine.io.Input;
import de.jonas.engine.math.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera {
    private Vector3f position,rotation;
    private double oldMouseX,oldMouseY = 0.0d;
    private double newMouseX,newMouseY;

    public Camera(Vector3f position, Vector3f rotation) {
            this.position = position;
            this.rotation = rotation;
    }

    public void update(Player player) {
        newMouseX = Input.getMouseX();
        newMouseY = Input.getMouseY();

        float x = (float) Math.sin(Math.toRadians(rotation.getY())) * player.getMoveSpeed();
        float z = (float) Math.cos(Math.toRadians(rotation.getY())) * player.getMoveSpeed();


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
            position = Vector3f.add(position, new Vector3f(0, player.getMoveSpeed(), 0));
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            position = Vector3f.add(position, new Vector3f(0, -player.getMoveSpeed(), 0));
        }

        float dx = (float) (newMouseX - oldMouseX);
        float dy = (float) (newMouseY - oldMouseY);

        rotation = Vector3f.add(rotation,new Vector3f(-dy * UserData.MOUSE_SENSITIVITY,-dx * UserData.MOUSE_SENSITIVITY,0));

        oldMouseX = newMouseX;
        oldMouseY = newMouseY;
    }

    public void setPosition(Vector3f position) {this.position = position;}
    public void setRotation(Vector3f rotation) {this.rotation = rotation;}
    public Vector3f getPosition() {return position;}
    public Vector3f getRotation() {return rotation;}
}
