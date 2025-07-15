package de.jonas.engine.objects.world;

import de.jonas.engine.graphics.Vertex;

public class Face {
    private Vertex[] vertices = new Vertex[4];
    private int[] indices = new int[6];
    private Voxel parrentVoxel;



    //TODO Add atlas texture! And fix this!
    public Face(Voxel parrentVoxel,String texture) {
        this.parrentVoxel = parrentVoxel;
    }


    public Vertex[] getVertices() {return vertices;}
    public int[] getIndices() {return indices;}
    public void setVertices(Vertex[] vertices) {this.vertices = vertices;}
    public void setIndices(int[] indices) {this.indices = indices;}
}
