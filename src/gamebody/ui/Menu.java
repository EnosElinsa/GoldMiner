package gamebody.ui;

import javax.swing.*;

import gamebody.body.scene.GameWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * <p>游戏菜单类。
 * <p>游戏的开始界面
 *
 * @author zzleaning
 * @author Enos
 * @see Cutscene
 * @see Shop
 */

public class Menu extends JPanel {

    private GameWindow gameWindow;

    /**
     * 唯一的构造方法。
     * @param gameWindow {@code GameWindow}游戏窗口主体的引用，用于改变游戏窗口的状态来进行界面切换。
     */
    public Menu(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setLayout(null);
        launchMenu();
    }

    /**
     * 初始化方法。
     */
    public void launchMenu() {
        setVisible(true);
        // 初始化按钮。
        initializeButton();
        // 初始化静态背景。
        initializeBackground();
    }

    /**
     * 初始化静态背景。
     */
    private void initializeBackground() {
        // 绘制背景。
        JLabel jLabelBackground = new JLabel(new ImageIcon("resources\\menu\\menu-background111.png"));
        jLabelBackground.setBounds(0,0,GameWindow.INIT_WIDTH, GameWindow.INIT_HEIGHT+50 );
        add(jLabelBackground);
    }

    /**
     * 初始化按钮。
     */
    private void initializeButton() {

        // 绘制标题。
        JLabel jLabelTitle = new JLabel(new ImageIcon("resources\\menu\\gold-miner-text.png"));
        jLabelTitle.setBounds(40, 10, 780, 116);
        // 将组件件入到面板之中。
        add(jLabelTitle) ;    

        // 绘制矿工。
        JLabel jLabelMiner = new JLabel(new ImageIcon("resources\\menu\\miner-menu.png"));
        jLabelMiner.setBounds(550, 231, 400, 404);
        add(jLabelMiner);

        // 绘制提示。
        JLabel jLabelTips = new JLabel(new ImageIcon("resources\\menu\\tips-menu.png"));
        jLabelTips.setBounds(0, 480, 350, 153);
        add(jLabelTips);

        // 绘制按钮点击效果。
        JLabel jButtonNextPressed = new JLabel(new ImageIcon("resources\\menu\\gold-menu-1.png"));
        jButtonNextPressed.setBounds(260, 150, 320, 291);
        jButtonNextPressed.setVisible(false);
        add(jButtonNextPressed);

        // 绘制按钮图片。
        JLabel jLabelPrepare = new JLabel(new ImageIcon("resources\\menu\\gold-menu-0.png"));
        jLabelPrepare.setBounds(260, 150, 320, 291);
        add(jLabelPrepare);

        // 绘制按钮闪光。
        JLabel jLabelSpark = new JLabel(new ImageIcon("resources\\menu\\menu-spark.png"));
        jLabelSpark.setBounds(137, 10, 580,578);
        add(jLabelSpark);

        // 绘制按钮。
        JButton jButtonNext = new JButton(new ImageIcon());
        jButtonNext.setBorderPainted(false);
        jButtonNext.setOpaque(false);
        jButtonNext.setFocusPainted(false);
        jButtonNext.setContentAreaFilled(false);
        jButtonNext.setBounds(300, 210, 320, 291);
        add(jButtonNext);

        // 设置点击事件。
        jButtonNext.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 鼠标有点击，则游戏开始
                gameWindow.startGame();
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
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
}


