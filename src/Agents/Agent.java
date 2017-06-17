package Agents;

import java.util.HashMap;
import java.util.Map;


/**
 * Abstract class for agents to navigate the environment
 */
public abstract class Agent {

    // Vars for x and y coordinates, energy, and previously visited locations
    int x, y;
    int energy;
    private int steps;
    Map<Location, Integer> visitedLocations = new HashMap<>();

    public Agent(Location spawnLocation) {
        this.x = spawnLocation.x;
        this.y = spawnLocation.y;
        this.energy = 3;
    }



    /**
     * Add or remove energy from agent
     * @param deltaEnergy amount of energy to add or subtract
     */
    private void changeEnergy(int deltaEnergy) {
        energy += deltaEnergy;
    }

    /**
     * Move agent to a new location
     * @param newLocation new GameLogic.Location to move to
     */
    void move(Location newLocation) {
        x = newLocation.x;
        y = newLocation.y;
        steps++;
        if (steps >= 100) {
            steps = 0;
            changeEnergy(-1);
        }

        // Increment time-since-visited for all past visited Locations
        for (Location location : visitedLocations.keySet()) {
            int newVal = visitedLocations.get(location) + 1;
            visitedLocations.put(location, newVal);
        }
        // Update newly visited location
        visitedLocations.put(newLocation, 0);
    }
}
