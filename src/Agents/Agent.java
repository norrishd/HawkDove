package Agents;

import GameLogic.Position;
import GameLogic.WorldSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Abstract class for agents which navigate navigate the environment
 */
public abstract class Agent {

    // Strategy, starting position etc
    public String name;
    Position position;
    private int food;
    private int steps_taken;            // way to keep track how long an agent has survived
    private int children_spawned;

    private Position last_pos = null;   // previous position agent was at. Prefers spawning here, and not re-visiting
    private Position goal;              // identified food, currently aiming to collect
    private HashMap<Agent, ArrayList<Boolean>> pastEncounters;      // remember outcomes of past encounters with agents

    public Agent(Position spawnLocation, String name) {
        this.position = spawnLocation;
        this.food = WorldSettings.STARTING_FOOD;
        this.steps_taken = 0;
        this.children_spawned = 0;
        this.name = name;
        this.pastEncounters = new HashMap<>();
    }

    // TODO implement. agent should choose randomly if multiple adjacent tiles have food
    abstract Set<Position> checkForAdjacentFood();

    // TODO implement. If no food in adjacent tiles, use depth-limited BFS to check for nearby food
    abstract Position scanForFood();

    // TODO implement
    abstract void move(Position adjacentTile);

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
