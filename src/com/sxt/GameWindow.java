package com.sxt;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private Dimension dimension;
    public static final int INIT_WIDTH = 960; // 默认窗口宽度960px
    public static final int INIT_HEIGHT = 540 + 124 + 30; // 默认窗口高度540px(关卡背景高度)+124px(陆地背景高度)+30px(填充)
    private Image icon = new ImageIcon("resources/miner-dig-0.png").getImage(); // 窗口图标
    private Background background;
    private Image offScreenImage;
    private Miner miner;

    public GameWindow() {
        dimension = new Dimension(INIT_WIDTH, INIT_HEIGHT);
        background = new Background();
        miner = new Miner();
        miner.setCurrentState(MinerState.PULL);
    }

    @Override
    public void paint(Graphics graphics) {
        offScreenImage = this.createImage(INIT_WIDTH, INIT_HEIGHT);
        Graphics graphics2 = offScreenImage.getGraphics();
        background.drawSelf(graphics2);
        miner.setGraphics(graphics2);
        new Thread(miner).start();
        graphics.drawImage(offScreenImage, 0, 0, null);
    }

    public void launch() {
        this.setVisible(true);
        this.setSize(getDimension());
        this.setResizable(false); // 设置窗口大小不能缩放
        this.setLocationRelativeTo(null);
        this.setTitle("GoldMiner");
        this.setIconImage(icon);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(int height, int width) {
        dimension.height = height;
        dimension.width = width;
    }
}
