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
        scene0.add(new Gold(x - 20, y / 3 * 2));
        scene0.add(new Gold(x / 7, y / 3 * 4, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 6, y / 3 * 4 - 20, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 2 + 20, y / 2, Gold.MIDDLE_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 4 - 10, y / 4, Gold.TINY_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 4 + 10, y / 3, Gold.TINY_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 4 * 3, y / 4 + 5, Gold.TINY_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 4 * 3 - 10, y / 4 + 15, Gold.TINY_GOLD_SCALE_RATIO));
        scene0.add(new Gold(x / 4 * 3 + 15, y / 4, Gold.TINY_GOLD_SCALE_RATIO));
    }

    public Vector<GameObject> getGameObjects(int level) {
        background.setLevelBackground(level);
        return scenes.get(level);
    }
}
