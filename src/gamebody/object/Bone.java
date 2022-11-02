package gamebody.object;

import gamebody.engine.GameObject;

public class Bone extends GameObject {
    private static final String TEXTURE_DIRECTORY = "resources/bone.png";
    public static final int BONE_MASS = 4;
    public static final int BONE_VALUE = 7;

    public Bone(int x, int y) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = BONE_MASS;
        value = BONE_VALUE;
    }

    @Override
    protected void update() {
    }
}
