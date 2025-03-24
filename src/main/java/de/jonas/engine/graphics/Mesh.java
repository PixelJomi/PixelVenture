package de.jonas.engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {
    //Variables
    private Vertex[] vertices;
    private int[] indices;
    private int vao, pbo, ibo; //Vertex Array Object (abo), Position Buffer Object (vbo), Indices Buffer Object (ibo)
    //Constructor
    public Mesh(Vertex[] vertices,int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }
    //Methods
    public void create() {
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        FloatBuffer posBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] posData = new float[vertices.length *3];

        for (int i = 0; i < vertices.length; i++) {
            posData[i * 3] = vertices[i].getPos().getX();
            posData[i * 3 + 1] = vertices[i].getPos().getY();
            posData[i * 3 + 2] = vertices[i].getPos().getZ();
        }

        posBuffer.put(posData).flip();

        pbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, pbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, posBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0,3, GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();

        ibo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,0);
    }
    //Getters
    public Vertex[] getVertices() {return vertices;}
    public int[] getIndices() {return indices;}
    public int getVAO() {return vao;}
    public int getPBO() {return pbo;}
    public int getIBO() {return ibo;}
}
