package Agents;

import GameLogic.Position;

/**
 * Hawks are aggressive agents that always attack
 */
public abstract class HawkAgent extends Agent {

    public HawkAgent(Position spawnLocation, String name) {

        super(spawnLocation, name);
    }

}
