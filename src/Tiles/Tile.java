package Tiles;

import GameLogic.Position;

/**
 * Class to represent board tiles.
 * Some will be walls, and some can be traversed by agents and may contain food
 */

public abstract class Tile {

    String image;
    Position position;

    public Tile(Position position, String image) {
        this.position = position;
        this.image = image;
    }

    public abstract boolean walkable();
}
