package de.jonas.engine.objects;

import de.jonas.engine.data.UserData;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.game.player.Player;
import de.jonas.engine.objects.world.Chunk;
import de.jonas.engine.utils.Console;

public class Scene {
    private Chunk[] chunks = new Chunk[getChunkAmount()];
    private Player player = new Player(new Vector3f(0,0,0),new Vector3f(0,0,0));

    public Scene() {
        reloadChunks();
    }

    //TODO Add render function
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

    public void generateChunk(Vector2f chunkPos) {
        chunks[getChunkIndex(chunkPos)] = new Chunk(chunkPos);
    }

    //TODO Fix Code
    public int getChunkIndex(Vector2f chunkPos) {
        Vector2f indexZeroChunkPos = chunks[0].getChunkPos();
        Vector2f indexMaxChunkPos = chunks[chunks.length - 1].getChunkPos();
        int maxXPos = (int) indexMaxChunkPos.getX();
        int maxYPos = (int) indexMaxChunkPos.getY();
        int minXPos = (int) indexZeroChunkPos.getX();
        int minYPos = (int) indexZeroChunkPos.getY();
        int chunkXPos = (int) chunkPos.getX();
        int chunkYPos = (int) chunkPos.getY();
        if (chunkXPos >= minXPos && chunkXPos <= maxXPos && chunkYPos <= minYPos && chunkYPos >= maxYPos) {
            chunkXPos -= minXPos;
            chunkYPos -= minYPos;
            return Math.abs(chunkXPos + (chunkYPos * getChunkAmountDiameter()));
        } else {
            Console.printError("Chunk is not loaded (out of bounds)!",chunkPos.toString());
            return -1;
        }
    }

    public int getChunkAmountDiameter() {
        return 2 * UserData.RENDER_DISTANCE + 1;
    }

    public int getChunkAmount() {
        return (int) Math.pow(getChunkAmountDiameter(),2);
    }

    public Chunk getChunk(int index) {
        return chunks[index];
    }

    public Chunk getChunk(Vector2f chunkPos) {
        return chunks[getChunkIndex(chunkPos)];
    }

}
