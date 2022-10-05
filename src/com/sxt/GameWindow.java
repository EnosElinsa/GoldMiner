package com.sxt;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private Dimension dimension;
    public static final int INIT_WIDTH = 960;
    public static final int INIT_HEIGHT = 540;
    private Image background;
    private Image icon;



    public GameWindow() {
        dimension = new Dimension(INIT_WIDTH, INIT_HEIGHT);
        background = Toolkit.getDefaultToolkit().getImage("resources/level-background-0.png");
        icon = Toolkit.getDefaultToolkit().getImage("resources/miner-dig-0.png");
    }

    public void paint(Graphics graphics) {
        graphics.drawImage(background, 0, 0, null);
    }

    public void launch() {
        this.setVisible(true);
        this.setSize(getDimension());
        this.setLocationRelativeTo(null);
        this.setTitle("GoldMiner");
        this.setIconImage(icon);
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
