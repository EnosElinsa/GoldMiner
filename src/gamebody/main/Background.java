package gamebody.main;

import gamebody.engine.GameObject;

import javax.swing.*;
import java.awt.*;

/**
 * <p> 背景类 </p>
 * 绘制游戏场景
 */
public class Background extends GameObject {
    
    private Image land;
    private Image levelBackground0;
    private Image floor;

    public Background() {
        land = new ImageIcon("resources/land.png").getImage();
        levelBackground0 = new ImageIcon("resources/level-background-0.png").getImage();
        floor = new ImageIcon("resources/floor_resize.png").getImage();
    }

    @Override
    public void render(Graphics graphics) {
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

    @Override
    protected void update() {}
}
