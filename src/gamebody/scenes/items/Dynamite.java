package gamebody.scenes.items;

import gamebody.engine.Audio;
import gamebody.engine.GameObject;
import gamebody.engine.ItemName;
import gamebody.engine.Rigidbody;
import gamebody.scenes.GameWindow;
import gamebody.scenes.characters.Rope;
import gamebody.scenes.characters.RopeState;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Vector;

/**
 * <p>游戏背景下的{@code Dynamite}炸药游戏物品。
 * <p>炸弹可以被投掷，并且炸掉在钩子上（此时必须是钩子上面有物体）以及钩子周围的在爆炸范围内的物体。
 * <p>{@code Dynamite}炸药游戏物品可以通过商店购买获取。
 * @author Enos
 * @author JiajiaPig
 * @see Diamond
 * @see Bone
 * @see Explosive
 * @see GemPig
 * @see Gold
 * @see Pig
 * @see Skull
 * @see Stone
 * @see TreasureBag
 */
public class Dynamite extends GameObject {

    /**
     * 贴图路径。
     */
    private static final String TEXTURE_DIRECTORY = "resources/dynamite.png";

    /**
     * 炸弹生成的固定位置的横坐标，固定为{@code Rope}绳索的起始位置横坐标。
     */
    private static final int SPAWN_X = Rope.START_X;

    /**
     * 炸弹生成的固定位置的纵坐标，固定为{@code Rope}绳索的起始位置纵坐标。
     */
    private static final int SPAWN_Y = Rope.START_Y;

    /**
     * 炸弹被投掷时的速度。
     */
    private static final int STEP = 50;

    /**
     * 炸弹爆炸时的爆炸半径。
     */
    private static final int RADIUS_OF_EXPLOSION = 50;

    /**
     * 用于标识炸弹是否被触发，被触发则调用{@code explode}方法爆炸。
     */
    private boolean isTriggered;

    /**
     * 炸弹爆炸时的音效。
     */
    private Audio explosionSound = new Audio("sound/sound_wav/explosive.wav");

    /**
     * 炸弹爆炸时的爆炸效果贴图。
     */
    private Image explosionImage = new ImageIcon("resources/dynamite-explosion-0.png").getImage();

    /**
     * {@code GameWindow}游戏窗口主体的引用，用于获取游戏场景下的其他游戏物体。
     */
    private GameWindow gameWindow;

    /**
     * 一个存放在炸弹爆炸范围内的物体的集合。
     */
    private Vector<GameObject> objectsWithinRange = new Vector<>();

    
    /**
     * 炸弹更新状态的线程
     */
    private Thread dynamiteThread = new Thread(this);

    /**
     * 生成炸弹的唯一构造方法。
     * @param gameWindow 游戏窗口主体的引用
     */
    public Dynamite(GameWindow gameWindow)  {
        super(SPAWN_X, SPAWN_Y, TEXTURE_DIRECTORY);
        this.gameWindow = gameWindow;
        angle = Math.PI * 2 - gameWindow.getRope().getAngle();
        dynamiteThread.start();
    }

    /**
     * 如果有检测到碰撞，则返回与其碰撞的物体的引用，否则返回{@code null}。
     * @return
     */
    private GameObject detectCollidingObject() {
        for (GameObject gameObject : gameWindow.getGameobjects()) {
            if (rigidbody.hasCollisionWith(gameObject.getRigidbody()) && gameObject.isOnHook()) {
                return gameObject;
            }
        }
        return null;
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

    /**
     * 
     */
    private void getObjectsWithinRange() {
        for (GameObject gameObject : gameWindow.getGameobjects()) {
            if (getDistance(gameObject, this) <= RADIUS_OF_EXPLOSION + gameObject.getHeight() / 2 
            && gameObject != this) {
                objectsWithinRange.add(gameObject);
            }
        }
        objectsWithinRange.add(collidingObject);
    }

    /**
     * 
     */
    private Thread explodeThread = new Thread(() -> {
        texture = explosionImage;
        new Thread(() -> { explosionSound.play(1); }).start();
        for (GameObject gameObject : objectsWithinRange) {
            gameObject.setColliding(true);
            gameObject.setTexture(explosionImage);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (gameObject.getName() != ItemName.EXPLOSIVE) {
                gameObject.vanish();
            }
        }
        vanish();
    });
    
    /**
     * 
     */
    private void explode() {
        isTriggered = true;
        getObjectsWithinRange();
        explodeThread.start();
        isTerminated = true;
    }

    @Override
    public void render(Graphics graphics, JPanel panel) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        if (isVanished == false) {
            affineTransform = AffineTransform.getTranslateInstance(x - width / 2, y - height / 2);
            affineTransform.rotate(angle - Math.toRadians(40), width / 2, height / 2);
            graphics2d.drawImage(texture, affineTransform, panel);
        }
    }


    @Override
    protected void update() throws InterruptedException {
        x += Math.sin(gameWindow.getRope().getAngle()) * STEP;
        y += Math.cos(Math.abs(gameWindow.getRope().getAngle())) * STEP;
        rigidbody = new Rigidbody(x, y, width, height);
        
        collidingObject = detectCollidingObject();
        if (collidingObject != null && isTriggered == false) {
            gameWindow.getRope().setCurrentState(RopeState.RETRIEVE);
            gameWindow.getRope().setRetrieveRate(Rope.INIT_RETRIEVE_RATE);
            gameWindow.getRope().setColliding(false);
            explode();
        }
    }

    public Thread getDynamiteThread() {
        return dynamiteThread;
    }

    public Thread getExplodeThread() {
        return explodeThread;
    }
}
