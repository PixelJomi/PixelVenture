package de.jonas.engine.objects.world;

import de.jonas.engine.data.StaticData;
import de.jonas.engine.graphics.Renderer;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.objects.game.player.Player;

public class Chunk {
    private Vector2f chunkPos;
    private ChunkSection[] chunkSections = new ChunkSection[StaticData.CHUNK_SECTION_AMOUNT];
    private World parrentWorld;

    public Chunk(Vector2f chunkPos, World parrentWorld) {
        this.chunkPos = chunkPos;
        this.parrentWorld = parrentWorld;
        generateSections();
    }

    public void generateSections() {
        for (int i = 0;i < chunkSections.length;i++){
            chunkSections[i] = new ChunkSection(this,(short) i);
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

    public World getParrentWorld() {return parrentWorld;}

    public ChunkSection getChunkSection(int sectionHeight) {return chunkSections[sectionHeight];}

    public Vector2f getChunkPos() {return chunkPos;}

}
