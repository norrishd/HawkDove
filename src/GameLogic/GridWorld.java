package GameLogic;

import Agents.Agent;
import Tiles.FloorTile;
import Tiles.Tile;
import Tiles.TilePattern;
import Tiles.WallTile;

import java.util.ArrayList;
import java.util.Random;

/**
 * A grid world, containing all tiles, agents and game logic
 */
public class GridWorld extends WorldSettings {

    Tile[][] tiles;             // store all board pieces
    ArrayList<Agent> agents;    // store all agents in play

    public GridWorld() {
        super();
        this.tiles = new Tile[WORLD_TILE_HEIGHT][WORLD_TILE_WIDTH];      // allocate memory for all needed tiles
    }


    public void generateWorld(TilePattern pattern) {

        Random r = new Random();

        for (int x = 0; x < WORLD_TILE_WIDTH; x++) {
            for (int y = 0; y < WORLD_TILE_HEIGHT; y++) {
                // Make all perimeter Tiles into Walls
                if (x == 0 || x == WORLD_TILE_WIDTH - 1 || y == 0 || y == WORLD_TILE_HEIGHT) {
                    // TODO add tile sprites and randomise different varieties
                    tiles[x][y] = new WallTile(new Position(x, y), "Wal11.png");
                } else {
                    switch (pattern) {
                        case OPEN_FIELD:
                            tiles[x][y] = new FloorTile(new Position(x, y), "Floor1.png");
                            break;

                        case RANDOM_SPARSE:
                            tiles[x][y] = r.nextDouble() > 0.9 ? new WallTile(new Position(x, y), "Wal11.png") :
                                    new FloorTile(new Position(x, y), "Floor1.png");
                            break;

                        case RANDOM_DENSE:
                            tiles[x][y] = r.nextDouble() > 0.65 ? new WallTile(new Position(x, y), "Wal11.png") :
                                    new FloorTile(new Position(x, y), "Floor1.png");
                            break;

                        case CORRIDORS:
                            boolean gapIncluded = false;
                            if (x % 2 == 1)     // every odd row should be clear
                                new FloorTile(new Position(x, y), "Floor1.png");
                            else {
                                // If at last position where there could be a gap, make sure there is at least one
                                if (x == WORLD_TILE_WIDTH - 2) {
                                    for (int i = 0; i < x; i++)
                                        if (tiles[i][y] instanceof FloorTile)
                                            gapIncluded = true;
                                    if (!gapIncluded)
                                        tiles[x][y] = new FloorTile(new Position(x, y), "Floor1.png");
                                    else {
                                        tiles[x][y] = r.nextDouble() < (1 / (WORLD_TILE_WIDTH - 2)) ?
                                                new FloorTile(new Position(x, y), "Floor1.png") :
                                                new WallTile(new Position(x, y), "Wal11.png");
                                    }
                                } else {        // randomly assign a gap with E(# gaps) = 1 per row
                                    tiles[x][y] = r.nextDouble() < (1 / (WORLD_TILE_WIDTH - 2)) ?
                                            new FloorTile(new Position(x, y), "Floor1.png") :
                                            new WallTile(new Position(x, y), "Wal11.png");
                                }
                            }
                            break;
                    }
                }
            }
        }
    }
}
