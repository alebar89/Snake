package Game;

import javax.swing.*;

public class SnakeModel extends JPanel implements GameObject {
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private final int ALL_DOTS = 900;
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    private int dots;
    public void initSnake() {
        setInitialPositions();
    }
    public void resetDirection() {
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
    }
    private void setInitialPositions() {
        int startX = 50;
        int startY = 50;

        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = startX - z * 20;
            y[z] = startY;
        }
    }

    public void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[z - 1];
            y[z] = y[z - 1];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }
        if (rightDirection) {
            x[0] += DOT_SIZE;
        }
        if (upDirection) {
            y[0] -= DOT_SIZE;
        }
        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    public void setDirection(String direction) {
        switch (direction) {
            case "LEFT":
                if (!rightDirection) {
                    leftDirection = true;
                    upDirection = false;
                    downDirection = false;
                }
                break;
            case "RIGHT":
                if (!leftDirection) {
                    rightDirection = true;
                    upDirection = false;
                    downDirection = false;
                }
                break;
            case "UP":
                if (!downDirection) {
                    upDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }
                break;
            case "DOWN":
                if (!upDirection) {
                    downDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }
                break;
        }
    }

    public void incrementDots() {
        dots++;
    }

    public int getDots() {
        return dots;
    }

    public int[] getXPositions() {
        return x;
    }

    public int[] getYPositions() {
        return y;
    }

    public int[] getHeadPosition() {
        int[] snakeHeadPosition = new int[2];
        snakeHeadPosition[0] = x[0];
        snakeHeadPosition[1] = y[0];
        return snakeHeadPosition;
    }

    @Override
    public void update() {
        initSnake();
        move();
    }
}
