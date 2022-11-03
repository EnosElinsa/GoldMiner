package gamebody.main;

import gamebody.engine.GameObject;
import gamebody.object.*;

import java.util.Vector;

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

        scene1.add(new Gold(x / 8 - 40, y / 2 + 200));
        scene1.add(new Gold(x / 2 + 130, y / 2 + 250));
        scene1.add(new Gold(x / 8 - 30, y / 2 - 30, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x / 2 + 350, y / 2 + 230, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x / 8 + 90, y / 2 + 150, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x / 8 + 200, y / 2 + 80, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x / 8 + 310, y / 2 - 20, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x / 8 + 410, y / 2 + 60, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x / 2 + 130, y / 2 + 170, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x / 2 + 290, y / 2 - 100, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Gold(x / 2 + 340, y / 2 + 60, Gold.TINY_GOLD_SCALE_RATIO));
        scene1.add(new Diamond(x / 2 + 190, y / 2 + 110));
        scene1.add(new TreasureBag(x / 8 + 210, y / 2 + 240));
        scene1.add(new Stone(x / 8 + 20, y / 2 + 20, 1));
        scene1.add(new Stone(x / 8 + 150, y / 2 + 100, 1));
        scene1.add(new Stone(x / 8 + 290, y / 2 + 190, 0));
        scene1.add(new Stone(x / 8 + 410, y / 2 + 190, 1));
        scene1.add(new Stone(x / 2 + 205, y / 2 + 30, 0));
        scene1.add(new Stone(x / 2 + 240, y / 2 + 260, 1));
        scene1.add(new Stone(x / 2 + 325, y / 2 - 50, 0));

        scene2.add(new Gold(x / 8 - 70, y / 2 + 200));
        scene2.add(new Gold(x / 8 + 30, y / 2 + 70, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene2.add(new Gold(x / 2 + 250, y / 2 + 80, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene2.add(new Gold(x / 2 + 400, y / 2 + 20, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene2.add(new Gold(x / 4 - 10, y / 3 + 20, Gold.TINY_GOLD_SCALE_RATIO));
        scene2.add(new Gold(x / 4 - 15, y / 3 + 80, Gold.TINY_GOLD_SCALE_RATIO));
        scene2.add(new Gold(x / 4 + 45, y / 3 + 30, Gold.TINY_GOLD_SCALE_RATIO));
        scene2.add(new Gold(x / 2 + 170, y / 2 + 20, Gold.TINY_GOLD_SCALE_RATIO));
        scene2.add(new Diamond(x / 2 + 285, y / 2 + 180));
        scene2.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene2.add(new Stone(x / 8 + 310, y / 2, 0));
        scene2.add(new Stone(x / 8 + 390, y / 2 + 60, 0));
        scene2.add(new Stone(x / 2 + 230, y / 2 + 180, 0));
        scene2.add(new Stone(x / 8 + 90, y / 2 + 160, 1));
        scene2.add(new Stone(x / 2 + 170, y / 2 + 120, 1));
        scene2.add(new Stone(x / 2 + 340, y / 2 + 60, 1));
        scene2.add(new Stone(x / 2 + 340, y / 2 + 130, 1));

        scene3.add(new Gold(x / 8 + 190, y / 2 + 210));
        scene3.add(new Gold(x / 8 + 480, y / 2 + 230));
        scene3.add(new Gold(x / 8 + 480, y / 2 + 50, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene3.add(new Gold(x / 4 - 30, y / 3 + 20, Gold.TINY_GOLD_SCALE_RATIO));
        scene3.add(new Gold(x / 2 + 170, y / 2 - 30, Gold.TINY_GOLD_SCALE_RATIO));
        scene3.add(new Gold(x / 2 + 200, y / 2, Gold.TINY_GOLD_SCALE_RATIO));
        scene3.add(new Gold(x / 2 + 225, y / 2 + 55, Gold.TINY_GOLD_SCALE_RATIO));
        scene3.add(new TreasureBag(x / 8 + 150, y / 2 + 80));
        scene3.add(new TreasureBag(x / 8 + 350, y / 2 + 220));
        scene3.add(new TreasureBag(x / 2 + 210, y / 2 + 130));
        scene3.add(new TreasureBag(x / 2 + 300, y / 2 + 40));
        scene3.add(new Stone(x / 8 + 500, y / 2 + 130, 0));
        scene3.add(new Stone(x / 8 + 90, y / 2 + 260, 1));
        scene3.add(new Stone(x / 4 + 20, y / 3 + 80, 1));
        scene3.add(new Stone(x / 2 + 355, y / 2 + 5, 1));

        scene4.add(new Gold(x / 8 - 70, y / 2 + 200));
        scene4.add(new Gold(x / 8 + 220, y / 2 + 260));
        scene4.add(new Gold(x / 8 + 400, y / 2 + 250));
        scene4.add(new Gold(x - 50, y / 2 + 230));
        scene4.add(new Gold(x / 8 + 30, y / 2, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene4.add(new Gold(x / 8 + 270, y / 2 + 5, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene4.add(new Gold(x - 120, y / 2, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene4.add(new Gold(x / 8 + 75, y / 2 - 130, Gold.TINY_GOLD_SCALE_RATIO));
        scene4.add(new Gold(x / 8 + 120, y / 2 + 150, Gold.TINY_GOLD_SCALE_RATIO));
        scene4.add(new Gold(x / 8 + 150, y / 2 - 110, Gold.TINY_GOLD_SCALE_RATIO));
        scene4.add(new Gold(x / 8 + 450, y / 2 + 100, Gold.TINY_GOLD_SCALE_RATIO));
        scene4.add(new Diamond(x / 8 + 30, y / 2 + 200));
        scene4.add(new Diamond(x / 2 + 220, y / 2 + 275));
        scene4.add(new Diamond(x / 2 + 300, y / 2 + 200));
        scene4.add(new TreasureBag(x / 2 + 130, y / 2 + 250));
        scene4.add(new Stone(x / 8 + 375, y / 2 + 125, 0));
        scene4.add(new Stone(x / 8 + 105, y / 2 - 100, 1));
        scene4.add(new Stone(x / 8 + 200, y / 2 + 100, 1));
        scene4.add(new Stone(x - 175, y / 2 + 50, 1));

        scene5.add(new Gold(x - 50, y / 2 + 280));
        scene5.add(new Gold(x / 8 + 50, y / 2 + 260, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x / 2 + 180, y / 2 + 295, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x - 220, y / 2 + 100, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x / 8 + 30, y / 2 + 50, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x / 8 + 160, y / 2 + 180, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x / 8 + 200, y / 2 + 260, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x - 170, y / 2 + 130, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x - 150, y / 2 + 100, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x - 125, y / 2 + 120, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new Gold(x - 65, y / 2 + 125, Gold.TINY_GOLD_SCALE_RATIO));
        scene5.add(new TreasureBag(x - 70, y / 2));
        scene5.add(new Stone(x / 8 + 60, y / 2 + 110, 0));
        scene5.add(new Stone(x - 280, y / 2 - 20, 0));
        scene5.add(new Stone(x - 150, y / 2 - 80, 0));
        scene5.add(new Stone(x / 8 + 35, y / 2 - 90, 1));
        scene5.add(new Stone(x / 8 + 230, y / 2 + 80, 1));
        scene5.add(new Stone(x / 2 + 100, y / 2 + 295, 1));

        scene6.add(new Gold(x / 8 + 35, y / 2 - 40));
        scene6.add(new Gold(x / 8 + 200, y / 2 + 260));
        scene6.add(new Gold(x / 8 + 360, y / 2 + 290));
        scene6.add(new Gold(x / 8 + 540, y / 2 + 270));
        scene6.add(new Gold(x - 70, y / 2));
        scene6.add(new Gold(x / 8 + 85, y / 2 + 20, Gold.TINY_GOLD_SCALE_RATIO));
        scene6.add(new Gold(x / 2, y / 2 + 20, Gold.TINY_GOLD_SCALE_RATIO));
        scene6.add(new TreasureBag(x - 200, y / 2 + 40));
        scene6.add(new Skull(x / 8 + 150, y / 2 + 140));
        scene6.add(new Skull(x - 270, y / 2 + 40));
        scene6.add(new Bone(x - 250, y / 2 + 20));
        scene6.add(new Bone(x / 8 + 600, y / 2 + 210));
        scene6.add(new Explosive(x / 8 + 300, y / 2 + 200));
        scene6.add(new Explosive(x / 8 + 460, y / 2 + 200));

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
