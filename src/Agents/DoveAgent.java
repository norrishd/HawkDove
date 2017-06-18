package Agents;

import GameLogic.Position;

/**
 * Doves are peaceful agents that never attack and use a pure Dove strategy
 */
public abstract class DoveAgent extends Agent {

    public DoveAgent(Position spawnLocation, String name) {

        super(spawnLocation, name);
    }

}
