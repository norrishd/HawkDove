package GameLogic;

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

        gridWorld.moveAgents();
        gridWorld.killDepletedAgents();
        gridWorld.growFood();
        view.drawAgents(gridWorld.agents, gridWorld.TILE_SIZE, gridWorld.OFFSET);
        view.drawFood(gridWorld.tiles, gridWorld.TILE_SIZE, gridWorld.OFFSET);
        view.highlightGoalFood(gridWorld.agents, gridWorld.TILE_SIZE, gridWorld.OFFSET);
    }
}
