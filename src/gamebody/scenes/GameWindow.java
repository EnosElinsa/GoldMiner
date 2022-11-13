package gamebody.scenes;

import gamebody.engine.Audio;
import gamebody.engine.GameObject;
import gamebody.scenes.characters.*;
import gamebody.scenes.items.Dynamite;
import gamebody.ui.Cutscene;
import gamebody.ui.Menu;
import gamebody.ui.Shop;
import gamebody.ui.Time;
import gamebody.ui.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * <p>游戏窗口类。
 * <p>游戏的界面设置在这个类中，包括窗口的绘制、背景图的绘制、图标的绘制、窗口名称
 * <p>以及菜单界面、商店界面、过关提示界面、失败界面
 * <p>还有矿工的绘制、绳索的绘制、炸药的绘制、其它UI文字的绘制
 *
 * @author Enos
 * @author JiajiaPig
 */

public class GameWindow extends JFrame implements Runnable, KeyListener {

    public static final int INIT_WIDTH = 960; // 默认窗口宽度960px
    public static final int INIT_HEIGHT = 540 + 124 + 30; // 默认窗口高度540px(关卡背景高度)+124px(陆地背景高度)+30px(填充)
    public static final int TIME_PER_FRAME = 80; // 更新一帧所需的时间，单位是微秒
    public static final int LEVEL_NUMBER = 10; // 10种不同的关卡场景

    private Dimension dimension = new Dimension(INIT_WIDTH, INIT_HEIGHT);
    private Image icon = new ImageIcon("resources/miner-dig-0.png").getImage(); // 窗口图标

    private JPanel windowPanel = new JPanel();
    private Menu menu = new Menu(this);//菜单对象
    private JPanel gameScenePanel = new JPanel();
    private Shop shop = new Shop(this);//商店对象

    private Cutscene cutscene0 = new Cutscene(0);//表示未达到目标，游戏失败时的画面

    private Cutscene cutscene1 = new Cutscene(1);//表示完成任务，准备进入下一关的画面

    private Cutscene cutscene2 = new Cutscene(2);//表示进入下一关
    private CardLayout cardLayout = new CardLayout();

    private boolean isInMainScene = false;//判断当前是否是主游戏界面
    private boolean nextLevelSignal = false;//用于表示是否进入下一关的信号

    private Image offScreenImage; // 用于双缓存的辅助画板
    private Scene scene = new Scene(this);  // 场景
    private Miner miner = new Miner();  // 矿工
    private Rope rope = new Rope(this); // 绳索
    private Dynamite dynamite;//炸药
    private Vector<GameObject> gameobjects = scene.getGameObjects(0);//物体数组

    private Time time;//时间
    private UI ui = new UI(this);
    private Audio digSound = new Audio("sound/sound_wav/dig.wav");      // 矿工挖音效
    private Audio pullSound = new Audio("sound/sound_wav/pull.wav");    // 矿工拉音效
    private Audio cutSceneSound1 = new Audio("sound/sound_wav/cut-scene-1.wav");//完成任务，准备进入下一关的音效
    private Audio cutSceneSound2 = new Audio("sound/sound_wav/cut-scene-2.wav");//进入下一关的音效
    private Thread gameWindowThread = new Thread(this); // 窗口线程
    
    private static int level = 1; // 关卡数
    private static int target = 105 + 545 * level + 135 * (level - 1) * (level - 2); // 目标分数
    private int dynamiteCount = 0; // 炸药数量初始化为0
    
    public GameWindow() {
        gameWindowThread.start(); // 开启窗口线程
    }

