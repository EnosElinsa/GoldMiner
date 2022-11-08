package gamebody.scenes.items;

import javax.swing.ImageIcon;

import gamebody.engine.Animation;
import gamebody.engine.Rigidbody;

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
