package de.jonas.engine.graphics;

import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;

public class Vertex {
    //Variables
    private Vector3f pos,color;
    private Vector2f textureCord;
    //Constructor
    public Vertex(Vector3f pos, Vector3f color, Vector2f textureCord) {
        this.pos = pos;
        this.color = color;
        this.textureCord = textureCord;
    }
    //Getters
    public Vector3f getPos() {return pos;}
    public Vector3f getColor() {return  color;}
    public Vector2f getTextureCord() {return  textureCord;}
    //Setters
    public void setPos(Vector3f pos) {this.pos = pos;}
    public void setColor(Vector3f color) {this.color = color;}
    public void setTextureCord(Vector2f textureCord) {this.textureCord = textureCord;}
}
