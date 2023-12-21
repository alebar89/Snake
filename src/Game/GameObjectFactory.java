package Game;

public class GameObjectFactory {
    public GameObject createGameObject(Objects type) {
        if (type == null) {
            return null;
        }

        switch (type) {
            case SNAKE:
                return new SnakeModel();
            case FOOD:
                return new FoodModel();
            default:
                return null;
        }
    }
}
