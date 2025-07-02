package de.jonas.engine.objects;

import de.jonas.engine.data.UserData;
import de.jonas.engine.graphics.Renderer;
import de.jonas.engine.graphics.Shader;
import de.jonas.engine.io.Window;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.game.player.Player;
import de.jonas.engine.objects.world.Chunk;
import de.jonas.engine.objects.world.Voxel;
import de.jonas.engine.objects.world.World;
import de.jonas.engine.utils.Console;

public class Scene {

    private Renderer renderer;
    private Player player;
    private World world = new World();

    public Scene(Vector3f spawnPos, Window window, Shader defaultShader) {
        renderer = new Renderer(window,defaultShader);
        player = new Player(spawnPos,new Vector3f(0,0,0));
        world.reloadChunks();
    }

    public void reload() {
        //world.reloadChunks();
        world.reloadMeshes();
    }

    public void render() {
        world.renderMeshes(renderer,player);
    }

    public void update() {
        player.update();
    }

    public void destroy() {
        world.destroy();
    }


    public void test() {
        Console.printDebug("Running scene test...",true);
        try {
            world.setVoxel(player.getPosition(),"air");
        } catch (Exception e) {
            Console.printWarn("Could not place block!",player.getPosition());
        }
        reload();
    }


}
