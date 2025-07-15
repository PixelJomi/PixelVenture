package de.jonas.engine.objects.game.player;

import de.jonas.engine.data.DynamicData;
import de.jonas.engine.data.UserData;
import de.jonas.engine.io.Input;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.game.GameObject;
import org.lwjgl.glfw.GLFW;

public class Camera extends GameObject {
    private double oldMouseX,oldMouseY = 0.0d;
    private double newMouseX,newMouseY;

    public Camera(Vector3f position, Vector3f rotation) {
            super(position,rotation,null);
    }

    public void update(Player player) {
        newMouseX = Input.getMouseX();
        newMouseY = Input.getMouseY();

        float x = (float) Math.sin(Math.toRadians(getRotation().getY())) * player.getMoveSpeed();
        float z = (float) Math.cos(Math.toRadians(getRotation().getY())) * player.getMoveSpeed();


        if (Input.isKeyDown(GLFW.GLFW_KEY_A)) {
            setPosition(Vector3f.add(getPosition(), new Vector3f(-z, 0, x)));
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_D)) {
            setPosition(Vector3f.add(getPosition(), new Vector3f(z, 0, -x)));
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_W)) {
            setPosition(Vector3f.add(getPosition(), new Vector3f(-x, 0, -z)));
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_S)) {
            setPosition(Vector3f.add(getPosition(), new Vector3f(x, 0, z)));
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
            setPosition(Vector3f.add(getPosition(), new Vector3f(0, player.getMoveSpeed(), 0)));
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            setPosition(Vector3f.add(getPosition(), new Vector3f(0, -player.getMoveSpeed(), 0)));
        }

        float dx = (float) (newMouseX - oldMouseX);
        float dy = (float) (newMouseY - oldMouseY);

        if (!DynamicData.DEBUG_GUI) {
            setRotation(Vector3f.add(getRotation(),new Vector3f(-dy * UserData.MOUSE_SENSITIVITY,-dx * UserData.MOUSE_SENSITIVITY,0)));
        }

        oldMouseX = newMouseX;
        oldMouseY = newMouseY;
    }
}
