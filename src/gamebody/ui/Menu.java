package gamebody.ui;

import gamebody.scenes.GameWindow;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JPanel {

    private GameWindow gameWindow;

    //构造方法
    public Menu(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setLayout(null);
        launchMenu();
    }

    public void launchMenu() {
        //初始化按钮
        initializeButton();
        //初始化静态背景
        initializeBackground();
        setVisible(true);
    }

    //初始化静态背景
    private void initializeBackground() {

        //绘制标题
        JLabel jLabelTitle = new JLabel(new ImageIcon("resources\\menu\\gold-miner-text.png"));
        jLabelTitle.setBounds(40, 10, 780, 116);
        add(jLabelTitle) ;    // 将组件件入到面板之中

        //绘制背景
        JLabel jLabelBackground = new JLabel(new ImageIcon("resources\\menu\\menu-background111.png"));
        jLabelBackground.setBounds(0,0,GameWindow.INIT_WIDTH, GameWindow.INIT_HEIGHT+50 );
        add(jLabelBackground);
    }


    //初始化按钮
    private void initializeButton() {

        //绘制矿工
        JLabel jLabelMiner = new JLabel(new ImageIcon("resources\\menu\\miner-menu.png"));
        jLabelMiner.setBounds(550, 231, 400, 404);
        add(jLabelMiner);

        //绘制提示
        JLabel jLabelTips = new JLabel(new ImageIcon("resources\\menu\\tips-menu.png"));
        jLabelTips.setBounds(0, 480, 350, 153);
        add(jLabelTips);

        //绘制按钮点击效果
        JLabel jButtonNextPressed = new JLabel(new ImageIcon("resources\\menu\\gold-menu-1.png"));
        jButtonNextPressed.setBounds(260, 150, 320, 291);
        jButtonNextPressed.setVisible(false);
        add(jButtonNextPressed);

        //绘制按钮图片
        JLabel jLabelPrepare = new JLabel(new ImageIcon("resources\\menu\\gold-menu-0.png"));
        jLabelPrepare.setBounds(260, 150, 320, 291);
        add(jLabelPrepare);

        //绘制按钮闪光
        JLabel jLabelSpark = new JLabel(new ImageIcon("resources\\menu\\menu-spark.png"));
        jLabelSpark.setBounds(137, 10, 580,578);
        add(jLabelSpark);

        //绘制按钮
        JButton jButtonNext = new JButton(new ImageIcon());
        jButtonNext.setBorderPainted(false);
        jButtonNext.setOpaque(false);
        jButtonNext.setFocusPainted(false);
        jButtonNext.setContentAreaFilled(false);
        jButtonNext.setBounds(300, 210, 320, 291);
        add(jButtonNext);

        //设置点击事件
        jButtonNext.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameWindow.startGame();
                //----------------------------------------------------------------------->鼠标点击“开始”按钮，这里书写跳转事件
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jButtonNextPressed.setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                jButtonNextPressed.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }



}


