package de.jonas.engine.objects.game.player;

import de.jonas.engine.io.Input;
import de.jonas.engine.math.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Player {
    private float moveSpeed = 0.05f;
    private Vector3f position,rotation;
    private short playerHeight = 2;
    private Camera camera;

    public Player(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
        this.camera = new Camera(position.add(0,playerHeight - 1,0),rotation);
    }

    public void update() {
        camera.update(this);
        position = camera.getPosition();
    }

    public short getPlayerHeight() {
        return playerHeight;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public Camera getCamera() {
        return camera;
    }

}
