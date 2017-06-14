/**
 * Class for the environment location tiles, which may be either barriers, or visitable by agents
 */
class Location {

    int x;
    int y;
    LocationType type;
    private boolean foodPresent = false;

    Location(int x, int y, LocationType type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    void getFood() {
        foodPresent = true;
    }

    void loseFood() {
        foodPresent = false;
    }

}
