package gamebody.object;

import gamebody.engine.GameObject;

public class TreasureBag extends GameObject {

    private static final String TEXTURE_DIRECTORY = "resources/treasure-bag.png";



    public TreasureBag() {}

    public TreasureBag(int x, int y) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = (int)(Math.random() * 15 + 1);
        value = (int)(Math.random() * 800 + 1);
        objectValueLevel=ObjectValueLevel.HIGH;
    }

    @Override
    protected void update() {}


}
