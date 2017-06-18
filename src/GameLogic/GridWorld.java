package GameLogic;

import Agents.Agent;
import Tiles.FloorTile;
import Tiles.Tile;
import Tiles.TilePattern;
import Tiles.WallTile;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A grid world, containing all tiles, agents and game logic
 */
public class GridWorld extends WorldSettings {

    Tile[][] tiles;             // store all board pieces
    ArrayList<Agent> agents;    // store all agents in play
    private Random r = new Random();

    GridWorld() {
        super();
        this.tiles = new Tile[WORLD_TILE_HEIGHT][WORLD_TILE_WIDTH];     // allocate memory for all needed tiles
    }

    /**
     * Populate the matrix of Tiles for this GridWOrld
     * @param pattern a TilePattern enum, indicating what the world will look like
     */
    void generateWorld(TilePattern pattern) {

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

                            default:
                                tiles[x][y] = new FloorTile(new Position(x, y), "Floor1.png");
                                break;
                    }
                }
            }
        }
    }

    // Add an agent to a specific tile on the map
    void addAgent(Agent newVisitor) throws Exception {
        Position spawnLocation = newVisitor.position;
        if (spawnLocation.x < 0 || spawnLocation.x > WORLD_TILE_WIDTH - 1 ||
                spawnLocation.y < 0 || spawnLocation.y > WORLD_TILE_HEIGHT - 1)
            throw new IndexOutOfBoundsException("That's outside the world!");
        else if (tiles[spawnLocation.x][spawnLocation.y] == null)
            throw new NoSuchElementException("World tiles haven't been initiated yet");
        else if (!tiles[spawnLocation.x][spawnLocation.y].walkable())
            throw new IllegalArgumentException("Cannot spawn an Agent on this kind of tile");
        tiles[spawnLocation.x][spawnLocation.y].addAgent(newVisitor);
    }

    // Get random walkable tile
    Position getWalkableTile() {
        if (tiles.length == 0)
            throw new NoSuchElementException("World tiles haven't been initiated yet.");

        int x = r.nextInt(WORLD_TILE_WIDTH);
        int y = r.nextInt(WORLD_TILE_HEIGHT);

        // DANGER! Infinite loop!
        while (!tiles[x][y].walkable()) {
            x = r.nextInt(WORLD_TILE_WIDTH);
            y = r.nextInt(WORLD_TILE_HEIGHT);
        }

        return new Position(x, y);
    }

    // User pressed button for next turn
    void nextTurn() {
        for (Agent agent : agents) {
            agent.checkForAdjacentFood(tiles, DFSlimit);
            agent.move();
        }
    }
}
