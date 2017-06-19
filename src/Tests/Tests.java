package Tests;
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
            for (int i = 0; i < gridWorld.tiles.length; i++) {
                for (int j = 0; j < gridWorld.tiles[0].length; j++) {
                    if (gridWorld.tiles[i][j] instanceof WallTile)
                        System.out.print("X ");
                    else
                        System.out.print("_ ");
                }
                System.out.println("");
            }
            System.out.println();
        }
    }
}
