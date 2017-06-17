package Tiles;

import Agents.Agent;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A walkable Tiles. Agents can traverse these, encounter other agents and possibly find food
 */
public class FloorTile extends Tile {

    boolean hasFood;
    double fertility;       // [0-1], indicates likelihood of food spawning here each turn
    ArrayList<Agent> agents;

    // Default constructor if no value is provided for the fertility. One will be generated uniform randomly
    public FloorTile(Position position, String image) {
        super(position, image);
        this.hasFood = false;
        this.agents = new ArrayList<>();
        Random r = new Random();        // Generate a fertility value using uniform random distribution
        fertility = r.nextDouble();
    }

    // Constructor if a fertility value is provided
    public FloorTile(Position position, String image, double fertility) {
        super(position, image);
        this.hasFood = false;
        this.agents = new ArrayList<>();
        this.fertility = fertility;
    }

    @Override
    public boolean walkable() {
        return true;
    }

    public void growFood() {
        this.hasFood = true;
    }

    public void loseFood() {
        this.hasFood = false;
    }

    public void agentArrives(Agent agent) {
        this.agents.add(agent);
    }

    // Remove an agent from the tile (if they move away or die)
    public void agentLeaves(Agent agent) throws Exception {
        if (this.agents.contains(agent))
            this.agents.remove(agent);
        else
            throw new NoSuchElementException("Tried to remove an agent from a tile they weren't on");
    }
}
