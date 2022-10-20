package gamebody.object;

import gamebody.engine.GameObject;

public class Stone extends GameObject {

    public Stone() {}

    public Stone(int x, int y) {
        super(x, y);
    }

    public Stone(int x, int y, String textureDirectory) {
        super(x, y, textureDirectory);
    }
}

class StoneVariant extends Stone {

    public StoneVariant() {}

    public StoneVariant(int x, int y) {
        super(x, y);
    }

    public StoneVariant(int x, int y, String textureDirectory) {
        super(x, y, textureDirectory);
    }

}