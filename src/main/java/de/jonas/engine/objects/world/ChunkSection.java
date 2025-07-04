package de.jonas.engine.objects.world;

import de.jonas.engine.data.PVData;
import de.jonas.engine.data.blocks.Default;
import de.jonas.engine.graphics.Material;
import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.graphics.Renderer;
import de.jonas.engine.graphics.Vertex;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.game.GameObject;
import de.jonas.engine.objects.game.player.Player;
import de.jonas.engine.utils.BasicMeshData;
import de.jonas.engine.utils.Console;

import java.util.ArrayList;

public class ChunkSection {
    private short sectionHeight;
    private Chunk parrentChunk;
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Integer> indices = new ArrayList<Integer>();
    private Voxel[][][] voxels = new Voxel[PVData.SECTION_SIZE][PVData.SECTION_SIZE][PVData.SECTION_SIZE];
    private Mesh mesh;

    public ChunkSection(Chunk parrentChunk, short sectionHeight) {
        this.sectionHeight = sectionHeight;
        this.parrentChunk = parrentChunk;
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

    public void generateMesh() {
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
                                    addFace(Default.DEFAULT_VOXEL_VERTICES_RIGHT, Default.DEFAULT_VOXEL_INDICES_RIGHT, new Vector3f(x,y,z));
                                }
                            } else {
                                //Do a check for the "next door" chunk.
                                addFace(Default.DEFAULT_VOXEL_VERTICES_RIGHT, Default.DEFAULT_VOXEL_INDICES_RIGHT, new Vector3f(x,y,z));
                            }

                            if (x-1 >= 0) {
                                if (isAir(new Vector3f(x-1,y,z))) {
                                    addFace(Default.DEFAULT_VOXEL_VERTICES_LEFT, Default.DEFAULT_VOXEL_INDICES_LEFT, new Vector3f(x,y,z));
                                }
                            } else {
                                //Do a check for the "next door" chunk.
                                addFace(Default.DEFAULT_VOXEL_VERTICES_LEFT, Default.DEFAULT_VOXEL_INDICES_LEFT, new Vector3f(x,y,z));
                            }

                            if (y+1 < PVData.SECTION_SIZE) {
                                if (isAir(new Vector3f(x,y+1,z))) {
                                    addFace(Default.DEFAULT_VOXEL_VERTICES_TOP, Default.DEFAULT_VOXEL_INDICES_TOP, new Vector3f(x, y, z));
                                }
                            } else {
                                //Do a check for the "next door" chunk.
                                addFace(Default.DEFAULT_VOXEL_VERTICES_TOP, Default.DEFAULT_VOXEL_INDICES_TOP, new Vector3f(x, y, z));
                            }

                            if (y-1 >= 0) {
                                if (isAir(new Vector3f(x,y-1,z))) {
                                    addFace(Default.DEFAULT_VOXEL_VERTICES_BOTTOM, Default.DEFAULT_VOXEL_INDICES_BOTTOM, new Vector3f(x,y,z));
                                }
                            } else {
                                //Do a check for the "next door" chunk.
                                addFace(Default.DEFAULT_VOXEL_VERTICES_BOTTOM, Default.DEFAULT_VOXEL_INDICES_BOTTOM, new Vector3f(x,y,z));
                            }

                            if (z+1 < PVData.SECTION_SIZE) {
                                if (isAir(new Vector3f(x,y,z+1))) {
                                    addFace(Default.DEFAULT_VOXEL_VERTICES_FRONT, Default.DEFAULT_VOXEL_INDICES_FRONT, new Vector3f(x, y, z));
                                }
                            } else {
                                //Do a check for the "next door" chunk.
                                addFace(Default.DEFAULT_VOXEL_VERTICES_FRONT, Default.DEFAULT_VOXEL_INDICES_FRONT, new Vector3f(x, y, z));
                            }

                            if (z-1 >= 0) {
                                if (isAir(new Vector3f(x,y,z-1))) {
                                    addFace(Default.DEFAULT_VOXEL_VERTICES_BACK, Default.DEFAULT_VOXEL_INDICES_BACK, new Vector3f(x,y,z));
                                }
                            } else {
                                //Do a check for the "next door" chunk.
                                addFace(Default.DEFAULT_VOXEL_VERTICES_BACK, Default.DEFAULT_VOXEL_INDICES_BACK, new Vector3f(x,y,z));
                            }

                        } catch (Exception e) {
                            Console.printError("Error while generating faces for Voxel!",new Vector3f(x,y,z).toString());
                        }
                    }
                }
            }
        }
        int[] newIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            newIndices[i] = indices.get(i);
        }
        Vertex[] newVertices = vertices.toArray(new Vertex[0]);

        //TODO Add atlas texture rendering
        if (mesh != null) {
            mesh.destroy();
        }
        mesh = new Mesh(newVertices,newIndices,new Material("textures/testPic.png"));
        mesh.create();
    }

    private boolean isAir(Vector3f pos) {
        if (voxels[(int) pos.getX()][(int) pos.getY()][(int) pos.getZ()].getVoxelID() == "air") {
            return true;
        } else {
            return false;
        }
    }

    public void render(Renderer renderer, Player player) {
        Vector3f pos = new Vector3f(parrentChunk.getChunkPos().getX() * PVData.SECTION_SIZE,sectionHeight * PVData.SECTION_SIZE,parrentChunk.getChunkPos().getY() * PVData.SECTION_SIZE);
        GameObject sectionObject = new GameObject(pos,mesh);
        renderer.renderGameObject(sectionObject,player.getCamera());
    }


    public void destroy() {
        mesh.destroy();
        vertices.clear();
        indices.clear();
    }

    private void addFace(Vertex[] faceVerticesTemplate, int[] faceIndicesTemplate, Vector3f voxelPosition) {
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

    public void setVoxel(Vector3f voxelSectionCords, String voxelID) {
        try {
            voxels[(int) voxelSectionCords.getX()][(int) voxelSectionCords.getY()][(int) voxelSectionCords.getZ()].setVoxelID(voxelID);
            generateMesh();
        } catch (Exception e) {
            Console.printError("Could not set voxel to \"" + voxelID + "\"", voxelSectionCords);
        }

    }

    public Voxel getVoxel(Vector3f voxelSectionPos) {
        return voxels[(int) voxelSectionPos.getX()][(int) voxelSectionPos.getY()][(int) voxelSectionPos.getZ()];
    }
}
