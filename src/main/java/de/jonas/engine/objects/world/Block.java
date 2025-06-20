package de.jonas.engine.objects.world;

import de.jonas.engine.data.blocks.Default;
import de.jonas.engine.graphics.Material;
import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.objects.game.GameObject;
import de.jonas.main.TestingGrounds;

public class Block{
    private String blockName;
    private Mesh mesh;

    public Block(String blockName,String type) {
        this.blockName = blockName;
        TestingGrounds.add();
        //TODO add block mesh types.
        mesh = new Mesh(Default.DEFAULT_VERTICES, Default.DEFAULT_INDICES, new Material("textures/testPic.png"));
    }
}
