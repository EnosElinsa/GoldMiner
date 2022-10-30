package gamebody.main;

import gamebody.engine.GameObject;
import gamebody.object.Gold;
import gamebody.object.Stone;
import gamebody.object.TreasureBag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameWindow extends JFrame implements Runnable, KeyListener {

    public static final int INIT_WIDTH = 960; // 默认窗口宽度960px
    public static final int INIT_HEIGHT = 540 + 124 + 30; // 默认窗口高度540px(关卡背景高度)+124px(陆地背景高度)+30px(填充)
    public static final int TIME_PER_FRAME = 80; // 更新一帧所需的时间，单位是微秒
    private Dimension dimension = new Dimension(INIT_WIDTH, INIT_HEIGHT);;
    private Image icon = new ImageIcon("resources/miner-dig-0.png").getImage(); // 窗口图标

    private Image offScreenImage; // 用于双缓存的辅助画板
    private Background background;
    private Miner miner = new Miner(); // 矿工
    private Rope rope = new Rope(this); // 绳索
    private ArrayList<GameObject> gameobjects = new ArrayList<>();

    private Time time = new Time();
    private UI ui = new UI(this);
    private Thread gameWindowThread = new Thread(this); // 窗口线程

    private static int level = 1; //关卡数
    private static int target = 105 + 545 * level + 135 * (level - 1) * (level - 2); // 目标分数
    
    public GameWindow() {}

    public void launch() {
        setVisible(true);
        setSize(getDimension());
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("GoldMiner");
        setIconImage(icon);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addKeyListener(this); // 给窗口注册键盘事件监听器
        loadGameObjects();
        repaint();

        gameWindowThread.start(); // 开启窗口线程
    }

    /**
     * 加载游戏物体
     */
    private void loadGameObjects() {
        background = new Background(level);
        gameobjects.add(new Gold(600, 500));
        gameobjects.add(new Stone(500, 500,"resources/stone-0.png" ,0)); //value=11，0号石头
        gameobjects.add(new Stone(200, 400, "resources/stone-1.png",1)); //value=20，1号石头
        gameobjects.add(new TreasureBag(250, 444));
        gameobjects.add(new Gold(300, 222, 0.5));
        gameobjects.add(new Gold(400, 400, 0.3));
    }
    
    private void update() {
        if (rope.getCurrentState() == RopeState.RETRIEVE
        && miner.getCurrentState() != MinerState.PULL) {      // 当绳索状态处于“收取”时
            miner.setCurrentState(MinerState.PULL);           // 将矿工的状态设置为“拉”
        }
        else if (rope.getCurrentState() == RopeState.SWING) { // 当绳索状态处于“摇摆”时
            miner.setCurrentState(MinerState.IDLE);           // 将矿工的状态设置为“静置”
        }

        if (time.countDown() < 0) {
            // nextLevel();
        }
    }
    
    public void nextLevel () {
        //达到下一关的条件
        if (rope.getOverallValue() >= target)
        {
            System.out.println("已经达到过关条件");
            level++;
            dispose();
            launch();
        }
    }
    
    @Override
    public void paint(Graphics graphics) {
        offScreenImage = createImage(INIT_WIDTH, INIT_HEIGHT); // 每次进行绘制的时候，都重新创建一个辅助画板

        // 将游戏元素都绘制在辅助画板上
        Graphics graphics2 = offScreenImage.getGraphics();
        // 画背景
        background.render(graphics2);
        // 画矿工
        miner.render(graphics2);
        // 画绳索
        rope.render(graphics2);
        // 画物体（金块、石头、钱袋、钻石）
        for (GameObject object : gameobjects) {
            object.render(graphics2);
        }
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

    public ArrayList<GameObject> getGameobjects() {
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

