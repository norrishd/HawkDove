package Agents;

import Tiles.Position;

/**
 * Hawks are aggressive agents that always attack
 */
public class HawkAgent extends Agent {

    public HawkAgent(Position spawnLocation, String name) {

        super(spawnLocation, name);
        this.strategy = Strategy.HAWK;
    }

}
