package gamebody.scenes.items;

import gamebody.engine.GameObject;
import gamebody.engine.ItemName;
import gamebody.engine.ObjectValueLevel;

/**
 * 游戏背景下的{@code Stone}石头游戏物品。
 * <p> 第一种类型的石头质量为9，第二种为14。
 * <p> 第一种类型的石头价值为7，第二种为20.
 * @author Enos
 * @see Diamond
 * @see Dynamite
 * @see Bone
 * @see GemPig
 * @see Gold
 * @see Pig
 * @see Skull
 * @see TreasureBag
 */
public class Stone extends GameObject {

    /**
     * 两种类型的石头的贴图路径。
     */
    private static final String[] TEXTURE_DIRECTORIES = {"resources/stone-0.png", "resources/stone-1.png"};

    /**
     * 第一种类型石头的固定质量。
     */
    public static final int STONE_MASS = 9;
    
    public Stone() {}

    /**
     * 生成石头的唯一构造方法。
     * @param x 位置的横坐标
     * @param y 位置的纵坐标
     * @param type 石头的类型
     */
    public Stone(int x, int y, int type) {
        super(x, y, TEXTURE_DIRECTORIES[type]);
        mass = STONE_MASS;
        name = ItemName.STONE;
        objectValueLevel = ObjectValueLevel.LOW;
        // 0号石头value = 11
        if (type == 0) {
            value = 11;
        }
        // 1号石头value = 20
        if(type == 1) {
            value = 20;
            mass = STONE_MASS + 5;
        }
    }
    
    @Override
    protected void update() {}
}