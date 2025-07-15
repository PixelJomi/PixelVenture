package de.jonas.engine.objects.game;

import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.math.Vector3f;

public class GameObject {
    private Vector3f position, rotation, scale;

    public GameObject(Vector3f position, Vector3f rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public GameObject(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
        this.scale = new Vector3f(1,1,1);
    }

    public GameObject(Vector3f position) {
        this.position = position;
        this.rotation = new Vector3f(0,0,0);
        this.scale = new Vector3f(1,1,1);
    }

    public Vector3f getPosition() {return position;}
    public Vector3f getRotation() {return rotation;}
    public Vector3f getScale() {return scale;}

    public void setPosition(Vector3f position) {this.position = position;}
    public void setRotation(Vector3f rotation) {this.rotation = rotation;}
    public void setScale(Vector3f scale) {this.scale = scale;}
}
