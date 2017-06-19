package Tests;
import Agents.DoveAgent;
import GameLogic.*;

import Tiles.TilePattern;
import Tiles.WallTile;
import org.junit.Test;

/**
 * Created by Noosh on 19/06/17.
 */
public class Tests {

    @Test
    public void makeGridWorld() {
        GridWorld gridWorld = new GridWorld();

        for (TilePattern pattern : TilePattern.values()) {
            gridWorld.generateWorld(pattern);

            System.out.println(pattern);
            drawWorld(gridWorld);
            System.out.println();
        }
    }

    // Helper function to draw an ASCII picture of the world
    public void drawWorld(GridWorld gridWorld) {
        for (int i = 0; i < gridWorld.tiles.length; i++) {
            for (int j = 0; j < gridWorld.tiles[0].length; j++) {
                if (!gridWorld.tiles[i][j].walkable())
                    System.out.print("X ");
                else {
                    if (gridWorld.tiles[i][j].agents.size() > 0)
                        System.out.println("o");
                    else
                        System.out.print("_ ");
                }
            }
            System.out.println("");
        }
    }

    @Test
    public void spawnAgent() {
        GridWorld gridWorld = new GridWorld();
        gridWorld.generateWorld(TilePattern.OPEN_FIELD);
        Position spawnLocation = gridWorld.getWalkableTile();
        System.out.println("Agent to spawn at " + spawnLocation.getCoords());
        DoveAgent adam = new DoveAgent(spawnLocation, "Adam");
        gridWorld.addAgent(adam);


    }
}
