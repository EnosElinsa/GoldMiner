package gamebody.scenes.items;

import gamebody.engine.GameObject;
import gamebody.engine.ItemName;
import gamebody.scenes.ObjectValueLevel;

/**
 * 游戏背景下的{@code Stone}石头游戏物品。
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

    private static final String[] TEXTURE_DIRECTORIES = {"resources/stone-0.png", "resources/stone-1.png"};
    public static final int STONE_MASS = 9;
    
    public Stone() {}

    public Stone(int x, int y, int type) {
        super(x, y, TEXTURE_DIRECTORIES[type]);
        mass = STONE_MASS;
        name = ItemName.STONE;
        objectValueLevel = ObjectValueLevel.LOW;
        // 0号石头value=11
        if (type == 0) {
            value = 11;
        }
        // 1号石头value=20
        if(type == 1) {
            value = 20;
            mass = STONE_MASS + 5;
        }
    }
    
    @Override
    protected void update() {}

}