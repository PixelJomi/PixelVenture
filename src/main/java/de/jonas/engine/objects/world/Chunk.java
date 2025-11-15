package de.jonas.engine.objects.world;

import de.jonas.engine.data.StaticData;
import de.jonas.engine.graphics.Renderer;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.objects.game.player.Player;

public class Chunk {
    private final Vector2f chunkPos;
    private final ChunkSection[] chunkSections = new ChunkSection[StaticData.CHUNK_SECTION_AMOUNT];
    private final World parrentWorld;

    public Chunk(Vector2f chunkPos, World parrentWorld) {
        this.chunkPos = chunkPos;
        this.parrentWorld = parrentWorld;
        generateSections();
    }

    public final void generateSections() {
        for (int i = 0;i < chunkSections.length;i++){
            chunkSections[i] = new ChunkSection(this,(short) i);
        }
    }

    public void render(Renderer renderer, Player player) {

        for (ChunkSection chunkSection : chunkSections) {
            //TODO Add render check
            if (true) {
                chunkSection.render(renderer, player);
            }
        }
    }

    public void destroy() {
        for (ChunkSection chunkSection : chunkSections) {
            chunkSection.destroy();
        }
    }

    public World getParrentWorld() {return parrentWorld;}

    public ChunkSection getChunkSection(int sectionHeight) {return chunkSections[sectionHeight];}

    public Vector2f getChunkPos() {return chunkPos;}

}
