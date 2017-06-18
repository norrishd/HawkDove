package Agents;

import GameLogic.Position;
import GameLogic.SearchNode;
import Tiles.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Abstract class for agents which navigate navigate the environment
 */
public abstract class Agent {

    // Strategy, starting position etc
    public String name;
    public Position position;
    private int food;
    private int steps_taken;            // way to keep track how long an agent has survived
    private int children_spawned;

    private ArrayList<Position> adjacentFood;
    private Position last_pos = null;   // previous position agent was at. Prefers spawning here, and not re-visiting
    private SearchNode goal;              // identified food, currently aiming to collect
    private HashMap<Agent, ArrayList<Boolean>> pastEncounters;      // remember outcomes of past encounters with agents
    Random r = new Random();

    public Agent(Position spawnLocation, int startingFood, String name) {
        this.position = spawnLocation;
        this.food = startingFood;
        this.steps_taken = 0;
        this.children_spawned = 0;
        this.name = name;
        this.pastEncounters = new HashMap<>();
        this.adjacentFood = new ArrayList<>();
    }

    // Identifies any adjacent tiles with food
    public void checkForAdjacentFood(Tile[][] tiles) {
        ArrayList<Position> updatedAdjFood = new ArrayList<>();
        Position[] adjacents = {new Position(position.x + 1, position.y),
                new Position(position.x - 1, position.y),
                new Position(position.x, position.y - 1),
                new Position(position.x, position.y + 1)};

        for (Position pos : adjacents) {
            if (tiles[pos.x][pos.y].hasFood())
                updatedAdjFood.add(pos);
        }
        adjacentFood = updatedAdjFood;

        // If any adjacent square has food, move to one randomly
        if (adjacentFood.size() > 0) {
            int choice = r.nextInt(adjacentFood.size());
            move(adjacentFood.get(choice));
            // if no adjacent food, check if there's still food at the goal
        } else if (goal != null && goal.tile.hasFood()) {
            // Find next Tile to walk to
            SearchNode node = goal;
            while (Math.abs(position.x - node.tile.position.x) + Math.abs(position.y - node.tile.position.y) != 1) {
                node = node.parent;
            }
            move(node.tile.position);
            // depth-limited DFS to find nearby food
        } else {
            SearchNode node = new SearchNode(tiles[position.x][position.y], null, 0);
            HashMap<Tile, SearchNode> visited = new HashMap<>();



        }
    }

    // TODO implement. If no food in adjacent tiles, use depth-limited BFS to check for nearby food
    abstract Position scanForFood();

    // If an adjacent
    public void move(Position adjacentTile) {

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
     * @param opposingAgent
     * @return
     */
    public abstract Strategy playGame(Agent opposingAgent);

    // TODO implement
    abstract Agent die();

    // TODO implement
    abstract Agent spawnChild(Position spawnLocation, String name);

}
