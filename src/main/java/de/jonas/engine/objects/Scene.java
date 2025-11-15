package de.jonas.engine.objects;

import de.jonas.engine.graphics.Renderer;
import de.jonas.engine.graphics.Shader;
import de.jonas.engine.io.Window;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.game.player.Player;
import de.jonas.engine.objects.world.World;
import de.jonas.engine.utils.Console;

public class Scene {

    private final Renderer renderer;
    private final Player player;
    private final World world = new World();

    public Scene(Vector3f spawnPos, Window window, Shader defaultShader) {
        renderer = new Renderer(window,defaultShader);
        player = new Player(spawnPos,new Vector3f(0,0,0));
        world.regenerateChunks();
    }

    public void reload() { //TODO Rename to update once the player updating is fixed.
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
        for (int x = 0;x < 5;x++) {
            for (int y = 0;y < 5;y++) {
                for (int z = 0;z < 5;z++) {
                    try {
                        world.setVoxel(Vector3f.add(player.getPosition(),new Vector3f(x,y,z)),"air");
                    } catch (Exception e) {
                        Console.printWarn("Could not place block!",player.getPosition());
                    }
                }
            }
        }

        //reload();
    }


}
