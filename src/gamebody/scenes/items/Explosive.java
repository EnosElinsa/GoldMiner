package gamebody.scenes.items;

import gamebody.engine.Animation;
import gamebody.engine.Audio;
import gamebody.engine.GameObject;
import gamebody.engine.ItemName;
import gamebody.scenes.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * 游戏背景下的{@code Explosive}TNT炸药游戏物品
 * <p>当TNT炸弹被钩子触碰到或者在其他炸弹的爆炸波及到时，会发生爆炸。
 * <p>爆炸会将其周围的物体摧毁（爆炸半径为120px)。
 * <p>爆炸后残骸的质量为2。
 * <p>爆炸后残骸的价值为2。
 * @author Enos
 * @author JiajiaPig
 * @see Diamond
 * @see Dynamite
 * @see Bone
 * @see GemPig
 * @see Gold
 * @see Pig
 * @see Skull
 * @see Stone
 * @see TreasureBag
 */
public class Explosive extends GameObject {

    /**
     * 爆炸效果的贴图1。
     */
    private static final String TEXTURE_DIRECTORY = "resources/explosive.png";

    /**
     * 爆炸效果的贴图2。
     */
    private static final String TEXTURE_DIRECTORY_2 = "resources/explosive-2.png";

    /**
     * 爆炸的半径。
     */
    private static final int RADIUS_OF_EXPLOSION = 120;

    /**
     * 爆炸后残骸的质量
     */
    private static final int EXPLOSIVE_VALUE = 2;

    /**
     * 爆炸后残骸的价值
     */
    private static final int EXPLOSIVE_MASS = 2;

    /**
     * 用于标识TNT炸弹是否被触发，被触发则调用{@code explode}方法爆炸。
     */
    private boolean isTriggered;

    /**
     * TNT炸弹被生成时的初始位置横坐标。
     */
    private int initialX;

    /**
     * TNT炸弹被生成时的初始位置纵坐标。
     */
    private int initialY;
    
    /**
     * TNT炸弹爆炸后的残骸贴图
     */
    private Image brokenTexture = new ImageIcon(TEXTURE_DIRECTORY_2).getImage();

    /**
     * TNT炸弹爆炸时的音效。
     */
    private Audio explosionSound = new Audio("sound/sound_wav/explosive.wav");

    /**
     * TNT炸弹爆炸时的动画。
     */
    private Animation explosionAnimation = new Animation("resources/tnt-explosion", 10);

    /**
     * {@code GameWindow}游戏窗口主体的引用，用于获取游戏场景下的其他游戏物体。
     */
    private GameWindow gameWindow;

    /**
     * TNT炸弹的更新线程。
     */
    private Thread explosiveThread = new Thread(this);

    /**
     * 一个存放在TNT炸弹爆炸范围内的物体的集合。
     */
    private Vector<GameObject> objectsWithinRange = new Vector<>();

    /**
     * TNT炸弹爆炸时的更新线程。
     */
    private Thread explosionThread = new Thread(() -> {
        if (isTriggered) {
            texture = brokenTexture;
            explosionSound.play(1);
            for (GameObject gameObject : objectsWithinRange) {
                gameObject.setColliding(true);
                gameObject.setTexture(new ImageIcon("resources/tnt-explosion-0").getImage());
                try {
                    Thread.sleep(180);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (gameObject.getName() != ItemName.EXPLOSIVE) {
                    gameObject.vanish(); 
                }
            }
        }
        if (isOnHook == false) {
            vanish();
        }
    });

    public Explosive() {}

    /**
     * 生成TNT炸弹的唯一构造方法。
     * @param x 位置横坐标
     * @param y 位置纵坐标
     * @param gameWindow 游戏窗口主体的引用
     */
    public Explosive(int x, int y, GameWindow gameWindow) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = EXPLOSIVE_MASS;
        value = EXPLOSIVE_VALUE;
        name = ItemName.EXPLOSIVE;
        initialX = x;
        initialY = y;
        this.gameWindow = gameWindow;
        explosiveThread.start();
    }

    /**
     * 进行爆炸的方法。
     * <p> 当TNT炸弹被钩子
     */
    private void explode() {
        isTriggered = true;
        getObjectsWithinRange();
        explosionThread.start();
    }
    
    /**
     * 通过遍历在游戏场景下的所有物体，获取在TNT炸弹爆炸范围内的物体并存放到{@code objectsWithinRange}集合里。
     */
    private void getObjectsWithinRange() {
        for (GameObject gameObject : gameWindow.getGameobjects()) {
            if (getDistance(gameObject, this) <= RADIUS_OF_EXPLOSION + gameObject.getHeight() / 2 
                && gameObject != this) {
                objectsWithinRange.add(gameObject);
            }
        }
    }

    /**
     * 得到两个游戏物体中心之间的距离
     * @param o1 游戏物体1
     * @param o2 游戏物体2
     * @return 两个游戏物体中心之间的距离
     */
    private double getDistance(GameObject o1, GameObject o2) {
        return Math.sqrt(Math.pow(o1.getX() - o2.getX(), 2) + Math.pow(o1.getY() - o2.getY(), 2));
    }

    @Override
    public void render(Graphics graphics, JPanel jPanel) {
        super.render(graphics, jPanel);
        if (isTriggered && isVanished == false) {
            if (isOnHook && explosionThread.isAlive() == false) return;
            Image explosionImage = explosionAnimation.getNextFrame();
            graphics.drawImage(explosionImage, initialX - explosionImage.getWidth(jPanel) / 2, 
                initialY - explosionImage.getHeight(jPanel) / 2, jPanel);
        }
    }

    @Override
    protected void update() {
        if (isColliding && isTriggered == false) {
            explode();
        }
    }
}
