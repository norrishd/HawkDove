package Tiles;

import Agents.Agent;
import GameLogic.Position;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A walkable Tiles. Agents can traverse, encounter other agents and possibly find food
 */
public class FloorTile extends Tile {

    private boolean hasFood;
    double fertility;           // [0-1], indicates likelihood of food spawning here if not picking tiles randomly

    // Default constructor if no value is provided for the fertility - will be generated uniform randomly
    public FloorTile(Position position, String image) {
        super(position, image);
        this.hasFood = false;
        Random r = new Random();        // Generate a fertility value using uniform random distribution
        fertility = r.nextDouble();
    }

    // Constructor if a fertility value is provided
    public FloorTile(Position position, String image, double fertility) {
        super(position, image);
        this.hasFood = false;
        this.fertility = fertility;
    }

    @Override
    public boolean walkable() {
        return true;
    }

    public boolean hasFood() {
        return hasFood;
    }

    public void growFood() {
        this.hasFood = true;
    }

    public void loseFood() {
        this.hasFood = false;
    }

    // Remove an agent from the tile (if they move away or die)
    public void removeAgent(Agent agent) throws Exception {
        if (this.agents.contains(agent))
            this.agents.remove(agent);
        else
            throw new NoSuchElementException("Tried to remove an agent from a tile they weren't on");
    }
}
