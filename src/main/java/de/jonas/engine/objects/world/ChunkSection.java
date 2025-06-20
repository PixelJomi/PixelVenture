package de.jonas.engine.objects.world;

import de.jonas.engine.data.PVData;

public class ChunkSection {
    private short sectionHeight;
    private Block[] blocks = new Block[PVData.SECTION_SIZE * PVData.SECTION_SIZE * PVData.SECTION_SIZE];

    public ChunkSection(short sectionHeight) {
        this.sectionHeight = sectionHeight;
        generateBlocks();
    }

    public void generateBlocks() {
        for (int i = 0;i < blocks.length;i++) {
            blocks[i] = new Block("Stone");
        }
    }
}
