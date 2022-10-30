package gamebody.main;

import gamebody.engine.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * <p> 背景类 </p>
 * 绘制游戏场景
 */
public class Background extends GameObject {
    
    private static Vector<Image> levelbackgrounds = new Vector<>();
    static {
        levelbackgrounds.add(new ImageIcon("resources/level-background-0.png").getImage());
        levelbackgrounds.add(new ImageIcon("resources/level-background-1.png").getImage());
        levelbackgrounds.add(new ImageIcon("resources/level-background-2.png").getImage());
        levelbackgrounds.add(new ImageIcon("resources/level-background-3.png").getImage());
        levelbackgrounds.add(new ImageIcon("resources/level-background-4.png").getImage());
    }
    private Image land;
    private Image levelBackground;
    private Image floor;

    public Background(int level) {
        land = new ImageIcon("resources/land.png").getImage();
        levelBackground = levelbackgrounds.get(level);
        floor = new ImageIcon("resources/floor_resize.png").getImage();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(land, 0, 30, null);
        graphics.drawImage(levelBackground, 0, land.getHeight(null) + 30, null);
        graphics.drawImage(floor, land.getWidth(null) / 2 - 40, land.getHeight(null) + 8, null);
    }

    @Override
    protected void update() {}
}
