package gamebody.object;

import gamebody.engine.Animation;
import gamebody.engine.GameObject;

public class Gold extends GameObject {
    
    private Animation goldSparkAnimation = new Animation("resources/gold-spark", 8);
    private Animation sparkAnimation = new Animation("resources/spark", 12);
    public static final int MASS = 10;

    public Gold() {}

    public Gold(int x, int y) {
        super(x, y);
    }

    public Gold(int x, int y, String textureDirectory) {
        super(x, y, textureDirectory);
        mass = MASS;
    }

    public Animation getGoldSparkAnimation() {
        return goldSparkAnimation;
    }

    public Animation getSparkAnimation() {
        return sparkAnimation;
    }  
}
