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
    
    private Vector<Image> levelBackgrounds = new Vector<>();
    private Image land;
    private Image levelBackground;
    private Image floor;

    public Background() {
        intialize();
    }

    private void intialize() {
        land = new ImageIcon("resources/land.png").getImage();
        floor = new ImageIcon("resources/floor_resize.png").getImage();
        levelBackgrounds.add(new ImageIcon("resources/level-background-0.png").getImage());
        levelBackgrounds.add(new ImageIcon("resources/level-background-1.png").getImage());
        levelBackgrounds.add(new ImageIcon("resources/level-background-2.png").getImage());
        levelBackgrounds.add(new ImageIcon("resources/level-background-3.png").getImage());
        levelBackgrounds.add(new ImageIcon("resources/level-background-4.png").getImage());
        levelBackground = levelBackgrounds.get(0);
    }

    public void setLevelBackground(int level) {
        levelBackground = levelBackgrounds.get(level % levelBackgrounds.size());
    }

    @Override
    public void render(Graphics graphics, JPanel panel) {
        graphics.drawImage(land, 0, 30, panel);
        graphics.drawImage(levelBackground, 0, land.getHeight(null) + 30, panel);
        graphics.drawImage(floor, land.getWidth(null) / 2 - 40, land.getHeight(null) + 8, panel);
    }

    @Override
    protected void update() {}
}
