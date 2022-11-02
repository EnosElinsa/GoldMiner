package gamebody.object;

import gamebody.engine.GameObject;

public class Stone extends GameObject {

    public static final int STONE_MASS = 8;
    private static final String[] TEXTURE_DIRECTORIES = {"resources/stone-0.png", "resources/stone-1.png"};

    public Stone() {}

    public Stone(int x, int y, int type) {
        super(x, y, TEXTURE_DIRECTORIES[type]);
        mass = STONE_MASS;
        // 0号石头value=11
        if (type == 0) {
            value = 11;
        }
        // 1号石头value=20
        if(type == 1) {
            value = 20;
            mass = STONE_MASS + 5;
        }
    }
    
    @Override
    protected void update() {}
}