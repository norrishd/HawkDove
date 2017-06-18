package GameLogic;

/**
 * Stores all details and logic for the game
 */
public class Model {

    // Game logic variables
    public static int STARTING_FOOD = 3;
    public static int STEPS_TO_LOSE_FOOD = 10;
    public static int FOOD_VALUE = 3;               // how much value each food item is worth
    public static double FOOD_GROWTH_RATE = 1.0;     // food will spawn once per turn
    public static int SPAWN_THRESHOLD = 10;         // how much food an agent must accrue before spawning a child
    public static int SPAWN_COST = 2;               // the food cost to spawn a child
    public static int CHILD_START_FOOD = 3;         // the amount of food a parent gives to its child to start with
    public static int GAME_LOSS_COST = 5;           // food lost when losing to another Hawk

    // Game play mode variables
    public static boolean autoplayMode = false;
    public static KinLoyalty kinLoyalty = KinLoyalty.ETERNAL;

    // GUI variables
    public static double WINDOW_WIDTH;
    public static double WINDOW_HEIGHT;

    public Model(int startingFood, int stepsToLoseFood, int foodValue, double foodGrowthRate, int spawnThreshold,
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


}