    /**
     * 窗口元素绘制
     */
    public void launch() {
        windowPanel.setLayout(cardLayout);
        windowPanel.add("gameScenePanel", gameScenePanel);
        windowPanel.add("menu", menu);
        windowPanel.add("shop", shop);
        windowPanel.add("cutscene0", cutscene0);
        windowPanel.add("cutscene1", cutscene1);
        windowPanel.add("cutscene2", cutscene2);
        add(windowPanel);
        setVisible(true);
        setSize(getDimension());
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("GoldMiner");
        setIconImage(icon);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this); // 给窗口注册键盘事件监听器
        startGame();
    }

    /**
     * 开始新的游戏
     */
    public void startGame() {
        //显示进入下一关的画面
        windowPanel.setVisible(true);
        cutscene2.setGoalScore(target);//传入并显示目标分数值
        cardLayout.show(windowPanel, "cutscene2"); 
        cutSceneSound2.play(1);
        delay(2000);//画面显示2s，2s后加载新的游戏画面

        //加载新的游戏画面
        loadGameObjects(); // 加载游戏场景的物体
        rope.setStopSignal(false);
        cardLayout.show(windowPanel, "gameScenePanel");
        isInMainScene = true;//当前是主游戏界面
        time = new Time(); //时间重置
    }

    /**
     * 加载游戏物体
     */
    private void loadGameObjects() {
        gameobjects = scene.getGameObjects((level - 1) % LEVEL_NUMBER);
    }
    
    /**
     * 延迟
     * @param millis 微秒
     */
    public void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止当前所有进行更新的对象的活动
     */
    private void stopCurrentActivity() {
        digSound.play(2);
        pullSound.play(2);
        // 停止绳索和矿工的动作
        rope.setStopSignal(true);
        miner.setCurrentState(MinerState.IDLE);   
    }

    /**
     * 更新状态
     */
    private void update() {
        if (rope.getCurrentState() == RopeState.RETRIEVE
            && miner.getCurrentState() != MinerState.PULL) {     // 当绳索状态处于“收取”时
            pullSound.play(3);
            miner.setCurrentState(MinerState.PULL);              // 将矿工的状态设置为“拉”
        }
        else if (rope.getCurrentState() == RopeState.SWING) {    // 当绳索状态处于“摇摆”时
            pullSound.play(2);
            miner.setCurrentState(MinerState.IDLE);              // 将矿工的状态设置为“静置”
        }

        //如果时间到了，跳转到判断是否过关
        if (time.countDown() < 0) {
            settle();
        }
    }

    /**
     * 判断是否达到过关条件，如果达到了，就显示商店界面购买道具；如果没达到，就显示游戏失败界面
     */
    private void settle () {
<<<<<<< HEAD
        //达到下一关的条件
        isInMainScene = false;//不在主游戏界面了
        stopCurrentActivity();//停止当前的动作
        //如果已得金钱大于或等于目标金钱，即达到过关条件
=======
        // 达到下一关的条件
        isInMainScene = false;
        stopCurrentActivity();                
>>>>>>> main
        if (rope.getOverallValue() >= target) {
            level++;//关卡数增加
            target = 105 + 545 * level + 135 * (level - 1) * (level - 2);//根据公式重置新的目标分数

            //显示过关的界面
            cardLayout.show(windowPanel, "cutscene1");
            cutSceneSound1.play(1);
            delay(2000);//过关界面显示2s

            //显示商店界面
            shop.launchShop();
            cardLayout.show(windowPanel, "shop");
        }
        //如果未达到目标，则显示游戏失败界面
        else {
            cardLayout.show(windowPanel, "cutscene0");
            delay(3000);//失败界面显示3s
            level = 1;//关卡数设置为1，需要重新开始
            target = 105 + 545 * level + 135 * (level - 1) * (level - 2);//重置目标分数
            scene.createScene(1);
            loadGameObjects();//加载游戏物体
            cardLayout.show(windowPanel, "menu");
        }
    }

    /**
     * 进入下一关
     */
    private void levelUp() {
        scene.createScene(level - 1); // 进入下一关前，恢复上一个关卡的场景
        // 显示下一关目标界面
        cutscene2.setGoalScore(target);
        cardLayout.show(windowPanel, "cutscene2"); 
        cutSceneSound2.play(1);
        // 判断是否购买完毕
        if (shop.getIsBuyFinish()) {
            rope.setIsShop(true); // 告诉rope那边商店购买已经结束了，可以生成商品属性效果了
            rope.setProduct(shop.getProductStatus());//将shop对象里的所有商品是否被购买的状态传递给rope对象，道具生效需要在rope对象里执行
            rope.setOverallValue(rope.getOverallValue() - shop.getTotalMoney()); // 把购买商品总共花费的钱扣除
        }
        delay(2000);//点击下一关后延迟2s
        // 显示游戏界面
        loadGameObjects();
        rope.setStopSignal(false);
        isInMainScene = true;
        nextLevelSignal = false;
        cardLayout.show(windowPanel, "gameScenePanel");
        time = new Time();
    }
    
    @Override
    public void paint(Graphics graphics) {
        if (isInMainScene) {
            offScreenImage = createImage(INIT_WIDTH, INIT_HEIGHT); // 每次进行绘制的时候，都重新创建一个辅助画板

            // 将游戏元素都绘制在辅助画板上
            Graphics graphics2 = offScreenImage.getGraphics();
            // 绘制场景
            for (GameObject object : gameobjects) {
                object.render(graphics2, gameScenePanel);
            }
            // 绘制矿工
            miner.render(graphics2, gameScenePanel);
            // 绘制绳索
            rope.render(graphics2, gameScenePanel);
            // 绘制UI
            ui.render(graphics2, gameScenePanel);

            //如果dynamite对象不为空，则画出该对象
            if (dynamite != null) {
                dynamite.render(graphics2, gameScenePanel);
            }
    
            // 如果炸药消失了，即炸开了，将dynamite对象置为空，重新设置rope的速度为正常速度，碰撞检测为false
            if (dynamite != null && dynamite.isVanished()) {
                dynamite = null;
                rope.setRetrieveRate(Rope.INIT_RETRIEVE_RATE);
                rope.setColliding(false);
            }
            
            // 将辅助画板绘制在原本的画板上
            graphics.drawImage(offScreenImage, 0, 0, gameScenePanel);
        }
    }

    @Override
    public void run() {
        while (true) {
            setFocusable(true);
            if (isInMainScene) {
                repaint(); // 重新绘制画板
                update();//更新状态
            }
            if (nextLevelSignal) {
                levelUp();//进入下一关
            }
            delay(TIME_PER_FRAME);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {            // 当按下“↓”键时
            if (rope.getCurrentState() == RopeState.SWING) { // 当绳索处于摆动状态的时候
                digSound.play(1);
                rope.setCurrentState(RopeState.GRAB);        // 将绳索的状态设为“抓取”
                miner.setCurrentState(MinerState.DIG);       // 将矿工的状态设为“挖”
            }
        }
        //当按下“↑”键时且rope目前的状态为抓取状态，且碰撞检测成功时
        else if (e.getKeyCode() == KeyEvent.VK_UP && rope.getCurrentState() == RopeState.RETRIEVE && rope.isColliding()) {
            //如果炸药数量大于0，则会触发爆炸效果
            if (dynamiteCount > 0) {
                dynamite = new Dynamite(this);
                miner.setCurrentState(MinerState.THROW);//将矿工的状态设置为扔炸弹
                dynamiteCount--;//炸药数量减少一个
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {}

    @Override
    public void keyTyped(KeyEvent arg0) {}


    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(int height, int width) {
        dimension.height = height;
        dimension.width = width;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getWindowPanel() {
        return windowPanel;
    }

    public Vector<GameObject> getGameobjects() {
        return gameobjects;
    }

    public Miner getMiner() {
        return miner;
    }

    public Rope getRope() {
        return rope;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Shop getShop() {
        return shop;
    }

    public int getLevel() {
        return level;
    }

    public int getTarget() {
        return target;
    }

    public boolean isInMainScene() {
        return isInMainScene;
    }

    public void setInMainScene(boolean isInMainScene) {
        this.isInMainScene = isInMainScene;
    }

    public boolean isNextLevelSignal() {
        return nextLevelSignal;
    }

    public void setNextLevelSignal(boolean nextLevelSignal) {
        this.nextLevelSignal = nextLevelSignal;
    }

    public void setDynamiteCount(int dynamiteCount) {
        this.dynamiteCount = dynamiteCount;
    }

    public int getDynamiteCount() {
        return dynamiteCount;
    }
}

