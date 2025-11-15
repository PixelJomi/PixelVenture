package de.jonas.engine.utils;

import java.util.ArrayList;

import de.jonas.engine.graphics.Vertex;

public class BasicMeshData {
    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<Integer> indices = new ArrayList<>();

    public BasicMeshData(ArrayList<Vertex> vertices,ArrayList<Integer> indices) {
        this.vertices = vertices;
        this.indices = indices;
    }

    public ArrayList<Vertex> getVertices() {return vertices;}
    public ArrayList<Integer> getIndices() {return indices;}
}
