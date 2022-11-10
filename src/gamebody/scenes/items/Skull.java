package gamebody.scenes.items;

import gamebody.engine.GameObject;
import gamebody.engine.ItemName;

public class Skull extends GameObject {

    private static final String TEXTURE_DIRECTORY = "resources/skull.png";
    public static final int SKULL_MASS = 5;
    public static final int SKULL_VALUE = 20;

    public Skull(int x, int y) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = SKULL_MASS;
        value = SKULL_VALUE;
        name = ItemName.SKULL;
    }

    @Override
    protected void update() {}
}