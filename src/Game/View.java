package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class View extends JPanel {
    private Image body;
    private Image apple;
    private Image head;
    private BoardModel boardModel;
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    SnakeModel snake;
    FoodModel food;

    public View(BoardModel boardModel) {
        this.boardModel = boardModel;
        this.snake = boardModel.getSnake();
        this.food = boardModel.getFood();
        initBoard();
        setFocusable(true);
        requestFocusInWindow();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
    }

    private void loadImages() {
        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        body = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (boardModel.isInGame()) {
            SnakeModel snake = boardModel.getSnake();
            FoodModel food = boardModel.getFood();

            int[] sx = snake.getXPositions();
            int[] sy = snake.getYPositions();
            int fx = food.getFoodX();
            int fy = food.getFoodY();
            //Food
            g.drawImage(apple, fx, fy, this);

            //Snake
            for (int z = 0; z < snake.getDots(); z++) {
                if (z == 0) {
                    g.drawImage(head, sx[z], sy[z], this);
                } else {
                    g.drawImage(body, sx[z], sy[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
            repaint();
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over - Points: " + boardModel.getPoints();
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }
    private class TAdapter extends KeyAdapter {
        public TAdapter() {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT)) {
                snake.setDirection("LEFT");
            }

            if ((key == KeyEvent.VK_RIGHT)) {
                snake.setDirection("RIGHT");
            }

            if ((key == KeyEvent.VK_UP)) {
                snake.setDirection("UP");
            }

            if ((key == KeyEvent.VK_DOWN)) {
                snake.setDirection("DOWN");
            }
        }
    }
}
