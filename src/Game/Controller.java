package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class Controller extends KeyAdapter implements ActionListener {
    private BoardModel model;
    private View view;

    public Controller() {
        model = new BoardModel();
        view = new View(model);

        JFrame frame = new JFrame("Snake Game");

        frame.setJMenuBar(createMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        startGame();
    }

    public void startGame() { model.initGame(); }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem settingsMenu = new JMenu("Settings");
        JMenuItem quitGameItem = new JMenuItem("Quit Game");

        JMenuItem easyItem = new JMenuItem("\u2713 Easy");
        JMenuItem mediumItem = new JMenuItem("Medium");
        JMenuItem hardItem = new JMenuItem("Hard");
        easyItem.setSelected(true);
        mediumItem.setSelected(false);
        hardItem.setSelected(false);

        newGameItem.addActionListener(e -> startGame());
        quitGameItem.addActionListener(e -> System.exit(0));

        easyItem.addActionListener(e -> setDifficulty(Difficulty.EASY, easyItem, mediumItem, hardItem));
        mediumItem.addActionListener(e -> setDifficulty(Difficulty.MEDIUM, easyItem, mediumItem, hardItem));
        hardItem.addActionListener(e -> setDifficulty(Difficulty.HARD, easyItem, mediumItem, hardItem));

        settingsMenu.add(easyItem);
        settingsMenu.add(mediumItem);
        settingsMenu.add(hardItem);

        fileMenu.add(newGameItem);
        fileMenu.add(settingsMenu);
        fileMenu.add(quitGameItem);

        menuBar.add(fileMenu);

        return menuBar;
    }

    private void setDifficulty(Difficulty difficulty, JMenuItem easy, JMenuItem medium, JMenuItem hard) {
        easy.setSelected(difficulty == Difficulty.EASY);
        medium.setSelected(difficulty == Difficulty.MEDIUM);
        hard.setSelected(difficulty == Difficulty.HARD);

        switch (difficulty) {
            case EASY:
                model.setDelay(140);
                break;
            case MEDIUM:
                model.setDelay(100);
                break;
            case HARD:
                model.setDelay(60);
                break;
        }
        updateDifficultyText(easy, medium, hard);
    }

    private void updateDifficultyText(JMenuItem easy, JMenuItem medium, JMenuItem hard) {
        easy.setText(getTextWithCheckMark(easy.isSelected(), "Easy"));
        medium.setText(getTextWithCheckMark(medium.isSelected(), "Medium"));
        hard.setText(getTextWithCheckMark(hard.isSelected(), "Hard"));
    }

    private String getTextWithCheckMark(boolean selected, String text) {
        return (selected ? "\u2713 " : "") + text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.isInGame()) {
            model.checkFood();
            model.checkCollision();
            model.getSnake().move();
            view.repaint();
        }
    }
}