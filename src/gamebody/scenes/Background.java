package gamebody.scenes;

import gamebody.engine.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * <p> 背景类 </p>
 * <p> 绘制游戏场景 </p>
 * @author Enos
 * @see Scene
 *
 */
public class Background extends GameObject {
    /**
     *变长数组vector存放每个关卡的背景
     */
    private Vector<Image> levelBackgrounds = new Vector<>();
    /**
     * 存放地板图片的变量
     */
    private Image land;
    /*
     * 存放每一关卡背景的变量
     */
    private Image levelBackground;
    /*
     * 地板图片变量
     */
    private Image floor;

    /**
     * Background的唯一构造方法，调用initialize初始化函数
     */
    public Background() {
        intialize();
    }

    /**
     * 初始化函数，将每个Image变量赋值上对应的图片，在创建对象时被调用
     */
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

    /**
     * levelBackground获取到当前关卡的背景图片
     * @param level
     */
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
