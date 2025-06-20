package de.jonas.main;

import de.jonas.engine.data.PVData;
import de.jonas.engine.data.UserData;
import de.jonas.engine.data.blocks.Default;
import de.jonas.engine.graphics.Mesh;
import de.jonas.engine.graphics.Vertex;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.Scene;
import de.jonas.engine.objects.world.ChunkSection;
import de.jonas.engine.utils.Console;

public class TestingGrounds {
    public static int amount = 0;
    public static void main(String[] args) {
        UserData.loadData();

        ChunkSection chunkSection = new ChunkSection((short) 16);

        Mesh mesh = chunkSection.generateMesh(new Vector2f(1f,1f));

        Vertex[] vertices = mesh.getVertices();

//        for (int i = 0; i < vertices.length;i++) {
//            System.out.println(vertices[i].getPos().toString());
//        }

    }

    public static void add() {
        amount++;
    }
}