package Agents;

/**
 * Hawks are aggressive agents that always attack
 */
public class Hawk extends Agent {

    public Hawk(Location spawnLocation) {

        super(spawnLocation);
    }

    Strategy strategy() {
        return Strategy.HAWK;
    }
}
