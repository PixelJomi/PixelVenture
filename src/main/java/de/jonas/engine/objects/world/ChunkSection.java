package de.jonas.engine.objects.world;

import de.jonas.engine.data.PVData;
import de.jonas.engine.data.blocks.Default;
import de.jonas.engine.graphics.Material;
import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.graphics.Vertex;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.utils.Console;

import java.util.ArrayList;
import java.util.Vector;

public class ChunkSection {
    private short sectionHeight;
    private Mesh mesh;
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Integer> indices = new ArrayList<Integer>();
    private Block[][][] blocks = new Block[PVData.SECTION_SIZE][PVData.SECTION_SIZE][PVData.SECTION_SIZE];

    public ChunkSection(short sectionHeight) {
        this.sectionHeight = sectionHeight;
        generateBlocks();
    }

    public void generateBlocks() {
        for (int x = 0;x < PVData.SECTION_SIZE;x++) {
            for (int y = 0;y < PVData.SECTION_SIZE;y++) {
                for (int z= 0;z < PVData.SECTION_SIZE;z++) {
                    blocks[x][y][z] = new Block("air");
                }
            }
        }
    }

    public Mesh generateMesh(Vector2f chunkPos) {
        for (int x = 0;x < PVData.SECTION_SIZE;x++) {
            for (int y = 0;y < PVData.SECTION_SIZE;y++) {
                for (int z= 0;z < PVData.SECTION_SIZE;z++) {
                    Block block = blocks[x][y][z];
                    String blockId = block.getBlockId();

                    if (blockId.equals("air")) {

                    } else {
                        addFace(Default.DEFAULT_VERTICES,Default.DEFAULT_INDICES,new Vector3f(x,y,z));
                    }
                }
            }
        }

        int[] newIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            newIndices[i] = indices.get(i);
        }

        Vertex[] newVertices = new Vertex[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            newVertices[i] = vertices.get(i);
        }
        return new Mesh(newVertices, newIndices, new Material("textures/testPic.png"));
    }

    private void addFace(Vertex[] faceVerticesTemplate, int[] faceIndicesTemplate, Vector3f blockPosition) {
        int baseVertexIndex = this.vertices.size();

        for (int i = 0; i < faceVerticesTemplate.length; i++) {
            Vertex templateVertex = faceVerticesTemplate[i];
            Vector3f templatePos = templateVertex.getPos();
            Vector3f newPos = Vector3f.add(templatePos,blockPosition);
            this.vertices.add(new Vertex(newPos, templateVertex.getTextureCord()));
        }
        for (int i = 0; i < faceIndicesTemplate.length; i++) {
            this.indices.add(faceIndicesTemplate[i] + baseVertexIndex);
        }
    }



}
