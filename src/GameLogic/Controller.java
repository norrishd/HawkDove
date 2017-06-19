package GameLogic;

import Agents.Agent;

import java.util.ArrayList;

/**
 * Controller (listener) class to receive user input and update model
 */

public class Controller {

    GridWorld gridWorld;
    View view;

    public void addModel(GridWorld gridWorld) {
        this.gridWorld = gridWorld;
    }
    public void addView(View view) {
        this.view = view;
    }

    public void nextTurn() {

        gridWorld.nextTurn();
        view.drawAgents(gridWorld.agents, gridWorld.TILE_SIZE, gridWorld.OFFSET);
    }
}
