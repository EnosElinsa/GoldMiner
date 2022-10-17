package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame implements Runnable, KeyListener {
    public static final int INIT_WIDTH = 960; // 默认窗口宽度960px
    public static final int INIT_HEIGHT = 540 + 124 + 30; // 默认窗口高度540px(关卡背景高度)+124px(陆地背景高度)+30px(填充)
    public static final int TIME_PER_FRAME = 80; // 更新一帧所需的时间，单位是微秒
    private Dimension dimension = new Dimension(INIT_WIDTH, INIT_HEIGHT);;
    private Image icon = new ImageIcon("resources/miner-dig-0.png").getImage(); // 窗口图标
    private Background background = new Background();
    private Image offScreenImage; // 用于双缓存的辅助画板
    private Thread gameWindowThread = new Thread(this); // 窗口线程
    private Miner miner = new Miner(); // 矿工
    private Rope rope = new Rope(); // 绳索

    public GameWindow() {
        this.repaint();
        gameWindowThread.start(); // 开启窗口线程
    }

    public void launch() {
        this.setVisible(true);
        this.setSize(getDimension());
        this.setResizable(false);
        this.setLocationRelativeTo(null);   
        this.setTitle("GoldMiner");
        this.setIconImage(icon);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this); // 给窗口注册键盘事件监听器
    }

    @Override
    public void paint(Graphics graphics) {
        offScreenImage = this.createImage(INIT_WIDTH, INIT_HEIGHT); // 每次进行绘制的时候，都重新创建一个辅助画板

        // 将游戏元素都绘制在辅助画板上
        Graphics graphics2 = offScreenImage.getGraphics();
        background.drawSelf(graphics2);
        miner.drawSelf(graphics2);
        rope.drawSelf(graphics2);

        // 将辅助画板绘制在原本的画板上
        graphics.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void run() {
        while (true) {
            repaint(); // 重新绘制画板

            if (rope.getCurrentState() == RopeState.RETRIEVE 
                && miner.getCurrentState() != MinerState.PULL) {  // 当绳索状态处于“收取”时
                miner.setCurrentState(MinerState.PULL);           // 将矿工的状态设置为“拉”
            }
            else if (rope.getCurrentState() == RopeState.SWING) { // 当绳索状态处于“摇摆”时
                miner.setCurrentState(MinerState.IDLE);           // 将矿工的状态设置为“静置”
            }

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
}
