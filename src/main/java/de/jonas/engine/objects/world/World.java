package de.jonas.engine.objects.world;

import de.jonas.engine.data.StaticData;
import de.jonas.engine.data.UserData;
import de.jonas.engine.graphics.Renderer;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.game.player.Player;
import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.MainThreadExecutor;

import java.util.ArrayList;

public class World {
    private Chunk[][] chunks = new Chunk[getChunkAmountDiameter()][getChunkAmountDiameter()];
    private ArrayList<ChunkSection> sectionsNeedingReload = new ArrayList<ChunkSection>();

    public void regenerateChunks() {
        chunks = new Chunk[getChunkAmountDiameter()][getChunkAmountDiameter()];
        for (int i = 0;i < getChunkAmountDiameter();i++) {
            for (int j = 0;j < getChunkAmountDiameter();j++) {
                chunks[i][j] = new Chunk(new Vector2f(i,j),this);
            }
        }
    }

    public void reloadMeshes() {
        if (sectionsNeedingReload.size() > 0) {
            Console.printDebug("sectionsNeedingReload: ",sectionsNeedingReload.size());

            ArrayList<ChunkSection> toReload = (ArrayList<ChunkSection>) sectionsNeedingReload.clone();
            sectionsNeedingReload.clear();
            for (int ind = 0;ind < toReload.size();ind++) {toReload.get(ind).asyncGenerateMesh();}
            toReload.clear();
            MainThreadExecutor.executeAll();
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

    public Chunk getChunk(Vector2f chunkPos) {
        return chunks[(int) chunkPos.getX()][(int) chunkPos.getY()];
    }

    public void setNeedsReloading(ChunkSection sectionNeedingReload) {
        sectionsNeedingReload.add(sectionNeedingReload);
    }

    public Voxel getVoxel(Vector3f worldPos) {
        Vector2f chunkPos = new Vector2f(worldPos.getX() / StaticData.SECTION_SIZE,worldPos.getZ() / StaticData.SECTION_SIZE);
        Chunk chunk = getChunk(chunkPos);
        int sectionHeight = (int) worldPos.getY() / StaticData.SECTION_SIZE;
        ChunkSection chunkSection = chunk.getChunkSection(sectionHeight);
        Voxel voxel = chunkSection.getVoxel(new Vector3f(worldPos.getX() - (chunkPos.getX() * StaticData.SECTION_SIZE),worldPos.getY() - (sectionHeight * StaticData.SECTION_SIZE),worldPos.getZ() - (chunkPos.getY() * StaticData.SECTION_SIZE)));
        return voxel;
    }

    public void setVoxel(Vector3f worldPos, String voxelID) {
        Vector2f chunkPos = new Vector2f((int) (worldPos.getX() / StaticData.SECTION_SIZE),(int) (worldPos.getZ() / StaticData.SECTION_SIZE));
        Chunk chunk = getChunk(chunkPos);
        int sectionHeight = (int) worldPos.getY() / StaticData.SECTION_SIZE;
        ChunkSection chunkSection = chunk.getChunkSection(sectionHeight);
        chunkSection.setVoxel(new Vector3f(worldPos.getX() - (chunkPos.getX() * StaticData.SECTION_SIZE),worldPos.getY() - (sectionHeight * StaticData.SECTION_SIZE),worldPos.getZ() - (chunkPos.getY() * StaticData.SECTION_SIZE)),voxelID);
    }

}
