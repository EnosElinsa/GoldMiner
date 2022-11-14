package gamebody.scenes;

import gamebody.engine.GameObject;
import gamebody.scenes.items.*;

import java.util.Vector;

/**
 * <p>游戏场景类。
 * <p>{@code scenes}集合中包含了每一关中的背景以及游戏物品对象的集合。
 * <p>{@link#createScene}生成指定关卡的游戏场景
 * <p>{@link#getGameObjects}获得指定关卡的游戏物品对象
 * 
 * @author Enos
 * @see Background
 * @see GameWindow
 */
public class Scene {

    /**
     * 集合中包含了每一关中的背景以及游戏物品对象的集合。
     */
    private Vector<Vector<GameObject>> scenes = new Vector<Vector<GameObject>>();

    /**
     * 游戏的背景。
     * <p>一开始因为不指定特定关卡，没有对应的背景图，在设置关卡的过程会根据关卡数来设置其背景图片。
     */
    private Background background = new Background();

    /**
     * {@code GameWindow}游戏窗口主体的引用，用于获取游戏场景下的其他游戏物体。
     */
    private GameWindow gameWindow;
    
    /** 存储每一关中的背景以及游戏物品对象的集合 */

    /** 场景0 */
    private Vector<GameObject> scene0;

    /** 场景1 */
    private Vector<GameObject> scene1;

    /** 场景2 */
    private Vector<GameObject> scene2;

    /** 场景3 */
    private Vector<GameObject> scene3;

    /** 场景4 */
    private Vector<GameObject> scene4;

    /** 场景5 */
    private Vector<GameObject> scene5;

    /** 场景6 */
    private Vector<GameObject> scene6;

    /** 场景7 */
    private Vector<GameObject> scene7;

    /** 场景8 */
    private Vector<GameObject> scene8;

    /** 场景9 */
    private Vector<GameObject> scene9;

    /**
     * 生成场景类的唯一构造方法。
     * @param gameWindow 游戏窗口主体的引用，用于获取游戏场景下的其他游戏物体。
     */
    public Scene(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        intialize();
    }

    /**
     * 初始化。
     * <p>初始化{@code scenes}集合中的元素。
     */
    public void intialize() {
        for (int index = 0; index < GameWindow.LEVEL_NUMBER; index++) {
            Vector<GameObject> newScene = new Vector<>();
            scenes.add(newScene);
            // 每个场景都需要添加一个背景
            newScene.add(background);
        }

        scene0 = scenes.get(0);
        scene1 = scenes.get(1);
        scene2 = scenes.get(2);
        scene3 = scenes.get(3);
        scene4 = scenes.get(4);
        scene5 = scenes.get(5);
        scene6 = scenes.get(6);
        scene7 = scenes.get(7);
        scene8 = scenes.get(8);
        scene9 = scenes.get(9);

        for (int index = 1; index <= 10; index++) {
            createScene(index);
        }
    }

