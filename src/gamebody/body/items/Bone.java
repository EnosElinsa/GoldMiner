package gamebody.body.items;

import gamebody.engine.GameObject;
import gamebody.engine.ItemName;

/**
 * 游戏背景下的{@code Bone}骨头游戏物品。
 * <p> 质量为4。
 * <p> 价值为7。
 * @author Enos
 * @see Diamond
 * @see Dynamite
 * @see Explosive
 * @see GemPig
 * @see Gold
 * @see Pig
 * @see Skull
 * @see Stone
 * @see TreasureBag
 */
public class Bone extends GameObject {
    
    /**
     * 贴图路径。
     */
    private static final String TEXTURE_DIRECTORY = "resources/bone.png";

    /**
     * 默认固定的质量。
     */
    public static final int BONE_MASS = 4;

    /** 
     * 默认固定的价值。
     */
    public static final int BONE_VALUE = 7;

    public Bone() {}

    /**
     * 生成在具体位置的骨头游戏对象。
     * @param x 位置的横坐标
     * @param y 位置的纵坐标
     */
    public Bone(int x, int y) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = BONE_MASS;
        value = BONE_VALUE;
        name = ItemName.BONE;
    }

    @Override
    protected void update() {}
}
