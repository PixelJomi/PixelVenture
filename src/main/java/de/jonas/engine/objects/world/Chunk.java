package de.jonas.engine.objects.world;

import de.jonas.engine.data.PVData;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;

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

    public Vector2f getChunkPos() {return chunkPos;}

}
