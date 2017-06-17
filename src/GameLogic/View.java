package GameLogic;

import Agents.DoveAgent;
import Agents.Hawk;
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

import java.util.Random;

/**
 * Created by Noosh on 03/03/17.
 */
public class View extends Application {

    // GameLogic.Game variables
    private static final int BOARD_SIZE = 20;
    private static final double WINDOW_WIDTH = 1000;
    private static final int TILE_SIZE = 30;
    private static final double WINDOW_HEIGHT = (BOARD_SIZE * TILE_SIZE) + 20;
    private final Group root = new Group();
    private final Group tiles = new Group();
    private final Group agents = new Group();
    Random r = new Random();

    Environment enviro = new Environment(BOARD_SIZE, 0.2);

    /**
     * Generate the game tiles
     */
    void makeEnviro() {

        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                Rectangle newRec = new Rectangle(x * 30 + 10, y * 30 + 10, 30, 30);
                if (enviro.locations[x][y].type == LocationType.TILE)
                    newRec.setFill(Color.WHITE);
                else newRec.setFill(Color.THISTLE);
                tiles.getChildren().add(newRec);
            }
        }
    }

    void makeAgents() {
        boolean blankFound = false;
        int x = 0;
        int y = 0;
        Location spawnLocation = enviro.locations[1][1];

        while (!blankFound) {
            x = r.nextInt(BOARD_SIZE - 1) + 1;
            y = r.nextInt(BOARD_SIZE - 1) + 1;
            spawnLocation = enviro.locations[x][y];
            if (enviro.locations[x][y].type == LocationType.TILE)
                blankFound = true;
        }

        enviro.makeAgent(new DoveAgent(spawnLocation));
        agents.getChildren().add(new Circle(x * TILE_SIZE + 10 + TILE_SIZE/2, y * TILE_SIZE + 10 +
                TILE_SIZE/2, 10, Color.LIGHTBLUE));
        blankFound = false;

        while (!blankFound) {
            x = r.nextInt(BOARD_SIZE - 1) + 1;
            y = r.nextInt(BOARD_SIZE - 1) + 1;
            if (enviro.locations[x][y].type == LocationType.TILE)
                blankFound = true;
        }
        spawnLocation = enviro.locations[x][y];
        enviro.makeAgent(new Hawk(spawnLocation));
        agents.getChildren().add(new Circle(x * TILE_SIZE + 10 + TILE_SIZE/2, y * TILE_SIZE + 10 +
                TILE_SIZE/2, 10, Color.PALEVIOLETRED));
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("HawkDove: A GameLogic.Game Theory Battleground");

        root.getChildren().add(tiles);
        root.getChildren().add(agents);

        makeEnviro();
        makeAgents();

        primaryStage.setScene(scene);

        // Timeline that makes all the action happen!
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), handler -> {




                }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        primaryStage.show();
    }
}