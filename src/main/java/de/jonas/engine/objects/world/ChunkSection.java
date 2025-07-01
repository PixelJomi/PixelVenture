package de.jonas.engine.objects.world;

import de.jonas.engine.data.PVData;
import de.jonas.engine.data.blocks.Default;
import de.jonas.engine.graphics.Material;
import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.graphics.Vertex;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.utils.BasicMeshData;
import de.jonas.engine.utils.Console;

import java.util.ArrayList;

public class ChunkSection {
    private short sectionHeight;
    private Mesh mesh;
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Integer> indices = new ArrayList<Integer>();
    private Voxel[][][] voxels = new Voxel[PVData.SECTION_SIZE][PVData.SECTION_SIZE][PVData.SECTION_SIZE];

    public ChunkSection(short sectionHeight) {
        this.sectionHeight = sectionHeight;
        generateVoxels();
    }

    public void generateVoxels() {
        for (int x = 0;x < PVData.SECTION_SIZE;x++) {
            for (int y = 0;y < PVData.SECTION_SIZE;y++) {
                for (int z= 0;z < PVData.SECTION_SIZE;z++) {
                    voxels[x][y][z] = new Voxel("dirt");
                }
            }
        }
    }

    public BasicMeshData generateMesh(Vector2f chunkPos) {
        for (int x = 0;x < PVData.SECTION_SIZE;x++) {
            for (int y = 0;y < PVData.SECTION_SIZE;y++) {
                for (int z= 0;z < PVData.SECTION_SIZE;z++) {
                    Voxel voxel = voxels[x][y][z];
                    String voxelID = voxel.getVoxelID();
                    if (voxelID.equals("air")) {
                        //DO NOTHING BC AIR...
                    } else {
                        try {
                            if (x+1 < PVData.SECTION_SIZE) {
                                if (isAir(new Vector3f(x+1,y,z))) {
                                    addFace(Default.DEFAULT_VOXEL_VERTICES_RIGHT, Default.DEFAULT_VOXEL_INDICES_RIGHT, new Vector3f(x,y,z),chunkPos);
                                }
                            } else {
                                addFace(Default.DEFAULT_VOXEL_VERTICES_RIGHT, Default.DEFAULT_VOXEL_INDICES_RIGHT, new Vector3f(x,y,z),chunkPos);
                            }

                            if (x-1 >= 0) {
                                if (isAir(new Vector3f(x-1,y,z))) {
                                    addFace(Default.DEFAULT_VOXEL_VERTICES_LEFT, Default.DEFAULT_VOXEL_INDICES_LEFT, new Vector3f(x,y,z),chunkPos);
                                }
                            } else {
                                addFace(Default.DEFAULT_VOXEL_VERTICES_LEFT, Default.DEFAULT_VOXEL_INDICES_LEFT, new Vector3f(x,y,z),chunkPos);
                            }

                            if (y+1 < PVData.SECTION_SIZE) {
                                if (isAir(new Vector3f(x,y+1,z))) {
                                    addFace(Default.DEFAULT_VOXEL_VERTICES_TOP, Default.DEFAULT_VOXEL_INDICES_TOP, new Vector3f(x, y, z),chunkPos);
                                }
                            } else {
                                addFace(Default.DEFAULT_VOXEL_VERTICES_TOP, Default.DEFAULT_VOXEL_INDICES_TOP, new Vector3f(x, y, z),chunkPos);
                            }

                            if (y-1 >= 0) {
                                if (isAir(new Vector3f(x,y-1,z))) {
                                    addFace(Default.DEFAULT_VOXEL_VERTICES_BOTTOM, Default.DEFAULT_VOXEL_INDICES_BOTTOM, new Vector3f(x,y,z),chunkPos);
                                }
                            } else {
                                addFace(Default.DEFAULT_VOXEL_VERTICES_BOTTOM, Default.DEFAULT_VOXEL_INDICES_BOTTOM, new Vector3f(x,y,z),chunkPos);
                            }

                            if (z+1 < PVData.SECTION_SIZE) {
                                if (isAir(new Vector3f(x,y,z+1))) {
                                    addFace(Default.DEFAULT_VOXEL_VERTICES_FRONT, Default.DEFAULT_VOXEL_INDICES_FRONT, new Vector3f(x, y, z),chunkPos);
                                }
                            } else {
                                addFace(Default.DEFAULT_VOXEL_VERTICES_FRONT, Default.DEFAULT_VOXEL_INDICES_FRONT, new Vector3f(x, y, z),chunkPos);
                            }

                            if (z-1 >= 0) {
                                if (isAir(new Vector3f(x,y,z-1))) {
                                    addFace(Default.DEFAULT_VOXEL_VERTICES_BACK, Default.DEFAULT_VOXEL_INDICES_BACK, new Vector3f(x,y,z),chunkPos);
                                }
                            } else {
                                addFace(Default.DEFAULT_VOXEL_VERTICES_BACK, Default.DEFAULT_VOXEL_INDICES_BACK, new Vector3f(x,y,z),chunkPos);
                            }

                        } catch (Exception e) {
                            Console.printError("Error while generating faces for Voxel!",new Vector3f(x,y,z).toString());
                        }
                    }
                }
            }
        }

//        int[] newIndices = new int[indices.size()];
//        for (int i = 0; i < indices.size(); i++) {
//            newIndices[i] = indices.get(i);
//        }
//
//        Vertex[] newVertices = new Vertex[vertices.size()];
//        for (int i = 0; i < vertices.size(); i++) {
//            newVertices[i] = vertices.get(i);
//        }
        return new BasicMeshData(vertices,indices);
    }

    private boolean isAir(Vector3f pos) {
        if (voxels[(int) pos.getX()][(int) pos.getY()][(int) pos.getZ()].getVoxelID() == "air") {
            return true;
        } else {
            return false;
        }
    }

    private void addFace(Vertex[] faceVerticesTemplate, int[] faceIndicesTemplate, Vector3f voxelPosition, Vector2f chunkPos) {
        voxelPosition.add(chunkPos.getX() * PVData.SECTION_SIZE,sectionHeight * PVData.SECTION_SIZE,chunkPos.getX() * PVData.SECTION_SIZE);

        int baseVertexIndex = this.vertices.size();

        for (int i = 0; i < faceVerticesTemplate.length; i++) {
            Vertex templateVertex = faceVerticesTemplate[i];
            Vector3f templatePos = templateVertex.getPos();
            Vector3f newPos = Vector3f.add(templatePos,voxelPosition);
            this.vertices.add(new Vertex(newPos, templateVertex.getTextureCord()));
        }
        for (int i = 0; i < faceIndicesTemplate.length; i++) {
            this.indices.add(faceIndicesTemplate[i] + baseVertexIndex);
        }
    }



}
