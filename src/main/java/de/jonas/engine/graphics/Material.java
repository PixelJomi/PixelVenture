package de.jonas.engine.graphics;

import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.FileUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Material {
    private String pathString;
    private float width, height;
    private int textureID;

    public Material(String path) {this.pathString = path;}

    public void create() {
        try (MemoryStack stack = MemoryStack.stackPush();
             InputStream inputStream = FileUtils.loadAsStream(pathString)) {

            if (inputStream == null) {
                Console.printError("Resource not found!", pathString);
                return;
            }

            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            byte[] imageBytes = inputStream.readAllBytes();

            ByteBuffer imageBuffer = ByteBuffer.allocateDirect(imageBytes.length);
            imageBuffer.put(imageBytes).flip();

            ByteBuffer image = STBImage.stbi_load_from_memory(imageBuffer, w, h, channels, 4);
            if (image == null) {
                Console.printError("Failed to load image: " + STBImage.stbi_failure_reason(), pathString);
                return;
            }

            width = w.get(0);
            height = h.get(0);
            int actualChannels = channels.get(0);

            textureID = GL13.glGenTextures();
            GL13.glBindTexture(GL13.GL_TEXTURE_2D, textureID);

            GL13.glTexParameteri(GL13.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
            GL13.glTexParameteri(GL13.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
            GL13.glTexParameteri(GL13.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL13.glTexParameteri(GL13.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

            int format = (actualChannels == 4) ? GL11.GL_RGBA : GL11.GL_RGB;

            GL13.glTexImage2D(GL13.GL_TEXTURE_2D, 0, format, w.get(0), h.get(0), 0, format, GL11.GL_UNSIGNED_BYTE, image);
            STBImage.stbi_image_free(image);

        } catch (IOException e) {
            Console.printError("IOException while reading image: " + e.getMessage(), pathString);
        } catch (Exception e) {
            Console.printError("An unexpected error occurred: " + e.getMessage(), pathString);
        }
    }


    public void destroy() {
        if (textureID != 0) {
            GL13.glDeleteTextures(textureID);
            textureID = 0;
        }
    }

    public float getWidth() {return width;}

    public float getHeight() {return height;}

    public int getTextureID() {return textureID;}
}