package de.jonas.engine.objects.world;

import de.jonas.engine.data.PVData;
import de.jonas.engine.graphics.Material;
import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.graphics.Vertex;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.utils.BasicMeshData;

import java.util.ArrayList;

public class Chunk {
    private Vector2f chunkPos;
    private ChunkSection[] chunkSections = new ChunkSection[PVData.CHUNK_SECTION_AMOUNT];

    public Chunk(Vector2f chunkPos) {
        this.chunkPos = chunkPos;
        generateSections();
    }

    public void generateSections() {
        for (int i = 0;i < chunkSections.length;i++){
            chunkSections[i] = new ChunkSection((short) i);
        }
    }

    public Mesh getMesh() {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> indices = new ArrayList<Integer>();

        for (int i = 0;i < chunkSections.length;i++){
            BasicMeshData basicMeshData = chunkSections[i].generateMesh(chunkPos);
            ArrayList<Vertex> newVertices = basicMeshData.getVertices();
            int offset = vertices.size();
            vertices.addAll(newVertices);

            System.out.println(offset);
            ArrayList<Integer> newIndices = basicMeshData.getIndices();
            for (int j = 0; j < newIndices.size(); j++) {
                indices.add(newIndices.get(j)+offset);
            }

        }

        int[] newIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            newIndices[i] = indices.get(i);
        }

        Vertex[] newVertices = vertices.toArray(new Vertex[0]);

        return new Mesh(newVertices,newIndices,new Material("textures/testPic.png"));


    }

    public Vector2f getChunkPos() {return chunkPos;}

}
