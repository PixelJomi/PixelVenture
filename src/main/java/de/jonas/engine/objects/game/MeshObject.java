package de.jonas.engine.objects.game;

import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.math.Vector3f;

public class MeshObject extends GameObject{
    private Mesh mesh;

    public MeshObject(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
        super(position,rotation,scale);
        this.mesh = mesh;
    }

    public MeshObject(Vector3f position, Vector3f rotation, Mesh mesh) {
        super(position,rotation);
        this.mesh = mesh;
    }

    public MeshObject(Vector3f position, Mesh mesh) {
        super(position);
        this.mesh = mesh;
    }

    public Mesh getMesh() {return mesh;}

    public void setMesh(Mesh mesh) {this.mesh = mesh;}
}
