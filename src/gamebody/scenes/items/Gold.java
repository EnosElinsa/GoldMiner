package gamebody.scenes.items;

import gamebody.engine.GameObject;
import gamebody.engine.ItemName;
import gamebody.engine.ObjectValueLevel;
import gamebody.engine.Rigidbody;

/**
 * 游戏背景下的{@code Gold}金矿游戏物品。
 * <p> 小金块质量为8，中金块质量为13，大金块的质量为17。
 * <p> 小金块价值为50，中金块价值为100，大金块的价值为500。
 * @author Enos
 * @see Diamond
 * @see Dynamite
 * @see Bone
 * @see GemPig
 * @see Pig
 * @see Skull
 * @see Stone
 * @see TreasureBag
 */
public class Gold extends GameObject {
    
    /**
     * 大金块的固定质量。
     */
    public static final double GOLD_MASS = 17;

    /**
     * 大金块的固定价值。
     */
    public static final int GOLD_VALUE = 500;

    /**
     * 中金块相对于大金块的缩放比例。
     */
    public static final double MIDDLE_GOLD_SCALE_RATIO = 0.5;

    /**
     * 小金块相对于大金块的缩放比例。
     */
    public static final double TINY_GOLD_SCALE_RATIO = 0.25;

    /**
     * 贴图路径。
     */
    private static final String TEXTURE_DIRECTORY = "resources/gold.png";

    public Gold() {}

    /**
     * 大金块的构造方法。
     */
    public Gold(int x, int y) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = GOLD_MASS;
        value = GOLD_VALUE;
        name = ItemName.GOLD;
        objectValueLevel = ObjectValueLevel.HIGH;
    }

    /**
     * 小金块和中金块的构造方法。
     * @param x 位置的横坐标
     * @param y 位置的纵坐标
     * @param scaleRatio 缩放的比例
     */
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

    @Override
    protected void update() {}      
}
