package de.jonas.engine.graphics;

import de.jonas.engine.math.Vector3f;

public class Vertex {
    //Variables
    private Vector3f pos;
    //Constructor
    public Vertex(Vector3f pos) {
        this.pos = pos;
    }
    //Getters
    public Vector3f getPos() {return this.pos;}
    //Setters
    public void setPos(Vector3f pos) {this.pos = pos;}
}
