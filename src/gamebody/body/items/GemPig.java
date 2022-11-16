package gamebody.body.items;

import gamebody.engine.Animation;
import gamebody.engine.ItemName;
import gamebody.engine.ObjectValueLevel;
import gamebody.engine.Rigidbody;

import javax.swing.*;

/**
 * 游戏背景下的{@code GemPig}钻石猪游戏物品。
 * <p> 钻石猪与普通猪一样会在固定高度的部分区域来回走动。
 * <p> 质量为5。
 * <p> 价值为602。
 * @author Enos
 * @see Diamond
 * @see Dynamite
 * @see Bone
 * @see Gold
 * @see Pig
 * @see Skull
 * @see Stone
 * @see TreasureBag
 */
public class GemPig extends Pig {

    
    /**
     * 贴图路径
     */
    private static final String TEXTURE_DIRECTORY = "resources/gem-pig-0.png";
    
    /**
     * 钻石猪走动时的动画。
     */
    private Animation animation = new Animation("resources/gem-pig", 4);

    /**
     * 生成钻石猪的唯一构造方法。
     * @param startX 钻石猪走动范围的的起始位置的横坐标
     * @param startY 钻石猪走动范围的起始位置的纵坐标
     * @param endX 钻石猪走动范围的终止位置的横坐标
     */
    public GemPig(int startX, int startY, int endX) {
        this.x = startX;
        this.y = startY;
        this.setEndX(endX);
        texture = new ImageIcon(TEXTURE_DIRECTORY).getImage();
        width = texture.getWidth(null);
        height = texture.getHeight(null);
        rigidbody = new Rigidbody(x, y, width, height);
        mass = PIG_MASS + Diamond.DIAMOND_MASS;
        value = PIG_VALUE + Diamond.DIAMOND_VALUE;
        name = ItemName.GEMPIG;
        objectValueLevel = ObjectValueLevel.SPECIAL;
        pigThread.start();
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
}
