package gamebody.scenes.items;

import gamebody.engine.GameObject;
import gamebody.engine.ItemName;

/**
 * 游戏背景下的{@code Skull}头骨游戏物品。
 * @author Enos
 * @see Diamond
 * @see Dynamite
 * @see Bone
 * @see GemPig
 * @see Gold
 * @see Pig
 * @see Stone
 * @see TreasureBag
 */
public class Skull extends GameObject {

    /**
     * 贴图路径
     */
    private static final String TEXTURE_DIRECTORY = "resources/skull.png";
    
    /**
     * 默认固定的质量
     */
    public static final int SKULL_MASS = 5;

    /** 
     * 默认固定的价值
     */
    public static final int SKULL_VALUE = 20;

    /**
     * 生成在具体位置的头骨游戏对象
     * @param x 位置的横坐标
     * @param y 位置的纵坐标
     */
    public Skull(int x, int y) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = SKULL_MASS;
        value = SKULL_VALUE;
        name = ItemName.SKULL;
    }

    @Override
    protected void update() {}
}
