package de.jonas.engine.objects.world;

import de.jonas.engine.data.PVData;
import de.jonas.engine.graphics.Material;
import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.graphics.Renderer;
import de.jonas.engine.graphics.Vertex;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.objects.game.player.Player;
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
            chunkSections[i] = new ChunkSection(chunkPos,(short) i);
        }
    }

    public void reloadMeshes() {
        for (int i = 0;i < chunkSections.length;i++){
            chunkSections[i].generateMesh();
        }
    }


    public void render(Renderer renderer, Player player) {
        for (int i = 0;i < chunkSections.length;i++){
            //TODO Add render check
            if (true) {
                chunkSections[i].render(renderer,player);
            }
        }
    }


    public void destroy() {
        for (int i = 0;i < chunkSections.length;i++){
            chunkSections[i].destroy();
        }
    }

    public ChunkSection getChunkSection(int sectionHeight) {return chunkSections[sectionHeight];}

    public Vector2f getChunkPos() {return chunkPos;}

}
