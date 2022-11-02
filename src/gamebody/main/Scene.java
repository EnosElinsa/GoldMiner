package gamebody.main;

import java.util.Vector;
import gamebody.engine.*;
import gamebody.object.*;

public class Scene {

    private Vector<Vector<GameObject>> scenes = new Vector<Vector<GameObject>>();
    private Background background = new Background();
    
    public Scene() {
        intialize();
    }

    public void intialize() {
        for (int index = 0; index < GameWindow.LEVEL_NUMBER; index++) {
            Vector<GameObject> newScene = new Vector<>();
            scenes.add(newScene);
            newScene.add(background);
        }

        Vector<GameObject> scene0 = scenes.get(0);
        Vector<GameObject> scene1 = scenes.get(1);
        Vector<GameObject> scene2 = scenes.get(2);
        Vector<GameObject> scene3 = scenes.get(3);
        Vector<GameObject> scene4 = scenes.get(4);
        Vector<GameObject> scene5 = scenes.get(5);
        Vector<GameObject> scene6 = scenes.get(6);
        Vector<GameObject> scene7 = scenes.get(7);
        Vector<GameObject> scene8 = scenes.get(8);
        Vector<GameObject> scene9 = scenes.get(9);

        int x = GameWindow.INIT_WIDTH;
        int y = GameWindow.INIT_HEIGHT;

        scene0.add(new Gold(x / 8, y / 2 + 40));
        scene0.add(new Gold(x - 30, y / 3 * 2));
        scene0.add(new Gold(x / 8 + 130, y / 2 + 190, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 2, y / 2 + 120, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 2 + 130, y / 2 + 20, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 4 - 10, y / 3, Gold.TINY_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 4 + 100, y / 2, Gold.TINY_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 2 + 250, y / 2 - 20, Gold.TINY_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 2 + 270, y / 2 + 20, Gold.TINY_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 2 + 300, y / 2 - 80, Gold.TINY_GOLD_SCALE_RATIO));
        scene0.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene0.add(new Stone(x / 8, y / 2 - 60, 0));
        scene0.add(new Stone(x / 8 + 210, y / 2 + 250, 1));
        scene0.add(new Stone(x / 2 + 230, y / 2 + 100, 1));
        scene0.add(new Stone(x / 2 + 330, y / 2 - 130, 0));

        scene1.add(new Gold(x, y));
        scene1.add(new Gold(x, y));
        scene1.add(new Gold(x, y, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x, y, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Diamond(x, y));
        scene1.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene1.add(new Stone(x, y, 0));
        scene1.add(new Stone(x, y, 0));
        scene1.add(new Stone(x, y, 0));
        scene1.add(new Stone(x, y, 1));
        scene1.add(new Stone(x, y, 1));
        scene1.add(new Stone(x, y, 1));
        scene1.add(new Stone(x, y, 1));

        scene2.add(new Gold(x, y));
        scene2.add(new Gold(x, y, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene2.add(new Gold(x, y, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene2.add(new Gold(x, y, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene2.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene2.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene2.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene2.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene2.add(new Diamond(x, y));
        scene2.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene2.add(new Stone(x, y, 0));
        scene2.add(new Stone(x, y, 0));
        scene2.add(new Stone(x, y, 0));
        scene2.add(new Stone(x, y, 1));
        scene2.add(new Stone(x, y, 1));
        scene2.add(new Stone(x, y, 1));
        scene2.add(new Stone(x, y, 1));

        scene3.add(new Gold(x, y));
        scene3.add(new Gold(x, y));
        scene3.add(new Gold(x, y, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene3.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene3.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene3.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene3.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene3.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene3.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene3.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene3.add(new Stone(x, y, 0));
        scene3.add(new Stone(x, y, 1));
        scene3.add(new Stone(x, y, 1));
        scene3.add(new Stone(x, y, 1));

        scene4.add(new Gold(x, y));
        scene4.add(new Gold(x, y));
        scene4.add(new Gold(x, y));
        scene4.add(new Gold(x, y));
        scene4.add(new Gold(x, y, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene4.add(new Gold(x, y, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene4.add(new Gold(x, y, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene4.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene4.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene4.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene4.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene4.add(new Diamond(x, y));
        scene4.add(new Diamond(x, y));
        scene4.add(new Diamond(x, y));
        scene4.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene4.add(new Stone(x, y, 0));
        scene4.add(new Stone(x, y, 1));
        scene4.add(new Stone(x, y, 1));
        scene4.add(new Stone(x, y, 1));

        scene5.add(new Gold(x, y));
        scene5.add(new Gold(x, y, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x, y, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x, y, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene5.add(new Stone(x, y, 0));
        scene5.add(new Stone(x, y, 0));
        scene5.add(new Stone(x, y, 0));
        scene5.add(new Stone(x, y, 1));
        scene5.add(new Stone(x, y, 1));
        scene5.add(new Stone(x, y, 1));

        scene6.add(new Gold(x, y));
        scene6.add(new Gold(x, y));
        scene6.add(new Gold(x, y));
        scene6.add(new Gold(x, y));
        scene6.add(new Gold(x, y));
        scene6.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene6.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene6.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene6.add(new Skull(x, y));
        scene6.add(new Skull(x, y));
        scene6.add(new Bone(x, y));
        scene6.add(new Bone(x, y));
        scene6.add(new Explosive(x, y));
        scene6.add(new Explosive(x, y));

        scene7.add(new Diamond(x, y));
        scene7.add(new Diamond(x, y));
        scene7.add(new Diamond(x, y));
        scene7.add(new Diamond(x, y));
        scene7.add(new Diamond(x, y));
        scene7.add(new Diamond(x, y));
        scene7.add(new Diamond(x, y));
        scene7.add(new Diamond(x, y));
        scene7.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene7.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene7.add(new Explosive(x, y));
        scene7.add(new Explosive(x, y));
        scene7.add(new Explosive(x, y));
        scene7.add(new Explosive(x, y));
        scene7.add(new Explosive(x, y));

        scene8.add(new Gold(x, y));
        scene8.add(new Gold(x, y));
        scene8.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene8.add(new Diamond(x, y));
        scene8.add(new Diamond(x, y));
        scene8.add(new Diamond(x, y));
        scene8.add(new Diamond(x, y));
        scene8.add(new Diamond(x, y));
        scene8.add(new Diamond(x, y));
        scene8.add(new Diamond(x, y));
        scene8.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene8.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene8.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene8.add(new Skull(x, y));
        scene8.add(new Bone(x, y));
        scene8.add(new Explosive(x, y));
        scene8.add(new Explosive(x, y));
        scene8.add(new Explosive(x, y));
        scene8.add(new Explosive(x, y));
        scene8.add(new Explosive(x, y));
        scene8.add(new Explosive(x, y));
        scene8.add(new Explosive(x, y));

        scene9.add(new Gold(x, y));
        scene9.add(new Gold(x, y));
        scene9.add(new Gold(x, y, Gold.TINY_GOLD_SCALE_RATIO));
        scene9.add(new Diamond(x, y));
        scene9.add(new Diamond(x, y));
        scene9.add(new Diamond(x, y));
        scene9.add(new Diamond(x, y));
        scene9.add(new Diamond(x, y));
        scene9.add(new Diamond(x, y));
        scene9.add(new Diamond(x, y));
        scene9.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene9.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene9.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene9.add(new Skull(x, y));
        scene9.add(new Bone(x, y));
        scene9.add(new Explosive(x, y));
        scene9.add(new Explosive(x, y));
        scene9.add(new Explosive(x, y));
        scene9.add(new Explosive(x, y));
        scene9.add(new Explosive(x, y));
        scene9.add(new Explosive(x, y));
        scene9.add(new Explosive(x, y));
    }

    public Vector<GameObject> getGameObjects(int level) {
        background.setLevelBackground(level);
        return scenes.get(level);
    }
}
