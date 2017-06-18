package Agents;

import Tiles.Position;

/**
 * Doves are peaceful agents that never attack and use a pure Dove strategy
 */
public class DoveAgent extends Agent {

    public DoveAgent(Position spawnLocation, String name) {

        super(spawnLocation, name);
        this.strategy = Strategy.DOVE;
    }

}
