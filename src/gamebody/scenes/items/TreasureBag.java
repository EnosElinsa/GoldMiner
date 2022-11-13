package gamebody.scenes.items;

import gamebody.engine.GameObject;
import gamebody.engine.ItemName;
import gamebody.scenes.ObjectValueLevel;

/**
 * 游戏背景下的{@code TreasureBag}幸运袋游戏物品。
 * <p> 质量为随机(1 ~ 15)。
 * <p> 价值为随机(1 ~ 800)。
 * @author Enos
 * @see Diamond
 * @see Dynamite
 * @see Bone
 * @see GemPig
 * @see Gold
 * @see Pig
 * @see Stone
 */
public class TreasureBag extends GameObject {

    /**
     * 贴图路径
     */
    private static final String TEXTURE_DIRECTORY = "resources/treasure-bag.png";

    public TreasureBag() {}

    /**
     * 生成在具体位置的幸运袋游戏对象
     * @param x 位置的横坐标
     * @param y 位置的纵坐标
     */
    public TreasureBag(int x, int y) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = (int)(Math.random() * 15 + 1);
        value = (int)(Math.random() * 800 + 1);
        name = ItemName.TREASUREBAG;
        objectValueLevel = ObjectValueLevel.HIGH;
    }

    @Override
    protected void update() {}
}
