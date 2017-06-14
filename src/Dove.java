/**
 * Doves are peaceful agents that never attack
 */
public class Dove extends Agent {

    public Dove(Location spawnLocation) {
        super(spawnLocation);
    }

    public Strategy strategy() {
        return Strategy.DOVE;
    }
}
