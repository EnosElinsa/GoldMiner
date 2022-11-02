package gamebody.object;

import gamebody.engine.GameObject;

public class Explosive extends GameObject {

    private static final String TEXTURE_DIRECTORY = "resources/explosive.png";

    public Explosive() {}

    public Explosive(int x, int y) {
        super(x, y, TEXTURE_DIRECTORY);
    }

    @Override
    protected void update() {}
}
