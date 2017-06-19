package GameLogic;

/**
 * Stores all parameter values for a game, including default values for everything
 */
public class WorldSettings {

    // Default Game logic variables
    public static int STARTING_FOOD = 3;
    public int STEPS_TO_LOSE_FOOD = 10;
    public int FOOD_VALUE = 3;               // how much value each food item is worth
    public double FOOD_GROWTH_RATE = 1.0;    // food will spawn once per turn
    public int SPAWN_THRESHOLD = 10;         // how much food an agent must accrue before spawning a child
    public int SPAWN_COST = 2;               // the food cost to spawn a child
    public int CHILD_START_FOOD = 3;         // the amount of food a parent gives to its child to start with
    public int GAME_LOSS_COST = 5;           // food lost when losing to another Hawk
    public KinLoyalty kinLoyalty = KinLoyalty.ETERNAL;

    // Game play mode variables
    public static int WORLD_TILE_HEIGHT = 10;                 // number of tiles vertically
    public static int WORLD_TILE_WIDTH = 10;                 // number of tiles horizontally
    public boolean autoplayMode = false;
    public boolean playMode = false;

    // GUI variables
    public static int BOARD_SIZE = 20;
    public static int TILE_SIZE = 30;
    public static double WINDOW_WIDTH = 1000;
    public static double WINDOW_HEIGHT = (BOARD_SIZE * TILE_SIZE) + 20;

    // Game performance variables
    public static int DFSlimit = 3;

    public WorldSettings(int startingFood, int stepsToLoseFood, int foodValue, double foodGrowthRate, int spawnThreshold,
                         int spawnCost, int childStartFood, int gameLossCost) {
        this.STARTING_FOOD = startingFood;
        this.STEPS_TO_LOSE_FOOD = stepsToLoseFood;
        this.FOOD_VALUE = foodValue;
        this.FOOD_GROWTH_RATE = foodGrowthRate;
        this.SPAWN_THRESHOLD = spawnThreshold;
        this.SPAWN_COST = spawnCost;
        this.CHILD_START_FOOD = childStartFood;
        this.GAME_LOSS_COST = gameLossCost;
    }

    // Alternative constructor using all default parameters
    public WorldSettings() { }


}
