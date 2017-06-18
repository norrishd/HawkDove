package GameLogic;

import Agents.Agent;
import Agents.DoveAgent;
import Agents.HawkAgent;
import Tiles.FloorTile;
import Tiles.Tile;
import Tiles.TilePattern;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    void drawWorldTiles(Tile[][] worldTiles) {
        for (int x = 0; x < worldTiles.length; x++) {
            for (int y = 0; y < worldTiles[0].length; y++) {
                Rectangle newRec = new Rectangle(x * 30 + 10, y * 30 + 10, 30, 30);
                if (worldTiles[x][y] instanceof FloorTile)
                    newRec.setFill(Color.DARKGREEN);
                else newRec.setFill(Color.DARKGRAY);
                tiles.getChildren().add(newRec);
            }
        }
    }

    void drawAgents(ArrayList<Agent> currentAgents, int tileSize) {

        for (Agent agent : currentAgents) {
            if (agent instanceof DoveAgent)
                agents.getChildren().add(new Circle(agent.position.x_pos * tileSize + 10 + tileSize / 2,
                        agent.position.y_pos * tileSize + 10 +
                                tileSize / 2, 10, Color.LIGHTBLUE));
            else if (agent instanceof HawkAgent)
                agents.getChildren().add(new Circle(agent.position.x_pos * tileSize + 10 + tileSize / 2,
                        agent.position.y_pos * tileSize + 10 +
                                tileSize / 2, 10, Color.PALEVIOLETRED));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create a GridWorld (Model), generate tiles
        GridWorld gridWorld = new GridWorld();
        gridWorld.generateWorld(TilePattern.RANDOM_SPARSE);
        gridWorld.addAgent(new DoveAgent(gridWorld.getWalkableTile(), "Adam"));

        Controller controller = new Controller();
        controller.addModel(gridWorld);


        Scene scene = new Scene(root, gridWorld.WINDOW_WIDTH, gridWorld.WINDOW_HEIGHT);
        primaryStage.setTitle("HawkDove: A Game Theory Battleground");

        root.getChildren().add(tiles);
        root.getChildren().add(agents);

        drawWorldTiles(gridWorld.tiles);
        drawAgents(gridWorld.agents, gridWorld.TILE_SIZE);

        primaryStage.setScene(scene);

        // Timeline that makes all the action happen!
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), handler -> {




                }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        primaryStage.show();
    }
}