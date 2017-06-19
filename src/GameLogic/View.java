package GameLogic;

import Agents.Agent;
import Agents.DoveAgent;
import Agents.HawkAgent;
import Tiles.FloorTile;
import Tiles.Tile;
import Tiles.TilePattern;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

/**
 * View class to handle display logic
 */
public class View extends Application {

    // GameLogic.WorldSettings variables
    private final Group root = new Group();
    private final Group tiles = new Group();
    private final Group agents = new Group();
    Random r = new Random();

    /**
     * Draw the game tiles
     */
    private void drawWorldTiles(Tile[][] worldTiles, int tileSize, int offset) {
        for (int x = 0; x < worldTiles.length; x++) {
            for (int y = 0; y < worldTiles[0].length; y++) {
                Rectangle newRec = new Rectangle(x * tileSize + offset, y * tileSize + offset, tileSize, tileSize);
                if (worldTiles[x][y].walkable())
                    newRec.setFill(Color.DARKGREEN);
                else newRec.setFill(Color.DARKGRAY);
                tiles.getChildren().add(newRec);
            }
        }
    }

    private void drawAgents(ArrayList<Agent> currentAgents, int tileSize, int offset) {

        for (Agent agent : currentAgents) {
            if (agent instanceof DoveAgent)
                agents.getChildren().add(new Circle(agent.position.x * tileSize + offset + tileSize / 2,
                        agent.position.y * tileSize + offset +
                                tileSize / 2, tileSize / 4, Color.LIGHTBLUE));
            else if (agent instanceof HawkAgent)
                agents.getChildren().add(new Circle(agent.position.x * tileSize + offset + tileSize / 2,
                        agent.position.y * tileSize + offset +
                                tileSize / 2, tileSize / 4, Color.PALEVIOLETRED));
        }
    }

    private void makecontrols(GridWorld gridWorld) {
        // Control panel background rectangle
        Rectangle newRec = new Rectangle(gridWorld.WINDOW_WIDTH - gridWorld.PANEL_WIDTH - gridWorld.OFFSET,
                gridWorld.OFFSET, gridWorld.PANEL_WIDTH, gridWorld.WINDOW_HEIGHT - 2 * gridWorld.OFFSET);
        newRec.setFill(Color.BISQUE);
        root.getChildren().add(newRec);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create a GridWorld (Model), generate tiles
        GridWorld gridWorld = new GridWorld();
        gridWorld.generateWorld(TilePattern.RANDOM_SPARSE);
        gridWorld.addAgent(new DoveAgent(gridWorld.getWalkableTile(), "Adam"));
        gridWorld.addAgent(new HawkAgent(gridWorld.getWalkableTile(), "Eve"));

        //Controller controller = new Controller();
        //controller.addModel(gridWorld);

        Scene scene = new Scene(root, gridWorld.WINDOW_WIDTH, gridWorld.WINDOW_HEIGHT);
        primaryStage.setTitle("HawkDove: A Game Theory Battleground");

        drawWorldTiles(gridWorld.tiles, gridWorld.TILE_SIZE, gridWorld.OFFSET);
        drawAgents(gridWorld.agents, gridWorld.TILE_SIZE, gridWorld.OFFSET);
        makecontrols(gridWorld);

        root.getChildren().add(tiles);
        root.getChildren().add(agents);

        primaryStage.setScene(scene);

        /*// Timeline that makes all the action happen!
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), handler -> {
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();*/

        primaryStage.show();
    }
}