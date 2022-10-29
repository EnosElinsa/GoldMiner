package gamebody.object;

import gamebody.engine.GameObject;

public class Explosive extends GameObject {

    private static final String textureDirectory = "resources/explosive.png";

    public Explosive() {}

    public Explosive(int x, int y) {
        super(x, y, textureDirectory);
    }

    @Override
    protected void update() {
        
    }
}
