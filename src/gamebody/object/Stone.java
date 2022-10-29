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

    public Stone(int x, int y, String textureDirectory,int type) {
        super(x, y, textureDirectory);
        mass = STONE_MASS;
        //0号石头value=11
        if (type==0)
        {
            value=11;
        }
        //1号石头value=20
        if(type==1)
        {
            value=20;
        }
    }
    

    @Override
    protected void update() {}
}