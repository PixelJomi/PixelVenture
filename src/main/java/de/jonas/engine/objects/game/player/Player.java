package de.jonas.engine.objects.game.player;

import de.jonas.engine.data.StaticData;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.game.MeshObject;

public class Player extends MeshObject {
    private float moveSpeed = 0.05f;
    private Camera camera;

    public Player(Vector3f position, Vector3f rotation) {
        super(position,rotation,null);
        this.camera = new Camera(position.add(0, StaticData.PLAYER_HEIGHT - 1,0),rotation);
    }

    public void update() {
        camera.update(this);
        setPosition(camera.getPosition());
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
}
