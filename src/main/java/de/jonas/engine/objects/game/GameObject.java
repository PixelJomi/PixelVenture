package de.jonas.engine.objects.game;

import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.math.Vector3f;

public class GameObject {
    private Vector3f position, rotation, scale;
    private Mesh mesh;
    private double temp;


    public GameObject(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.mesh = mesh;
    }

    public GameObject(Vector3f position, Vector3f rotation, Mesh mesh) {
        this.position = position;
        this.rotation = rotation;
        this.scale = new Vector3f(1,1,1);
        this.mesh = mesh;
    }

    public GameObject(Vector3f position, Mesh mesh) {
        this.position = position;
        this.rotation = new Vector3f(0,0,0);
        this.scale = new Vector3f(1,1,1);
        this.mesh = mesh;
    }

    public  void update() {

    }

    public Vector3f getPosition() {return position;}
    public Vector3f getRotation() {return rotation;}
    public Vector3f getScale() {return scale;}
    public Mesh getMesh() {return mesh;}

}
