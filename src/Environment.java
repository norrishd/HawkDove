import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class to hold all location Objects
 */
class Environment {

    public Location[][] locations;
    private List<Agent> agents = new ArrayList<>();

    Environment(int boardSize, double barrierProb) {

        locations = new Location[boardSize][boardSize];

        // Generate edge barriers
        for (int x = 0; x < boardSize; x++) {
            locations[x][0] = new Location(x, 0, LocationType.BARRIER);
            locations[x][boardSize - 1] = new Location(x, boardSize - 1, LocationType.BARRIER);
        }
        for (int y = 1; y < boardSize - 1; y++) {
            locations[0][y] = new Location(0, y, LocationType.BARRIER);
            locations[boardSize - 1][y] = new Location(boardSize - 1, y, LocationType.BARRIER);
        }

        // Generate inner tiles
        Random r = new Random();
        for (int x = 1; x < boardSize - 1; x++) {
            for (int y = 1; y < boardSize - 1; y++) {
                LocationType newLocType;
                if (r.nextDouble() > barrierProb)
                    newLocType = LocationType.TILE;
                else newLocType = LocationType.BARRIER;
                locations[x][y] = new Location(x, y, newLocType);
            }
        }
    }

    void makeAgent(Agent newAgent) {
        agents.add(newAgent);
    }


    /**
     * Method to move all agents
     * First check for surrounding tiles which can be visited, then pick randomly from all unvisited tiles.
     * If all have been visited, pick from least frequently visited
     */
    void moveAgents() {
        for (Agent agent : agents) {
            Random r = new Random();
            // Get list of possible moves
            List<Location> legalMoves = getLegalMoves(agent.x, agent.y);

            // Create ArrayList to list best options for next move
            List<Location> bestVisitOptions = new ArrayList<>();
            int leastRecentVisit = 0;               // initialise to 0, which is worst possible value

            // Check surrounding legal tiles to find new or least recently visited
            for (Location location : legalMoves) {
                if (agent.visitedLocations.containsKey(location)) {
                    if (agent.visitedLocations.get(location) > leastRecentVisit) {
                        bestVisitOptions.clear();
                        bestVisitOptions.add(location);
                        leastRecentVisit = agent.visitedLocations.get(location);
                    }
                    // Not previously visited - will be amongst best options to visit
                    // 10,000 used as arbitrary high value to indicate not previously visited
                } else {
                    // Check if any other unvisited tiles discovered
                    if (leastRecentVisit < 10000) {     // previously visited (probably)
                        bestVisitOptions.clear();
                    }
                    bestVisitOptions.add(location);
                    leastRecentVisit = 10000;
                }
            }

            // Select a random option from the best options to visit
            int moveToIndex = r.nextInt(bestVisitOptions.size());
            Location locToMoveTo = bestVisitOptions.get(moveToIndex);

            agent.move(locToMoveTo);
        }
    }

    /**
     * Check surrounding 4 tiles to see whether they are visitable tiles
     * @param x - x board coordinate
     * @param y - y board coordinate
     * @return a List of visitable Location objects
     */
    private List<Location> getLegalMoves(int x, int y) {
        List<Location> legalMoves = new ArrayList<>();
        // Add current location
        legalMoves.add(locations[x][y]);
        // Check for other options
        if (locations[x - 1][y].type == LocationType.TILE)
            legalMoves.add(locations[x - 1][y]);
        if (locations[x +1][y].type == LocationType.TILE)
            legalMoves.add(locations[x + 1][y]);
        if (locations[x][y - 1].type == LocationType.TILE)
            legalMoves.add(locations[x][y - 1]);
        if (locations[x][y + 1].type == LocationType.TILE)
            legalMoves.add(locations[x][y + 1]);

        return legalMoves;
    }

}
