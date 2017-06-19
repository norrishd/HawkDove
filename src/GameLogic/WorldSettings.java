package GameLogic;

/**
 * Stores all parameter values for a game, including default values for everything
 */
public class WorldSettings {

    // Default Game logic variables
    public static int STARTING_FOOD = 3;
    public int STEPS_TO_LOSE_FOOD = 10;
    public int FOOD_VALUE = 3;               // how much value each food item is worth
    public double FOOD_GROWTH_RATE = 0.5;    // rough frequency that food grows
    public int SPAWN_THRESHOLD = 10;         // how much food an agent must accrue before spawning a child
    public int SPAWN_COST = 2;               // the food cost to spawn a child
    public int CHILD_START_FOOD = 3;         // the amount of food a parent gives to its child to start with
    public int GAME_LOSS_COST = 5;           // food lost when losing to another Hawk
    public KinLoyalty kinLoyalty = KinLoyalty.ETERNAL;

    // Game play mode variables
    public int WORLD_X_TILES = 15;           // number of tiles vertically
    public int WORLD_Y_TILES = 15;           // number of tiles horizontally
    public boolean autoplayMode = false;
    public boolean playMode = false;

    // GUI variables
    public int TILE_SIZE = 30;
    public int OFFSET = 10;                         // makes tiles and agents position properly somehow
    public int PANEL_WIDTH = 300;                   // The width of the control panel buttons will go on
    public int PANEL_MIN_HEIGHT = 400;

    public double WINDOW_WIDTH = (WORLD_X_TILES * TILE_SIZE) + 3 * OFFSET + PANEL_WIDTH;
    public double WINDOW_HEIGHT = Math.max((WORLD_Y_TILES * TILE_SIZE) + 2 * OFFSET, PANEL_MIN_HEIGHT);

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
