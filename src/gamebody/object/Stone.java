package gamebody.object;

import gamebody.engine.GameObject;

public class Stone extends GameObject {

    public static final int STONE_MASS = 10;
    private static final String textureDirectory = "resources/stone-0.png";

    public Stone() {}

    public Stone(int x, int y) {
        super(x, y, textureDirectory);
        mass = STONE_MASS;
    }

    public Stone(int x, int y, String textureDirectory) {
        super(x, y, textureDirectory);
        mass = STONE_MASS;
    }
    

    @Override
    protected void update() {}
}