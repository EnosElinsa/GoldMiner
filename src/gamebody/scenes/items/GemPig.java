package gamebody.scenes.items;

import gamebody.engine.Animation;
import gamebody.engine.ItemName;
import gamebody.engine.Rigidbody;
import gamebody.scenes.ObjectValueLevel;

import javax.swing.*;

/**
 * 游戏背景下的{@code GemPig}钻石猪游戏物品。
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

    private Animation animation = new Animation("resources/gem-pig", 4);
    private static final String TEXTURE_DIRECTORY = "resources/gem-pig-0.png";

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
