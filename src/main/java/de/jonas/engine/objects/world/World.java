package de.jonas.engine.objects.world;

import de.jonas.engine.data.PVData;
import de.jonas.engine.data.UserData;
import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.graphics.Renderer;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.game.GameObject;
import de.jonas.engine.objects.game.player.Player;
import de.jonas.engine.utils.Console;

public class World {
    private Chunk[][] chunks = new Chunk[getChunkAmountDiameter()][getChunkAmountDiameter()];

    public void reloadChunks() {
        chunks = new Chunk[getChunkAmountDiameter()][getChunkAmountDiameter()];
        generateChunks();
    }

    public void generateChunks() {
        for (int i = 0;i < getChunkAmountDiameter();i++) {
            for (int j = 0;j < getChunkAmountDiameter();j++) {
                chunks[i][j] = new Chunk(new Vector2f(i,j));
            }
        }
    }

    public void reloadMeshes() {
        for (int i = 0;i < getChunkAmountDiameter();i++) {
            for (int j = 0;j < getChunkAmountDiameter();j++) {
                chunks[i][j].reloadMeshes();
            }
        }
    }

    public void renderMeshes(Renderer renderer, Player player) {
        for (int i = 0;i < getChunkAmountDiameter();i++) {
            for (int j = 0;j < getChunkAmountDiameter();j++) {
                chunks[i][j].render(renderer,player);
            }
        }
    }

    public void destroy() {
        for (int i = 0;i < getChunkAmountDiameter();i++) {
            for (int j = 0;j < getChunkAmountDiameter();j++) {
                chunks[i][j].destroy();
            }
        }
    }

    public int getChunkAmountDiameter() {return 2 * UserData.RENDER_DISTANCE + 1;}

    public Chunk getChunk(Vector2f chunkPos) {return chunks[(int) chunkPos.getX()][(int)  chunkPos.getY()];}

    public Voxel getVoxel(Vector3f worldPos) {
        Vector2f chunkPos = new Vector2f(worldPos.getX() / PVData.SECTION_SIZE,worldPos.getZ() / PVData.SECTION_SIZE);
        Chunk chunk = getChunk(chunkPos);
        int sectionHeight = (int) worldPos.getY() / PVData.SECTION_SIZE;
        ChunkSection chunkSection = chunk.getChunkSection(sectionHeight);
        Voxel voxel = chunkSection.getVoxel(new Vector3f(worldPos.getX() - (chunkPos.getX() * PVData.SECTION_SIZE),worldPos.getY() - (sectionHeight * PVData.SECTION_SIZE),worldPos.getZ() - (chunkPos.getY() * PVData.SECTION_SIZE)));
        return voxel;
    }

    public void setVoxel(Vector3f worldPos, String voxelID) {
        Vector2f chunkPos = new Vector2f((int) (worldPos.getX() / PVData.SECTION_SIZE),(int) (worldPos.getZ() / PVData.SECTION_SIZE));
        Chunk chunk = getChunk(chunkPos);
        int sectionHeight = (int) worldPos.getY() / PVData.SECTION_SIZE;
        ChunkSection chunkSection = chunk.getChunkSection(sectionHeight);
        chunkSection.setVoxel(new Vector3f(worldPos.getX() - (chunkPos.getX() * PVData.SECTION_SIZE),worldPos.getY() - (sectionHeight * PVData.SECTION_SIZE),worldPos.getZ() - (chunkPos.getY() * PVData.SECTION_SIZE)),voxelID);

        System.out.println(worldPos);
        System.out.println(chunkPos);
        System.out.println(worldPos.getX() - (chunkPos.getX() * PVData.SECTION_SIZE));
        System.out.println(new Vector3f(worldPos.getX() - (chunkPos.getX() * PVData.SECTION_SIZE),worldPos.getY() - (sectionHeight * PVData.SECTION_SIZE),worldPos.getZ() - (chunkPos.getY() * PVData.SECTION_SIZE)));
    }

}
