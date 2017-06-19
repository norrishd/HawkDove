package GameLogic;

/**
 * Controller (listener) class to receive user input and update model
 */

public class Controller {

    GridWorld gridWorld;

    public void addModel(GridWorld gridWorld) {
        this.gridWorld = gridWorld;
    }

    public void nextTurn() {
        gridWorld.nextTurn();
    }

}
