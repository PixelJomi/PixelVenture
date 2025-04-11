package de.jonas.engine.graphics;

import de.jonas.engine.utils.Console;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Objects;

public class Material {
    private String path;
    private float width, height;
    private int textureID;

    public Material(String path) {
        this.path = path;
    }

    public void create() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(2);

            InputStream inputStream = Material.class.getResourceAsStream(path);
            if (inputStream == null) {
                Console.printError("Resource not found!", path);
                return;
            }

            byte[] imageBytes = null;
            try {imageBytes = inputStream.readAllBytes();}
            catch (IOException e) {Console.printError(e.toString(), path);return;}
            ByteBuffer imageBuffer = ByteBuffer.wrap(imageBytes);

            ByteBuffer image = STBImage.stbi_load_from_memory(imageBuffer,w,h,channels,4);
            if (image == null) {
                Console.printError("Failed to load image: " + STBImage.stbi_failure_reason(),path);
                return;
            }

            width = w.get();
            height = h.get();

            textureID = GL13.glGenTextures();
            GL13.glBindTexture(GL13.GL_TEXTURE_2D, textureID);

            GL13.glTexParameteri(GL13.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
            GL13.glTexParameteri(GL13.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
            GL13.glTexParameteri(GL13.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL13.glTexParameteri(GL13.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

            GL13.glTexImage2D(GL13.GL_TEXTURE_2D, 0, GL11.GL_RGBA, (int) width, (int) height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);
            STBImage.stbi_image_free(image);
        } catch (Error error) {
            Console.printError("Can't find texture!",path);
            return;
        }
    }

    public void destroy() {
        GL13.glDeleteTextures(textureID);
    }

    public float getWidth(){return width;}
    public float getHeight(){return height;}
    public int getTextureID(){return textureID;}
}
