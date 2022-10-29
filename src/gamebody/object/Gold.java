package gamebody.object;

import gamebody.engine.*;



public class Gold extends GameObject {
    
    private Animation goldSparkAnimation = new Animation("resources/gold-spark", 8);
    private Animation sparkAnimation = new Animation("resources/spark", 12);

    public static final double GOLD_MASS = 15;
    private static final String textureDirectory = "resources/gold.png";

    public Gold() {}

    public Gold(int x, int y) {
        super(x, y, textureDirectory);
        mass = GOLD_MASS;
    }
    
    public Gold(int x, int y, double scaleRatio) {
        super(x, y, textureDirectory);
        mass = GOLD_MASS * scaleRatio;
        this.scaleRatio = scaleRatio;
    }

    public Animation getGoldSparkAnimation() {
        return goldSparkAnimation;
    }

    public Animation getSparkAnimation() {
        return sparkAnimation;
    }

    @Override
    protected void update() {}  
}
