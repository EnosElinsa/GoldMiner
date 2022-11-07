package gamebody.scenes;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gamebody.engine.Audio;
import gamebody.engine.GameObject;
import gamebody.scenes.characters.Miner;
import gamebody.scenes.characters.MinerState;
import gamebody.scenes.characters.Rope;
import gamebody.scenes.characters.RopeState;
import gamebody.ui.Cutscene;
import gamebody.ui.Shop;
import gamebody.ui.Time;
import gamebody.ui.UI;

public class GameWindow extends JFrame implements Runnable, KeyListener {

    public static final int INIT_WIDTH = 960; // 默认窗口宽度960px
    public static final int INIT_HEIGHT = 540 + 124 + 30; // 默认窗口高度540px(关卡背景高度)+124px(陆地背景高度)+30px(填充)
    public static final int TIME_PER_FRAME = 80; // 更新一帧所需的时间，单位是微秒
    public static final int LEVEL_NUMBER = 10; // 10种不同的关卡场景

    private Dimension dimension = new Dimension(INIT_WIDTH, INIT_HEIGHT);
    private Image icon = new ImageIcon("resources/miner-dig-0.png").getImage(); // 窗口图标
    private JPanel windowPanel = new JPanel();
    private JPanel gameScenePanel = new JPanel();
    private Shop shop = new Shop(this);
    private Cutscene cutscene0 = new Cutscene(0);
    private Cutscene cutscene1 = new Cutscene(1);
    private Cutscene cutscene2 = new Cutscene(2);
    private CardLayout cardLayout = new CardLayout();
    private boolean isInMainScene = true;
    private boolean nextLevelSignal = false;

    private Image offScreenImage; // 用于双缓存的辅助画板
    private Scene scene = new Scene();  // 场景
    private Miner miner = new Miner();  // 矿工
    private Rope rope = new Rope(this); // 绳索
    private Vector<GameObject> gameobjects = scene.getGameObjects(0);

    private Time time = new Time();
    private UI ui = new UI(this);
    // private Sound bgSound=new Sound("sound/sound_wav/cut-scene.wav");    // 背景音效
    private Audio digSound = new Audio("sound/sound_wav/dig.wav");      // 矿工挖音效
    private Audio pullSound = new Audio("sound/sound_wav/pull.wav");    // 矿工拉音效
    private Audio cutSceneSound1 = new Audio("sound/sound_wav/cut-scene-1.wav");
    private Audio cutSceneSound2 = new Audio("sound/sound_wav/cut-scene-2.wav");
    private Thread gameWindowThread = new Thread(this);                   // 窗口线程
    
    private static int level = 1; // 关卡数
    private static int target = 105 + 545 * level + 135 * (level - 1) * (level - 2); // 目标分数
    
    public GameWindow() {
        gameWindowThread.start(); // 开启窗口线程
    }

    public void launch() {
        //bgSound.musicMain(3);
        setVisible(true);
        windowPanel.setLayout(cardLayout);
        windowPanel.add("gameScenePanel", gameScenePanel);
        windowPanel.add("shop", shop);
        windowPanel.add("cutscene0", cutscene0);
        windowPanel.add("cutscene1", cutscene1);
        windowPanel.add("cutscene2", cutscene2);
        add(windowPanel);
        setSize(getDimension());
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("GoldMiner");
        setIconImage(icon);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addKeyListener(this); // 给窗口注册键盘事件监听器
        loadGameObjects(); // 加载游戏场景的物体
        repaint();
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
        digSound.musicMain(2);
        pullSound.musicMain(2);
        // 停止绳索和矿工的动作
        rope.setStopSignal(true);
        miner.setCurrentState(MinerState.IDLE);   
    }

    private void update() {
        if (rope.getCurrentState() == RopeState.RETRIEVE
        && miner.getCurrentState() != MinerState.PULL) {      // 当绳索状态处于“收取”时
            pullSound.musicMain(3);
            miner.setCurrentState(MinerState.PULL);           // 将矿工的状态设置为“拉”
        }
        else if (rope.getCurrentState() == RopeState.SWING) { // 当绳索状态处于“摇摆”时
            pullSound.musicMain(2);
            miner.setCurrentState(MinerState.IDLE);           // 将矿工的状态设置为“静置”
        }

        if (time.countDown() < 0) {
            settle();
        }
    }
    
    private void settle () {
        //达到下一关的条件
        isInMainScene = false;
        stopCurrentActivity();                
        if (rope.getOverallValue() >= target) {
            System.out.println("已经达到过关条件");
            level++;
            target = 105 + 545 * level + 135 * (level - 1) * (level - 2);
            
            System.out.println("显示过关的界面");
            cardLayout.show(windowPanel, "cutscene1");
            cutSceneSound1.musicMain(1);
            delay(2000);
            
            shop.launchShop();
            System.out.println("显示商店界面");
            cardLayout.show(windowPanel, "shop");
        } else {
            cardLayout.show(windowPanel, "cutscene0");
        }
    }

    private void levelUp() {
        scene.createScene(level - 1); // 进入下一关前，恢复上一个关卡的场景
        // 显示下一关目标界面
        System.out.println("显示下一关目标界面");
        cutscene2.setGoalScore(target);
        cardLayout.show(windowPanel, "cutscene2"); 
        cutSceneSound2.musicMain(1);
        delay(2000);
        // 显示游戏界面
        loadGameObjects();
        rope.setStopSignal(false);
        isInMainScene = true;
        nextLevelSignal = false;
        System.out.println("显示游戏界面");
        cardLayout.show(windowPanel, "gameScenePanel");
        time = new Time();
    }
    
    @Override
    public void paint(Graphics graphics) {
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
        
        // 将辅助画板绘制在原本的画板上
        graphics.drawImage(offScreenImage, 0, 0, gameScenePanel);
    }

    @Override
    public void run() {
        while (true) {
            if (isInMainScene) {
                setFocusable(true);
                repaint(); // 重新绘制画板
                update();
            }
            if (nextLevelSignal) {
                levelUp();
            }
            delay(TIME_PER_FRAME);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {            // 当按下“↓”键时
            if (rope.getCurrentState() == RopeState.SWING) { // 当绳索处于摆动状态的时候
                digSound.musicMain(1);
                rope.setCurrentState(RopeState.GRAB);        // 将绳索的状态设为“抓取”
                miner.setCurrentState(MinerState.DIG);       // 将矿工的状态设为“挖”
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

    
}

