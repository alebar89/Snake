package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardModel extends JPanel implements ActionListener {
    private GameObjectFactory factory = new GameObjectFactory();
    private SnakeModel snake = (SnakeModel) factory.createGameObject(Objects.SNAKE);
    private FoodModel food = (FoodModel) factory.createGameObject(Objects.FOOD);
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private int points = 0;
    private boolean inGame = true;
    private int delay = 140;
    private Timer timer;

    public BoardModel() {
        initGame();
        setFocusable(true);
        requestFocusInWindow();
    }

    public void initGame() {
        points = 0;
        inGame = true;

        this.getSnake().initSnake();
        this.getSnake().resetDirection();
        this.getFood().update();

        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(delay, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkFood();
            updateBoard();
            checkCollision();
        }
        repaint();
    }

    public void checkCollision() {
        int[] x = snake.getXPositions();
        int[] y = snake.getYPositions();

        for (int z = snake.getDots(); z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT || y[0] < 0 || x[0] >= B_WIDTH || x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    public void checkFood() {
        int[] snakeHeadPos = snake.getHeadPosition();
        int x = food.getFoodX();
        int y = food.getFoodY();
        if ((x == snakeHeadPos[0]) && (y == snakeHeadPos[1])) {
            snake.incrementDots();
            points++;
            food.update();
        }
    }

    public void updateBoard() {
        snake.move();
        repaint();
    }

    public boolean isInGame() {
        return inGame;
    }

    public int getPoints() {
        return points;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public SnakeModel getSnake() {
        return snake;
    }

    public FoodModel getFood() {
        return food;
    }
}
