package gamebody.object;

import gamebody.engine.Animation;
import gamebody.engine.GameObject;



public class Gold extends GameObject {
    
    private Animation goldSparkAnimation = new Animation("resources/gold-spark", 8);
    private Animation sparkAnimation = new Animation("resources/spark", 12);

    public static final double GOLD_MASS = 15;
    private static final String textureDirectory = "resources/gold.png";

    public static final int GOLD_VALUE=500;//大金块默认的金钱

    public Gold() {}

    //不带缩放比例的构造方法，表示大金块，value默认值为500
    public Gold(int x, int y) {
        super(x, y, textureDirectory);
        mass = GOLD_MASS;
        value=GOLD_VALUE;
    }

    //带缩放比例的构造方法
    public Gold(int x, int y, double scaleRatio) {
        super(x, y, textureDirectory);
        mass = GOLD_MASS * scaleRatio;
        this.scaleRatio = scaleRatio;
        //缩放比例为0.5，即中金块
        if (scaleRatio==0.5)
        {
            value=100;
        }
        //缩放比例为0.3，即小金块
        if (scaleRatio==0.3)
        {
            value=50;
        }
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
