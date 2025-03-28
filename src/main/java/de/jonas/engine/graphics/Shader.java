package de.jonas.engine.graphics;

import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.FileUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shader {
    private String vertexFile,fragmentFile;
    private int vertexID,fragmentID,programID;

    public Shader(String vertexPath, String fragmentPath) {
        vertexFile = FileUtils.loadAsString(vertexPath);
        fragmentFile = FileUtils.loadAsString(fragmentPath);
    }

    public void create() {
        programID = GL20.glCreateProgram();

        vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexID,vertexFile);
        GL20.glCompileShader(vertexID);

        if (GL20.glGetShaderi(vertexID,GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            Console.printError("Could not compile vertex shader!",vertexID);
            return;
        }


        fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);



    }
}