    /**
     * 根据关卡数生成对应的游戏场景。
     * @param level 关卡数
     */
    public void createScene(int level) {
        int x = GameWindow.INIT_WIDTH;
        int y = GameWindow.INIT_HEIGHT;
        switch (level) {
        case 1:
            // 如果该场景之前被创建和使用过，需要重新创建这个场景。
            if (scene0.size() >= 1) {
                scene0.removeAll(scene0);
                scene0.add(background);
            }
            // 往游戏场景集合中添加游戏物品对象。
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
            break;
        case 2:
            if (scene1.size() >= 1) {
                scene1.removeAll(scene1);
                scene1.add(background);
            }
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
            break;
        case 3:
            if (scene2.size() >= 1) {
                scene2.removeAll(scene2);
                scene2.add(background);
            }
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
            break;
        case 4:
            if (scene3.size() >= 1) {
                scene3.removeAll(scene3);
                scene3.add(background);
            }
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
            scene3.add(new Pig(0, y / 2, x / 2 + 50));
            scene3.add(new Pig(50, y / 2 + 140, x / 2 + 80));
            scene3.add(new Pig(x - 100, y / 2 + 80, x - 10));
            break;
        case 5:
            if (scene4.size() >= 1) {
                scene4.removeAll(scene4);
                scene4.add(background);
            }
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
            scene4.add(new Pig(0, y / 2 - 50, x / 2));
            scene4.add(new Pig(x - 300, y / 2 + 140, x));
            break;
        case 6:
            if (scene5.size() >= 1) {
                scene5.removeAll(scene5);
                scene5.add(background);
            }
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
            scene5.add(new GemPig(0, y / 2 - 40, x / 2 - 20));
            scene5.add(new GemPig(x - 300, y / 2 + 30, x));
            scene5.add(new GemPig(0, y / 2 + 150, x / 2));
            scene5.add(new GemPig(x - 500, y / 2 + 150, x));
            break;
        case 7:
            if (scene6.size() >= 1) {
                scene6.removeAll(scene6);
                scene6.add(background);
            }
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
            scene6.add(new Explosive(x / 8 + 300, y / 2 + 200, gameWindow));
            scene6.add(new Explosive(x / 8 + 460, y / 2 + 200, gameWindow));
            scene6.add(new Pig(x / 8 + 100, y / 2 - 40, x / 2));
            scene6.add(new Pig(0, y / 2 + 80, x / 2 - 50));
            scene6.add(new Pig(20, y / 2 + 180, x / 2 - 160));
            scene6.add(new Pig(x - 350, y / 2 + 100, x - 5));
            break;
        case 8:
            if (scene7.size() >= 1) {
                scene7.removeAll(scene7);
                scene7.add(background);
            }
            scene7.add(new Diamond(x / 8 + 30, y / 2 + 190));
            scene7.add(new Diamond(x / 8 + 220, y / 2 + 200));
            scene7.add(new Diamond(x / 8 + 450, y / 2 + 250));
            scene7.add(new Diamond(x / 4 - 60, y / 3 + 60));
            scene7.add(new Diamond(x / 4 + 90, y / 3 + 100));
            scene7.add(new Diamond(x / 4 + 260, y / 3 + 145));
            scene7.add(new Diamond(x / 4 + 400, y / 3 + 185));
            scene7.add(new Diamond(x / 2 + 390, y / 2 - 100));
            scene7.add(new TreasureBag(x / 8 - 30, y / 2 + 250));
            scene7.add(new TreasureBag(x / 2 + 410, y / 2 + 210));
            scene7.add(new Explosive(x / 8, y / 2 + 70, gameWindow));
            scene7.add(new Explosive(x / 8 + 160, y / 2 + 150, gameWindow));
            scene7.add(new Explosive(x / 4 + 350, y / 3 + 265, gameWindow));
            scene7.add(new Explosive(x / 2 + 320, y / 2 - 20, gameWindow));
            scene7.add(new Explosive(x / 2 + 370, y / 2 + 285, gameWindow));
            scene7.add(new GemPig(x / 8, y / 2 + 30, x / 2));
            scene7.add(new GemPig(x / 8 + 200, y / 2 + 120, x / 2 + 20));
            scene7.add(new GemPig(x - 350, y / 2 - 50, x));
            break;
        case 9:
            if (scene8.size() >= 1) {
                scene8.removeAll(scene8);
                scene8.add(background);
            }
            scene8.add(new Gold(x / 8 + 130, y / 2 + 240));
            scene8.add(new Gold(x / 2 + 270, y / 2 + 245));
            scene8.add(new Gold(x / 4 - 10, y / 3, Gold.TINY_GOLD_SCALE_RATIO));
            scene8.add(new Diamond(x / 8 + 25, y / 2 + 150));
            scene8.add(new Diamond(x / 2 + 55, y / 2 + 60));
            scene8.add(new Diamond(x / 2 - 60, y / 2 + 60));
            scene8.add(new Diamond(x - 65, y - 120));
            scene8.add(new Diamond(x / 2 + 75, y / 2 + 310));
            scene8.add(new Diamond(x / 2 + 215, y / 2 - 25));
            scene8.add(new TreasureBag(x / 2, y / 2 + 300));
            scene8.add(new TreasureBag(x - 55, y - 205));
            scene8.add(new TreasureBag(50, y / 2));
            scene8.add(new Skull(x / 2 + 50, y / 2 + 210));
            scene8.add(new Bone(x - 100, y - 100));
            scene8.add(new Explosive(x / 2, y / 2 + 110, gameWindow));
            scene8.add(new Explosive(x / 2 - 120, y / 2 + 110, gameWindow));
            scene8.add(new Explosive(x / 2 + 120, y / 2 + 110, gameWindow));
            scene8.add(new Explosive(x / 4 + 20, y / 3 + 75, gameWindow));
            scene8.add(new Explosive(x / 8 - 30, y / 2 + 290, gameWindow));
            scene8.add(new Explosive(x - 60, y - 60, gameWindow));
            scene8.add(new Explosive(x - 220, 300, gameWindow));
            scene8.add(new GemPig(60, y / 2, x / 2));
            scene8.add(new GemPig(0, y / 2 + 110, x / 2 - 150));
            scene8.add(new GemPig(x / 2 + 140, y / 2 + 110, x));
            scene8.add(new GemPig(x / 2, y / 3 + 115, x));
            break;
        case 10:
            if (scene9.size() >= 1) {
                scene9.removeAll(scene9);
                scene9.add(background);
            }
            scene9.add(new Gold(205, y - 120));
            scene9.add(new Gold(x - 235, y - 80));
            scene9.add(new Gold(x / 2 - 50, y / 2 - 80, Gold.TINY_GOLD_SCALE_RATIO));
            scene9.add(new Diamond(150, y - 195));
            scene9.add(new Diamond(280, y - 90));
            scene9.add(new Diamond(x / 2 - 70, y / 2 + 50));
            scene9.add(new Diamond(x - 170, y / 2 + 130));
            scene9.add(new Diamond(x - 110, y / 2 + 60));
            scene9.add(new Diamond(x - 60, y / 2 + 65));
            scene9.add(new Diamond(x - 40, y - 100));
            scene9.add(new TreasureBag(45, y / 2 - 30));
            scene9.add(new TreasureBag(x / 2 - 90, y - 80));
            scene9.add(new TreasureBag(x - 105, y / 2 + 160));
            scene9.add(new Skull(x / 2, y / 2 + 180));
            scene9.add(new Bone(x - 155, y / 2 + 210));
            scene9.add(new Explosive(100, y - 165, gameWindow));
            scene9.add(new Explosive(295, y - 30, gameWindow));
            scene9.add(new Explosive(x / 2 - 135, y / 2, gameWindow));
            scene9.add(new Explosive(x / 2 - 5, y - 80, gameWindow));
            scene9.add(new Explosive(x / 2 + 115, y / 2 + 215, gameWindow));
            scene9.add(new Explosive(x - 110, y - 80, gameWindow));
            scene9.add(new Explosive(x, y - 185, gameWindow));
            scene9.add(new GemPig(0, y / 2, x / 2));
            scene9.add(new GemPig(10, y / 2 + 60, x / 2));
            scene9.add(new GemPig(x / 2, y / 2 + 30, x));
            scene9.add(new GemPig(x / 2 - 20, y / 2 + 130, x));
            break;
        default:
            System.out.println("Invail index");
        }
    }

    /**
     * 获取指定关卡下的游戏场景游戏对象的集合。
     * @param level 指定的关卡数
     * @return 返回包含指定关卡下的游戏场景游戏对象的集合
     */
    public Vector<GameObject> getGameObjects(int level) {
        background.setLevelBackground(level);
        return scenes.get(level);
    }
}
