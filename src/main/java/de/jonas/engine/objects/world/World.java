package de.jonas.engine.objects.world;

import de.jonas.engine.data.UserData;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.utils.Console;

public class World {
    private Chunk[] chunks = new Chunk[getChunkAmount()];

    public void render() {

    }

    public void reloadChunks() {
        chunks = new Chunk[getChunkAmount()];
        generateChunks();
    }

    public void generateChunks() {
        for (int i = 0;i < chunks.length;i++) {
            chunks[i] = new Chunk(calculateChunkPos(i));
        }
    }

    public Vector2f calculateChunkPos(int index) {
        int chunkYPos = index / getChunkAmountDiameter();
        int chunkXPos = index - (chunkYPos * getChunkAmountDiameter());
        return new Vector2f((float) chunkXPos,(float) (-chunkYPos));
    }

    public int getChunkAmountDiameter() {return 2 * UserData.RENDER_DISTANCE + 1;}

    public int getChunkAmount() {return (int) Math.pow(getChunkAmountDiameter(),2);}

    public Chunk getChunk(int index) {return chunks[index];}
}
