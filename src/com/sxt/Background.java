package com.sxt;

import java.awt.*;

public class Background {
    private Image land;
    private Image levelBackground0;

    public Background() {
        land = Toolkit.getDefaultToolkit().getImage("resources/land.png");
        levelBackground0 = Toolkit.getDefaultToolkit().getImage("resources/level-background-0.png");
    }

    public void paintBackground(Graphics graphics) {
        graphics.drawImage(land, 0, 30, null);
        graphics.drawImage(levelBackground0, 0, 140 + 30, null);
    }
}
