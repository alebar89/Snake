package Game;

public class FoodModel implements GameObject {
    private int food_x;
    private int food_y;
    private final int RAND_POS = 29;
    public void locateFood() {
        int r = (int) (Math.random() * RAND_POS);
        food_x = r * DOT_SIZE;
        r = (int) (Math.random() * RAND_POS);
        food_y = r * DOT_SIZE;
    }

    public int getFoodX() {
        return food_x;
    }

    public int getFoodY() {
        return food_y;
    }

    @Override
    public void update() {
        locateFood();
    }
}
