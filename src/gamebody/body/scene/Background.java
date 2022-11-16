package gamebody.body.scene;

import gamebody.engine.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * 背景类，用于储存游戏所需的背景。
 * <p>游戏总共设有5个背景，进行循环轮换。
 * <p>通过{@link#setLevelBackground}根据关卡数设置关卡的背景。
 * 
 * @author Enos
 * @see Scene
 */
public class Background extends GameObject {
    
    /**
     * 变长数组{@code Vector<Image>}存放每个关卡的背景。
     */
    private Vector<Image> levelBackgrounds = new Vector<>();

    /**
     * 每一关的陆地图片。
     */
    private Image land;

    /**
     * 存放每一关卡背景图片。
     */
    private Image levelBackground;

    /**
     * 每一关的地板图片。
     */
    private Image floor;

    /**
     * {@code Background}的唯一构造方法，调用{@code initialize}初始化函数。
     */
    public Background() {
        intialize();
    }

    /**
     * 初始化函数，从文件中获取图片，在创建对象时被调用。
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
     * 根据关卡数设置关卡的背景。
     * @param level 关卡数
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
