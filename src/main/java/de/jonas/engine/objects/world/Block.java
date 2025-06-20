package de.jonas.engine.objects.world;

import de.jonas.engine.data.blocks.Default;
import de.jonas.engine.graphics.Material;
import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.objects.game.GameObject;
import de.jonas.main.TestingGrounds;

public class Block{
    private String blockId;

    public Block(String blockId) {
        this.blockId = blockId;
    }

    public String getBlockId() {return blockId;}
}
