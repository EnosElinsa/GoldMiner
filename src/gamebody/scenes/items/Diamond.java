package gamebody.scenes.items;

import gamebody.engine.GameObject;
import gamebody.engine.ItemName;
import gamebody.scenes.ObjectValueLevel;

public class Diamond extends GameObject {

    private static final String TEXTURE_DIRECTORY = "resources/diamond.png";
    public static final int DIAMOND_MASS = 2;
    public static final int DIAMOND_VALUE = 600;

    public Diamond() {}

    public Diamond(int x, int y) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = DIAMOND_MASS;
        value = DIAMOND_VALUE;
        objectValueLevel = ObjectValueLevel.HIGH;
        name = ItemName.DIAMOND;
    }

    @Override
    protected void update() {
        
    }
}
