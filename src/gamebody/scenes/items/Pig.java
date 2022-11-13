package gamebody.scenes.items;

import gamebody.engine.Animation;
import gamebody.engine.GameObject;
import gamebody.engine.ItemName;
import gamebody.engine.Rigidbody;
import gamebody.scenes.ObjectValueLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * 游戏背景下的{@code Pig}猪游戏物品。
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

    private static final String TEXTURE_DIRECTORY = "resources/pig-0.png";
    public static final int PIG_MASS = 3;
    public static final int PIG_VALUE = 2;
    protected static final boolean RIGHT = true;
    protected static final boolean LEFT = false;
    protected boolean currentDirection = RIGHT;
    protected static final int STEP = 6;
    protected static final int SLOW_STEP = 3;
    protected static final int SLOW_GAP = 20;
    private Animation animation = new Animation("resources/pig", 4);
    protected Image currentFrame;
    protected int startX;
    protected int endX;
    protected Thread pigThread = new Thread(this);

    public Pig() {}

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
        if (currentDirection == LEFT) {
            affineTransform.scale(-1, 1);
        }
        graphics2d.drawImage(currentFrame, affineTransform, jPanel);
    }

    @Override
    protected void update() {
        currentFrame = animation.getNextFrame();
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
        if (x >= endX) {
            delay(1200);
            currentDirection = LEFT;
            delay(1000);
        }
        else if (x <= startX) {
            delay(1200);
            currentDirection = RIGHT;
            delay(1000);
        }
        rigidbody = new Rigidbody(x, y, width, height);
    }

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