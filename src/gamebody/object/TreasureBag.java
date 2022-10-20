package gamebody.object;

import gamebody.engine.GameObject;

public class TreasureBag extends GameObject {

    public TreasureBag() {}

    public TreasureBag(int x, int y) {
        super(x, y);
    }

    public TreasureBag(int x, int y, String textureDirectory) {
        super(x, y, textureDirectory);
    }
    
}
