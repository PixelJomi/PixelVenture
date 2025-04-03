package de.jonas.engine.graphics;

import de.jonas.engine.math.Vector3f;

public class Vertex {
    //Variables
    private Vector3f pos,color;
    //Constructor
    public Vertex(Vector3f pos, Vector3f color) {
        this.pos = pos;
        this.color = color;
    }
    //Getters
    public Vector3f getPos() {return pos;}
    public Vector3f getColor() {return  color;}
    //Setters
    public void setPos(Vector3f pos) {this.pos = pos;}
    public void setColor(Vector3f color) {this.color = color;}

}
