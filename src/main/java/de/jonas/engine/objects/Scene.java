package de.jonas.engine.objects;

import de.jonas.engine.data.UserData;
import de.jonas.engine.math.Vector2f;
import de.jonas.engine.math.Vector3f;
import de.jonas.engine.objects.game.player.Player;
import de.jonas.engine.objects.world.Chunk;
import de.jonas.engine.objects.world.World;
import de.jonas.engine.utils.Console;

public class Scene {

    private Player player = new Player(new Vector3f(0,0,0),new Vector3f(0,0,0));
    private World world = new World();

    public Scene() {
        world.reloadChunks();
    }


}
