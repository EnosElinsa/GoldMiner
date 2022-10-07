package com.sxt;

import java.awt.*;

public class Background {
    private Image land;
    private Image levelBackground0;
    private Image floor;

    public Background() {
        land = Toolkit.getDefaultToolkit().getImage("resources/land.png");
        levelBackground0 = Toolkit.getDefaultToolkit().getImage("resources/level-background-0.png");
        floor = Toolkit.getDefaultToolkit().getImage("resources/floor_resize.png");
    }

    public void paintBackground(Graphics graphics) {
        graphics.drawImage(land, 0, 30, null);
        graphics.drawImage(levelBackground0, 0, land.getHeight(null) + 30, null);
        graphics.drawImage(floor, land.getWidth(null) / 2 - 40, land.getHeight(null) + 8, null);
    }

    public Image getLand() {
        return land;
    }

    public Image getLevelBackground0() {
        return levelBackground0;
    }

    public Image getFloor() {
        return floor;
    }
}
