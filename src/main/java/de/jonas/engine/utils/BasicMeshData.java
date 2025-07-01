package de.jonas.engine.utils;

import de.jonas.engine.graphics.Vertex;

import java.util.ArrayList;

public class BasicMeshData {
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Integer> indices = new ArrayList<Integer>();

    public BasicMeshData(ArrayList<Vertex> vertices,ArrayList<Integer> indices) {
        this.vertices = vertices;
        this.indices = indices;
    }

    public ArrayList<Vertex> getVertices() {return vertices;}
    public ArrayList<Integer> getIndices() {return indices;}
}
