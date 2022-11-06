package gamebody.object;

import gamebody.engine.*;

public class Gold extends GameObject {
    
    private Animation goldSparkAnimation = new Animation("resources/gold-spark", 8);
    private Animation sparkAnimation = new Animation("resources/spark", 12);

    public static final double GOLD_MASS = 15;
    public static final int GOLD_VALUE = 500; //大金块默认的金钱
    public static final double MIDDLE_GOLD_SCALE_RATIO = 0.5;
    public static final double TINY_GOLD_SCALE_RATIO = 0.25;
    private static final String TEXTURE_DIRECTORY = "resources/gold.png";

    public Gold() {}

    // 不带缩放比例的构造方法，表示大金块，value默认值为500
    public Gold(int x, int y) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = GOLD_MASS;
        value = GOLD_VALUE;
        objectValueLevel = ObjectValueLevel.HIGH;
    }

    // 带缩放比例的构造方法
    public Gold(int x, int y, double scaleRatio) {
        super(x, y, TEXTURE_DIRECTORY);
        this.scaleRatio = scaleRatio;
        mass = GOLD_MASS * scaleRatio;
        width = (int)(scaleRatio * width);
        height = (int)(scaleRatio * height);
        rigidbody = new Rigidbody(x, y, width, height);
        objectValueLevel = ObjectValueLevel.NORMAL;
        // 缩放比例为0.5，即中金块
        if (scaleRatio == MIDDLE_GOLD_SCALE_RATIO) {
            value = 100;
        }
        // 缩放比例为0.25，即小金块
        if (scaleRatio == TINY_GOLD_SCALE_RATIO) {
            value = 50;
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
