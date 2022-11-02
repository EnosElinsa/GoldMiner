package gamebody.object;

import gamebody.engine.GameObject;

public class Skull extends GameObject {

    private static final String TEXTURE_DIRECTORY = "resources/skull.png";
    public static final int SKULL_MASS = 5;
    public static final int SKULL_VALUE = 20;

    public Skull(int x, int y) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = SKULL_MASS;
        value = SKULL_VALUE;
    }

    @Override
    protected void update() {}
}
