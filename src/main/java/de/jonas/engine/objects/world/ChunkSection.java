    package de.jonas.engine.objects.world;

    import de.jonas.engine.data.StaticData;
    import de.jonas.engine.data.blocks.Default;
    import de.jonas.engine.graphics.Material;
    import de.jonas.engine.graphics.Mesh;
    import de.jonas.engine.graphics.Renderer;
    import de.jonas.engine.graphics.Vertex;
    import de.jonas.engine.math.Vector2f;
    import de.jonas.engine.math.Vector3f;
    import de.jonas.engine.objects.game.MeshObject;
    import de.jonas.engine.objects.game.player.Player;
    import de.jonas.engine.utils.Console;
    import de.jonas.engine.utils.MainThreadExecutor;
    import java.util.ArrayList;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

    public class ChunkSection extends MeshObject {
        private short sectionHeight;
        private Chunk parrentChunk;
        private Voxel[][][] voxels = new Voxel[StaticData.SECTION_SIZE][StaticData.SECTION_SIZE][StaticData.SECTION_SIZE];
        //TODO Check if valid pls Idk if this code is right.
        private static final ExecutorService meshThreadPool = Executors.newFixedThreadPool(4);
        private boolean needsReloading = false;

        public ChunkSection(Chunk parrentChunk, short sectionHeight) {
            //TODO Add multiple objects like a alongside the Mesh.java the MeshObject.java which is a GameObject with a mesh rendering stuff.
            //TODO Add a class for a face etc.
            //TODO Add more things like a StaticBody / Hitbox class / A Voxel class with the help of extends/implements.
            super(
                    new Vector3f(
                            parrentChunk.getChunkPos().getX() * StaticData.SECTION_SIZE,
                            sectionHeight * StaticData.SECTION_SIZE,
                            parrentChunk.getChunkPos().getY() * StaticData.SECTION_SIZE),
                    null);
            this.sectionHeight = sectionHeight;
            this.parrentChunk = parrentChunk;
            generateVoxels();
            setNeedsReloading();
        }

        public void generateVoxels() {
            for (int x = 0; x < StaticData.SECTION_SIZE; x++) {
                for (int y = 0; y < StaticData.SECTION_SIZE; y++) {
                    for (int z = 0; z < StaticData.SECTION_SIZE; z++) {
                        voxels[x][y][z] = new Voxel("dirt");
                    }
                }
            }
        }

        private void generateMesh(ResultCallback callback) {
            new Thread(() -> {
                try {
                    ArrayList<Vertex> vertices = new ArrayList<Vertex>();
                    ArrayList<Integer> indices = new ArrayList<Integer>();
                    for (int x = 0; x < StaticData.SECTION_SIZE; x++) {
                        for (int y = 0; y < StaticData.SECTION_SIZE; y++) {
                            for (int z = 0; z < StaticData.SECTION_SIZE; z++) {
                                if (isAir(new Vector3f(x, y, z))) {
                                    //DO NOTHING BC AIR...
                                    //TODO Add a check for block changing so maybe no chunk neighbour updates need to be run
                                    if (x+1 >= StaticData.SECTION_SIZE) {
                                        try {
                                            parrentChunk.getParrentWorld().getChunk(Vector2f.add(parrentChunk.getChunkPos(),new Vector2f(1,0))).getChunkSection(sectionHeight).setNeedsReloading();
                                        } catch (Exception e) {}
                                    }
                                    if (x-1 < 0) {
                                        try {
                                            parrentChunk.getParrentWorld().getChunk(Vector2f.sub(parrentChunk.getChunkPos(),new Vector2f(1,0))).getChunkSection(sectionHeight).setNeedsReloading();
                                        } catch (Exception e) {}
                                    }
                                    if (y+1 >= StaticData.SECTION_SIZE) {
                                        try {
                                            parrentChunk.getChunkSection(sectionHeight + 1).setNeedsReloading();
                                        } catch (Exception e) {}
                                    }
                                    if (y-1 < 0) {
                                        try {
                                            parrentChunk.getChunkSection(sectionHeight - 1).setNeedsReloading();
                                        } catch (Exception e) {}
                                    }
                                    if (z+1 >= StaticData.SECTION_SIZE) {
                                        try {
                                            parrentChunk.getParrentWorld().getChunk(Vector2f.add(parrentChunk.getChunkPos(),new Vector2f(0,1))).getChunkSection(sectionHeight).setNeedsReloading();
                                        } catch (Exception e) {}
                                    }
                                    if (z-1 < 0) {
                                        try {
                                            parrentChunk.getParrentWorld().getChunk(Vector2f.sub(parrentChunk.getChunkPos(),new Vector2f(0,1))).getChunkSection(sectionHeight).setNeedsReloading();
                                        } catch (Exception e) {}
                                    }
                                } else {
                                    try {
                                        //RIGHT x
                                        if (x + 1 < StaticData.SECTION_SIZE) {
                                            if (isAir(new Vector3f(x + 1, y, z))) {
                                                addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_RIGHT, Default.DEFAULT_VOXEL_INDICES_RIGHT, new Vector3f(x, y, z));
                                            }
                                        } else {
                                            try {
                                                ChunkSection chunkSectionToCheck = parrentChunk.getParrentWorld().getChunk(Vector2f.add(parrentChunk.getChunkPos(), new Vector2f(1, 0))).getChunkSection(sectionHeight);
                                                if (chunkSectionToCheck.isAir(new Vector3f(0, y, z))) {
                                                    addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_RIGHT, Default.DEFAULT_VOXEL_INDICES_RIGHT, new Vector3f(x, y, z));
                                                }
                                            } catch (Exception e) {
                                                addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_RIGHT, Default.DEFAULT_VOXEL_INDICES_RIGHT, new Vector3f(x, y, z));
                                            }
                                        }
                                        //LEFT -x
                                        if (x - 1 >= 0) {
                                            if (isAir(new Vector3f(x - 1, y, z))) {
                                                addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_LEFT, Default.DEFAULT_VOXEL_INDICES_LEFT, new Vector3f(x, y, z));
                                            }
                                        } else {
                                            try {
                                                ChunkSection chunkSectionToCheck = parrentChunk.getParrentWorld().getChunk(Vector2f.sub(parrentChunk.getChunkPos(), new Vector2f(1, 0))).getChunkSection(sectionHeight);
                                                if (chunkSectionToCheck.isAir(new Vector3f(StaticData.SECTION_SIZE - 1, y, z))) {
                                                    addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_LEFT, Default.DEFAULT_VOXEL_INDICES_LEFT, new Vector3f(x, y, z));
                                                }
                                            } catch (Exception e) {
                                                addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_LEFT, Default.DEFAULT_VOXEL_INDICES_LEFT, new Vector3f(x, y, z));
                                            }
                                        }
                                        //FRONT
                                        if (y + 1 < StaticData.SECTION_SIZE) {
                                            if (isAir(new Vector3f(x, y + 1, z))) {
                                                addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_TOP, Default.DEFAULT_VOXEL_INDICES_TOP, new Vector3f(x, y, z));
                                            }
                                        } else {
                                            try {
                                                ChunkSection chunkSectionToCheck = parrentChunk.getChunkSection(sectionHeight + 1);
                                                if (chunkSectionToCheck.isAir(new Vector3f(x, 0, z))) {
                                                    addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_TOP, Default.DEFAULT_VOXEL_INDICES_TOP, new Vector3f(x, y, z));
                                                }
                                            } catch (Exception e) {
                                                addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_TOP, Default.DEFAULT_VOXEL_INDICES_TOP, new Vector3f(x, y, z));
                                            }
                                        }
                                        //BOTTOM
                                        if (y - 1 >= 0) {
                                            if (isAir(new Vector3f(x, y - 1, z))) {
                                                addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_BOTTOM, Default.DEFAULT_VOXEL_INDICES_BOTTOM, new Vector3f(x, y, z));
                                            }
                                        } else {
                                            try {
                                                ChunkSection chunkSectionToCheck = parrentChunk.getChunkSection(sectionHeight - 1);
                                                if (chunkSectionToCheck.isAir(new Vector3f(x, StaticData.SECTION_SIZE - 1, z))) {
                                                    addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_BOTTOM, Default.DEFAULT_VOXEL_INDICES_BOTTOM, new Vector3f(x, y, z));
                                                }
                                            } catch (Exception e) {
                                                addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_BOTTOM, Default.DEFAULT_VOXEL_INDICES_BOTTOM, new Vector3f(x, y, z));
                                            }
                                        }
                                        //FRONT z
                                        if (z + 1 < StaticData.SECTION_SIZE) {
                                            if (isAir(new Vector3f(x, y, z + 1))) {
                                                addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_FRONT, Default.DEFAULT_VOXEL_INDICES_FRONT, new Vector3f(x, y, z));
                                            }
                                        } else {
                                            try {
                                                ChunkSection chunkSectionToCheck = parrentChunk.getParrentWorld().getChunk(Vector2f.add(parrentChunk.getChunkPos(), new Vector2f(0, 1))).getChunkSection(sectionHeight);
                                                if (chunkSectionToCheck.isAir(new Vector3f(x, y, 0))) {
                                                    addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_FRONT, Default.DEFAULT_VOXEL_INDICES_FRONT, new Vector3f(x, y, z));
                                                }
                                            } catch (Exception e) {
                                                addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_FRONT, Default.DEFAULT_VOXEL_INDICES_FRONT, new Vector3f(x, y, z));
                                            }
                                        }
                                        //BACK -z
                                        if (z - 1 >= 0) {
                                            if (isAir(new Vector3f(x, y, z - 1))) {
                                                addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_BACK, Default.DEFAULT_VOXEL_INDICES_BACK, new Vector3f(x, y, z));
                                            }
                                        } else {
                                            try {
                                                ChunkSection chunkSectionToCheck = parrentChunk.getParrentWorld().getChunk(Vector2f.sub(parrentChunk.getChunkPos(), new Vector2f(0, 1))).getChunkSection(sectionHeight);
                                                if (chunkSectionToCheck.isAir(new Vector3f(x, y, StaticData.SECTION_SIZE - 1))) {
                                                    addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_BACK, Default.DEFAULT_VOXEL_INDICES_BACK, new Vector3f(x, y, z));
                                                }
                                            } catch (Exception e) {
                                                addFace(vertices,indices,Default.DEFAULT_VOXEL_VERTICES_BACK, Default.DEFAULT_VOXEL_INDICES_BACK, new Vector3f(x, y, z));
                                            }
                                        }

                                    } catch (Exception e) {
                                        Console.printError("Error while generating faces for Voxel!", new Vector3f(x, y, z).toString());
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

                    vertices.clear();
                    indices.clear();

                    //TODO Add atlas texture rendering
                    Mesh returnMesh = new Mesh(newVertices, newIndices, new Material("assets/pv/textures/testPic.png"));
                    MainThreadExecutor.runLater(() -> {
                        if (this.getMesh() != null) {this.getMesh().destroy();}
                        returnMesh.create();
                        setMesh(returnMesh);
                        callback.onResult(returnMesh);
                        this.needsReloading = false;
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        public void asyncGenerateMesh() {
            meshThreadPool.submit(() -> {
                generateMesh(result -> setMesh(result));
            });
            //TODO Add thread debug menu like this.
//            ThreadPoolExecutor executor = (ThreadPoolExecutor) meshThreadPool;
//
//            System.out.println("Active Threads: " + executor.getActiveCount());
//            System.out.println("Pool Size: " + executor.getPoolSize());
//            System.out.println("Completed Tasks: " + executor.getCompletedTaskCount());
//            System.out.println("Task Count: " + executor.getTaskCount());
//            System.out.println("Is Shutdown: " + executor.isShutdown());
        }

        private boolean isAir(Vector3f pos) {
            if (voxels[(int) pos.getX()][(int) pos.getY()][(int) pos.getZ()].getVoxelID().equalsIgnoreCase("air")) {
                return true;
            } else {
                return false;
            }
        }

        public void render(Renderer renderer, Player player) {
            if (getMesh() == null) {return;}
            renderer.renderGameObject(this,player.getCamera());
        }

        public void setNeedsReloading() {
            if (!needsReloading) {
                this.needsReloading = true;
                parrentChunk.getParrentWorld().setNeedsReloading(this);
            }
        }

        public void destroy() {
            getMesh().destroy();
        }

        private void addFace(ArrayList<Vertex> vertices, ArrayList<Integer> indices, Vertex[] faceVerticesTemplate, int[] faceIndicesTemplate, Vector3f voxelPosition) {
            int baseVertexIndex = vertices.size();

            for (int i = 0; i < faceVerticesTemplate.length; i++) {
                Vertex templateVertex = faceVerticesTemplate[i];
                Vector3f templatePos = templateVertex.getPos();
                Vector3f newPos = Vector3f.add(templatePos,voxelPosition);
                //TODO Calculate texture cords via atlas texture.
                vertices.add(new Vertex(newPos, templateVertex.getTextureCord()));
            }
            for (int i = 0; i < faceIndicesTemplate.length; i++) {
                indices.add(faceIndicesTemplate[i] + baseVertexIndex);
            }
        }

        public void setVoxel(Vector3f voxelSectionCords, String voxelID) {
            try {
                voxels[(int) voxelSectionCords.getX()][(int) voxelSectionCords.getY()][(int) voxelSectionCords.getZ()].setVoxelID(voxelID);
                setNeedsReloading();
            } catch (Exception e) {
                Console.printError("Could not set voxel to \"" + voxelID + "\"", voxelSectionCords);
            }

        }

        public Voxel getVoxel(Vector3f voxelSectionPos) {
            return voxels[(int) voxelSectionPos.getX()][(int) voxelSectionPos.getY()][(int) voxelSectionPos.getZ()];
        }

        interface ResultCallback {
            void onResult(Mesh mesh);
        }
    }
