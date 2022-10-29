package gamebody.object;

import gamebody.engine.GameObject;

public class Diamond extends GameObject {

    private static final String textureDirectory = "resources/diamond.png";

    public Diamond() {}

    public Diamond(int x, int y) {
        super(x, y, textureDirectory);
        mass = 3;
    }

    @Override
    protected void update() {
        
    }
}
