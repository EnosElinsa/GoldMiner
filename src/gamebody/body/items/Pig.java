package gamebody.body.items;

import gamebody.engine.Animation;
import gamebody.engine.GameObject;
import gamebody.engine.ItemName;
import gamebody.engine.ObjectValueLevel;
import gamebody.engine.Rigidbody;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * 游戏背景下的{@code Pig}猪游戏物品。
 * <p> 猪会在固定高度的部分区域来回走动。
 * <p> 质量为3。
 * <p> 价值为2。
 * @author Enos
 * @see Diamond
 * @see Dynamite
 * @see Bone
 * @see GemPig
 * @see Gold
 * @see Skull
 * @see Stone
 * @see TreasureBag
 */
public class Pig extends GameObject {

    /**
     * 贴图路径
     */
    private static final String TEXTURE_DIRECTORY = "resources/pig-0.png";

    /**
     * 猪的固定质量。
     */
    public static final int PIG_MASS = 3;

    /**
     * 猪的固定价值。
     */
    public static final int PIG_VALUE = 2;

    /**
     * 猪走动的方向为{@code RIGHT}右时，用{@code true}标识。
     */
    protected static final boolean RIGHT = true;

    /**
     * 猪走动的方向为{@code LEFT}左时，用{@code false}标识。
     */
    protected static final boolean LEFT = false;

    /**
     * 标识猪目前走动的方向。{@code RIGHT}或者{@code LEFT}。
     */
    protected boolean currentDirection = RIGHT;

    /**
     * 猪走动时的固定速度。
     */
    protected static final int STEP = 6;

    /**
     * 猪要改变方向时的固定速度。
     */
    protected static final int SLOW_STEP = 3;

    /**
     * 猪要改变方向时的距离长度。
     */
    protected static final int SLOW_GAP = 20;

    /**
     * 猪走动时的动画。
     */
    private Animation animation = new Animation("resources/pig", 4);

    /**
     * 猪走动时的当前动画帧。
     */
    protected Image currentFrame;

    /**
     * 猪的起始位置的横坐标。
     */
    protected int startX;

    /**
     * 猪的起始位置的纵坐标。
     */
    protected int endX;

    /**
     * 猪更新状态的线程。
     */
    protected Thread pigThread = new Thread(this);

    public Pig() {}

    /**
     * 生成猪的唯一构造方法。
     * @param startX 猪走动范围的的起始位置的横坐标
     * @param startY 猪走动范围的起始位置的纵坐标
     * @param endX 猪走动范围的终止位置的横坐标
     */
    public Pig(int startX, int startY, int endX) {
        super(startX, startY, TEXTURE_DIRECTORY);
        this.startX = startX;
        this.endX = endX;
        mass = PIG_MASS;
        value = PIG_VALUE;
        name = ItemName.PIG;
        objectValueLevel = ObjectValueLevel.SPECIAL;
        pigThread.start();
    }

    @Override
    public void render(Graphics graphics, JPanel jPanel) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        affineTransform = AffineTransform.getTranslateInstance(x, y);
        affineTransform.rotate(angle, width / 2, height / 2);
        // 走动的方向为左时，绘制镜像的贴图。
        if (currentDirection == LEFT) {
            affineTransform.scale(-1, 1);
        }
        graphics2d.drawImage(currentFrame, affineTransform, jPanel);
    }

    @Override
    protected void update() {
        currentFrame = animation.getNextFrame();
        // 如果猪与其他物体产生了碰撞，则不更新位置。
        if (isColliding) return;
        if (currentDirection == RIGHT) {
            if (x + SLOW_GAP >= endX) {
                x += SLOW_GAP;
            }
            else {
                x += STEP;
            }
        } else if (currentDirection == LEFT) {
            if (x - SLOW_GAP <= startX) {
                x -= SLOW_STEP;
            }
            else {
                x -= STEP;
            }
        }
        // 到达边界，向相反方向走。
        if (x >= endX) {
            delay(1200);
            currentDirection = LEFT;
            delay(1000);
        } else if (x <= startX) {
            delay(1200);
            currentDirection = RIGHT;
            delay(1000);
        }
        // 更新碰撞体
        rigidbody = new Rigidbody(x, y, width, height);
    }

    /**
     * 更新时进行延迟。
     * @param millis 要延迟的时间长度（毫秒）
     */
    protected void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(boolean currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Image getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(Image currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }
}