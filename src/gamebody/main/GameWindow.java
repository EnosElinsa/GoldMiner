package gamebody.main;

import gamebody.engine.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class GameWindow extends JFrame implements Runnable, KeyListener {

    public static final int INIT_WIDTH = 960; // 默认窗口宽度960px
    public static final int INIT_HEIGHT = 540 + 124 + 30; // 默认窗口高度540px(关卡背景高度)+124px(陆地背景高度)+30px(填充)
    public static final int TIME_PER_FRAME = 80; // 更新一帧所需的时间，单位是微秒
    public static final int LEVEL_NUMBER = 10; // 10种不同的关卡场景
    private Dimension dimension = new Dimension(INIT_WIDTH, INIT_HEIGHT);
    private Image icon = new ImageIcon("resources/miner-dig-0.png").getImage(); // 窗口图标

    private Image offScreenImage; // 用于双缓存的辅助画板
    private Scene scene = new Scene();
    private Miner miner = new Miner(); // 矿工
    private Rope rope = new Rope(this); // 绳索
    private Vector<GameObject> gameobjects = scene.getGameObjects(0);

    private Time time = new Time();
    private UI ui = new UI(this);
    private Thread gameWindowThread = new Thread(this); // 窗口线程

    private static int level = 8; //关卡数
    private static int target = 105 + 545 * level + 135 * (level - 1) * (level - 2); // 目标分数
    
    public GameWindow() {
        gameWindowThread.start(); // 开启窗口线程
    }

    private Sound bgSound=new Sound("sound/sound_wav/cut-scene.wav");//背景音效

    private Sound digSound=new Sound("sound/sound_wav/dig.wav");//矿工挖音效

    private Sound pullSound=new Sound("sound/sound_wav/pull.wav");//矿工拉音效

    public void launch() {
        //bgSound.musicMain(3);
        setVisible(true);
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
            nextLevel();
        }
    }
    
    public void nextLevel () {
        //达到下一关的条件
        if (rope.getOverallValue() >= target) {
            System.out.println("已经达到过关条件");
            dispose();
            level++;
            target = 105 + 545 * level + 135 * (level - 1) * (level - 2);
            time = new Time();
            miner.setCurrentState(MinerState.IDLE);
            rope.setCurrentState(RopeState.SWING);
            rope.setLength(Rope.MIN_LENGTH);
            launch();
        }
    }
    
    @Override
    public void paint(Graphics graphics) {
        offScreenImage = createImage(INIT_WIDTH, INIT_HEIGHT); // 每次进行绘制的时候，都重新创建一个辅助画板

        // 将游戏元素都绘制在辅助画板上
        Graphics graphics2 = offScreenImage.getGraphics();
        // 绘制场景
        for (GameObject object : gameobjects) {
            object.render(graphics2);
        }
        // 绘制矿工
        miner.render(graphics2);
        // 绘制绳索
        rope.render(graphics2);
        // 绘制UI
        ui.render(graphics2);
        
        // 将辅助画板绘制在原本的画板上
        graphics.drawImage(offScreenImage, 0, 0, null);
    }



    @Override
    public void run() {
        while (true) {
            repaint(); // 重新绘制画板
            update();
            try {
                Thread.sleep(TIME_PER_FRAME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public int getLevel() {
        return level;
    }

    public int getTarget() {
        return target;
    }
}

