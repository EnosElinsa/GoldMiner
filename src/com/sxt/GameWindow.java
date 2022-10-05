package com.sxt;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private Dimension dimension;
    public static final int INIT_WIDTH = 960;
    public static final int INIT_HEIGHT = 540 + 140 + 30;
    private Image icon;
    private Background background;

    public GameWindow() {
        dimension = new Dimension(INIT_WIDTH, INIT_HEIGHT);
        icon = Toolkit.getDefaultToolkit().getImage("resources/miner-dig-0.png");
        background = new Background();
    }

    @Override
    public void paint(Graphics graphics) {
        background.paintBackground(graphics);
    }

    public void launch() {
        this.setVisible(true);
        this.setSize(getDimension());
        this.setLocationRelativeTo(null);
        this.setTitle("GoldMiner");
        this.setIconImage(icon);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(int height, int width) {
        dimension.height = height;
        dimension.width = width;
    }
}
