package com.sxt;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private Dimension dimension;
    public static final int INIT_HEIGHT = 500;
    public static final int INIT_WIDTH = 500;
    private background = Toolkit.getDefaultToolkit().getImage("resources/level-background-0.png");



    public GameWindow() {
        dimension = new Dimension(INIT_HEIGHT, INIT_WIDTH);
    }

    public void launch() {
        this.setVisible(true);
        this.setSize(getDimension());
        this.setLocationRelativeTo(null);
        this.setTitle("GoldMiner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.launch();
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(int height, int width) {
        dimension.height = height;
        dimension.width = width;
    }
}
