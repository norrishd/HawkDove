package Agents;

/**
 * Doves are peaceful agents that never attack no matter what
 */
public class DoveAgent extends Agent {

    public DoveAgent(Location spawnLocation) {
        super(spawnLocation);
    }

    public Strategy strategy() {
        return Strategy.DOVE;
    }
}
