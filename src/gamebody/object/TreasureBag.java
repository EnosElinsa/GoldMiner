package gamebody.object;

import gamebody.engine.GameObject;

public class TreasureBag extends GameObject {

    private static final String textureDirectory = "resources/treasure-bag.png";
    public TreasureBag() {}

    public TreasureBag(int x, int y) {
        super(x, y, textureDirectory);
        mass = (int)(Math.random() * 15 + 1);
    }

    @Override
    protected void update() {}
}
