package Agents;

import GameLogic.Position;
import GameLogic.SearchNode;
import Tiles.Tile;

import java.util.*;

/**
 * Abstract class for agents which navigate navigate the environment
 */
public abstract class Agent {

    // Strategy, starting position etc
    public String name;
    public Position position;
    private Position last_pos = null;   // previous position agent was at. Prefers spawning here, and not re-visiting
    private Position next_pos = null;
    private int food;
    private int steps_taken;            // way to keep track how long an agent has survived
    private int children_spawned;

    private ArrayList<Position> adjacentFood;
    private SearchNode goal;              // identified food, currently aiming to collect
    private HashMap<Agent, ArrayList<Boolean>> pastEncounters;      // remember outcomes of past encounters with agents
    private Random r = new Random();

    Agent(Position spawnLocation, int startingFood, String name) {
        this.position = spawnLocation;
        this.food = startingFood;
        this.steps_taken = 0;
        this.children_spawned = 0;
        this.name = name;
        this.pastEncounters = new HashMap<>();
        this.adjacentFood = new ArrayList<>();
    }

    // Identifies any adjacent tiles with food
    public void checkForAdjacentFood(Tile[][] tiles, int max_depth) {
        ArrayList<Position> updatedAdjFood = new ArrayList<>();
        Position[] adjacents = getAdjacents(this.position);

        for (Position pos : adjacents) {
            if (tiles[pos.x][pos.y].hasFood())
                updatedAdjFood.add(pos);
        }
        adjacentFood = updatedAdjFood;

        // If any adjacent square has food, move to one randomly
        if (adjacentFood.size() > 0) {
            int choice = r.nextInt(adjacentFood.size());
            next_pos = adjacentFood.get(choice);
            // if no adjacent food, check if there's still food at the goal and if so move towards it
        } else if (goal != null && goal.tile.hasFood()) {
            next_pos = findNextTilePos(goal);
          // else depth-limited DFS to find nearest food
        } else {
            HashMap<Tile, SearchNode> discovered = new HashMap<>();        // remember visited tiles

            LinkedList<SearchNode> queue = new LinkedList<>();
            queue.add(new SearchNode(tiles[position.x][position.y], null, 0));

            boolean goalFound = false;
            while (!(queue.isEmpty() || goalFound)) {
                SearchNode node = queue.poll();
                if (node.tile.hasFood()) {
                    goal = node;
                    next_pos = findNextTilePos(goal);
                    goalFound = true;
                } else {
                    adjacents = getAdjacents(node.tile.position);
                    for (Position pos : adjacents) {
                        if (!discovered.containsKey(tiles[pos.x][pos.y]) && node.depth < max_depth) {
                            SearchNode newNode = new SearchNode(tiles[pos.x][pos.y], node, node.depth + 1);
                            discovered.put(tiles[pos.x][pos.y], newNode);
                            queue.add(newNode);
                        }
                    }
                }
            }
            // No food found nearby; choose a random move
            if (!goalFound) {
                adjacents = getAdjacents(this.position);
                int choice = r.nextInt(adjacents.length);
                // re-roll once if select previous tile. Puts bias against going backwards though still allows
                if (adjacents[choice].equals(last_pos))
                    choice = r.nextInt(adjacents.length);
                next_pos = adjacents[choice];
            }
        }
    }

    //  Helper function to get adjacent positions to a given position
    private Position[] getAdjacents(Position pos) {
        return new Position[] {new Position(pos.x + 1, pos.y), new Position(pos.x - 1, pos.y),
                new Position(pos.x, pos.y - 1), new Position(pos.x, pos.y + 1)};
    }

    // Helper function to take a SearchNode and finds the next Tile in the path
    private Position findNextTilePos(SearchNode node) {
        while (Math.abs(position.x - node.tile.position.x) + Math.abs(position.y - node.tile.position.y) != 1) {
            node = node.parent;
        }

        return node.tile.position;
    }

    // TODO implement. If no food in adjacent tiles, use depth-limited BFS to check for nearby food
    abstract Position scanForFood();

    // If an adjacent
    public void move() {

    }

    // If finding food uncontested or gaining some from a game
    public void gain_food(int v) {
        this.food += v;
    }

    // Lose food either from walking, spawning or losing a game when playing Hawk
    public void lose_food(int v) {
        this.food -= v;
    }

    /**
     * Abstract method that must be implemented to play a game. Agent received information about the opposing agent
     * @param opposingAgent Another agent trying to claim the food on the same turn
     * @return A Strategy move played by the Agent in this game
     */
    public abstract Strategy playGame(Agent opposingAgent);

    // TODO implement
    abstract Agent die();

    // TODO implement
    abstract Agent spawnChild(Position spawnLocation, String name);

}
