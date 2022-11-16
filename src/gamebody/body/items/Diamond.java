package gamebody.body.items;

import gamebody.engine.GameObject;
import gamebody.engine.ItemName;
import gamebody.engine.ObjectValueLevel;

/**
 * 游戏背景下的{@code Diamond}钻石游戏物品。
 * <p>质量为2
 * <p>价值为600
 * @author Enos
 * @see Bone
 * @see Dynamite
 * @see Explosive
 * @see GemPig
 * @see Gold
 * @see Pig
 * @see Skull
 * @see Stone
 * @see TreasureBag
 */
public class Diamond extends GameObject {

    /**
     * 贴图路径。
     */
    private static final String TEXTURE_DIRECTORY = "resources/diamond.png";

    /**
     * 默认固定的质量。
     */
    public static final int DIAMOND_MASS = 2;

    /** 
     * 默认固定的价值。
     */
    public static final int DIAMOND_VALUE = 600;

    public Diamond() {}

    /**
     * 生成在具体位置的钻石游戏对象。
     * @param x 位置的横坐标
     * @param y 位置的纵坐标
     */
    public Diamond(int x, int y) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = DIAMOND_MASS;
        value = DIAMOND_VALUE;
        objectValueLevel = ObjectValueLevel.HIGH;
        name = ItemName.DIAMOND;
    }

    @Override
    protected void update() {}
}
