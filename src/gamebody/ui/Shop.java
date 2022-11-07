package gamebody.ui;

import javax.swing.*;

import gamebody.scenes.GameWindow;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Shop extends JPanel {

    private boolean dynamite, drink, clover, book, polish; //炸药、力量饮料、四叶草、石头书、钻石抛光是否刷出
    private int dynamitePrice, drinkPrice, cloverPrice, bookPrice, polishPrice; //对应物品的价格
    private int score; //分数

    GameWindow gameWindow;

    //构造方法
    public Shop(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void launchShop() {
        setLayout(null);
        score = gameWindow.getRope().getOverallValue();
        //初始化道具
        initializeProps();
        //初始化道具点击事件
        initializePropsBuy();
        //初始化按钮
        initializeButton();
        //初始化静态背景
        initializeBackground();
    }
    //初始化静态背景
    private void initializeBackground() {
        //绘制文本内容
        Font font = new Font("宋体", Font.BOLD, 20);
        JLabel jLabelTest2 = new JLabel("双击想要购买的物品.");
        jLabelTest2.setFont(font);
        jLabelTest2.setForeground(Color.black);
        jLabelTest2.setBounds(220, 135, 1000, 40);
        add(jLabelTest2);

        JLabel jLabelTest3 = new JLabel("当准备好继续游戏时,点击\"下一关卡\".");
        jLabelTest3.setFont(font);
        jLabelTest3.setForeground(Color.black);
        jLabelTest3.setBounds(220, 165, 1000, 40);
        add(jLabelTest3);

        //绘制文本框
        JLabel jLabelBox = new JLabel(new ImageIcon("resources\\shop\\seller-talk-banner.png"));
        jLabelBox.setBounds(190, 120, 700, 175);
        add(jLabelBox);

        //绘制标题
        JLabel jLabelTitle = new JLabel(new ImageIcon("resources\\shop\\gold-miner-text.png"));
        jLabelTitle.setBounds(150, 10, 700, 104);
        add(jLabelTitle);
        
        //绘制售货员
        JLabel jLabelSeller = new JLabel(new ImageIcon("resources\\shop\\seller.png"));
        jLabelSeller.setBounds(690, 231, 320, 291);
        add(jLabelSeller);

        //绘制桌子
        JLabel jLabelDesk = new JLabel(new ImageIcon("resources\\shop\\desk.png"));
        jLabelDesk.setBounds(-6, 451, 960, 209);
        add(jLabelDesk);

        //绘制背景
        JLabel jLabelBackground = new JLabel(new ImageIcon("resources\\shop\\shop-scene-background.png"));
        jLabelBackground.setBounds(-3, -20, GameWindow.INIT_WIDTH, GameWindow.INIT_HEIGHT);
        add(jLabelBackground);
    }

    //初始化道具
    private void initializeProps() {
        //随机生成两个以上物品
        while ((dynamite ? 1 : 0) + (drink ? 1 : 0) + (clover ? 1 : 0) + (book ? 1 : 0) + (polish ? 1 : 0) < 2) {
            dynamite = new Random().nextBoolean();
            drink = new Random().nextBoolean();
            clover = new Random().nextBoolean();
            book = new Random().nextBoolean();
            polish = new Random().nextBoolean();
        }

        //随机生成价格
        dynamitePrice = Math.abs(new Random().nextInt()) % score / 70 + 2;
        drinkPrice = Math.abs(new Random().nextInt()) % score / 24 + 50;
        cloverPrice = Math.abs(new Random().nextInt()) % score / 100 + 2;
        bookPrice = Math.abs(new Random().nextInt()) % score / 80 + 2;
        polishPrice = Math.abs(new Random().nextInt()) % score / 15 + 120;
    }

    //初始化道具点击事件
    private void initializePropsBuy() {
        //设置字体样式
        Font font = new Font("宋体", Font.BOLD, 20);
        if (dynamite) {
            //绘制物品描述
            JLabel jLabelDescription = new JLabel("在使用钳子抓到一些东西时,扔出炸弹并炸开它");
            jLabelDescription.setFont(font);
            jLabelDescription.setBounds(70, 520, 1000, 20);
            add(jLabelDescription);
            jLabelDescription.setVisible(false);

            //绘制物品价格
            JLabel jLabelPrice = new JLabel("$" + dynamitePrice);
            jLabelPrice.setFont(font);
            jLabelPrice.setBounds(80, 465, 100, 20);
            add(jLabelPrice);

            //绘制按钮
            JButton jButtonNext = new JButton(new ImageIcon("resources\\shop\\dynamite.png"));
            jButtonNext.setBorderPainted(false);
            jButtonNext.setOpaque(false);
            jButtonNext.setFocusPainted(false);
            jButtonNext.setContentAreaFilled(false);
            jButtonNext.setBounds(80, 320, 56, 112);
            add(jButtonNext);

            //设置点击事件
            jButtonNext.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    jLabelPrice.setVisible(false);
                    jLabelDescription.setVisible(false);
                    jButtonNext.setVisible(false);
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    jLabelDescription.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jLabelDescription.setVisible(false);
                }
            });
        }

        if (drink) {
            //绘制物品描述
            JLabel jLabelDescription = new JLabel("能量饮料.矿工将在下一关卡中更快地卷起抓到的物品.饮料仅持续一个关卡.");
            jLabelDescription.setFont(font);
            jLabelDescription.setBounds(70, 520, 1000, 20);
            add(jLabelDescription);
            jLabelDescription.setVisible(false);

            //绘制物品价格
            JLabel jLabelPrice = new JLabel("$" + drinkPrice);
            jLabelPrice.setFont(font);
            jLabelPrice.setBounds(185, 465, 100, 20);
            add(jLabelPrice);

            //绘制按钮
            JButton jButtonNext = new JButton(new ImageIcon("resources\\shop\\strength-drink.png"));
            jButtonNext.setBorderPainted(false);
            jButtonNext.setOpaque(false);
            jButtonNext.setFocusPainted(false);
            jButtonNext.setContentAreaFilled(false);
            jButtonNext.setBounds(170, 320, 100, 109);
            add(jButtonNext);

            //设置点击事件
            jButtonNext.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    jLabelPrice.setVisible(false);
                    jLabelDescription.setVisible(false);
                    jButtonNext.setVisible(false);
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    jLabelDescription.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jLabelDescription.setVisible(false);
                }
            });
        }

        if (clover) {
            //绘制物品描述
            JLabel jLabelDescription = new JLabel("幸运四叶草.此物品会增加下一关卡抓住袋子获得更好东西的几率.仅在一个关卡内有效.");
            jLabelDescription.setFont(font);
            jLabelDescription.setBounds(70, 520, 1000, 20);
            add(jLabelDescription);
            jLabelDescription.setVisible(false);

            //绘制物品价格
            JLabel jLabelPrice = new JLabel("$" + cloverPrice);
            jLabelPrice.setFont(font);
            jLabelPrice.setBounds(315, 465, 100, 20);
            add(jLabelPrice);

            //绘制按钮
            JButton jButtonNext = new JButton(new ImageIcon("resources\\shop\\lucky-clover.png"));
            jButtonNext.setBorderPainted(false);
            jButtonNext.setOpaque(false);
            jButtonNext.setFocusPainted(false);
            jButtonNext.setContentAreaFilled(false);
            jButtonNext.setBounds(300, 320, 70, 124);
            add(jButtonNext);

            //设置点击事件
            jButtonNext.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    jLabelPrice.setVisible(false);
                    jLabelDescription.setVisible(false);
                    jButtonNext.setVisible(false);
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    jLabelDescription.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jLabelDescription.setVisible(false);
                }
            });
        }

        if (book) {
            //绘制物品描述
            JLabel jLabelDescription = new JLabel("石头收藏家书籍.石头在下一关卡将会是平时价值的三倍.仅在一个关卡内有效.");
            jLabelDescription.setFont(font);
            jLabelDescription.setBounds(70, 520, 1000, 20);
            add(jLabelDescription);
            jLabelDescription.setVisible(false);
            
            //绘制物品价格
            JLabel jLabelPrice = new JLabel("$" + bookPrice);
            jLabelPrice.setFont(font);
            jLabelPrice.setBounds(433, 465, 100, 20);
            add(jLabelPrice);

            //绘制按钮
            JButton jButtonNext = new JButton(new ImageIcon("resources\\shop\\rock-collectors-book.png"));
            jButtonNext.setBorderPainted(false);
            jButtonNext.setOpaque(false);
            jButtonNext.setFocusPainted(false);
            jButtonNext.setContentAreaFilled(false);
            jButtonNext.setBounds(400, 320, 115, 108);
            add(jButtonNext);

            //设置点击事件
            jButtonNext.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    jLabelPrice.setVisible(false);
                    jLabelDescription.setVisible(false);
                    jButtonNext.setVisible(false);
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    jLabelDescription.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jLabelDescription.setVisible(false);
                }
            });
        }

        if (polish) {
            //绘制物品描述
            JLabel jLabelDescription = new JLabel("钻石抛光.在下一关卡中的钻石会更加值钱.仅在一个关卡内有效.");
            jLabelDescription.setFont(font);
            jLabelDescription.setBounds(70, 520, 1000, 20);
            add(jLabelDescription);
            jLabelDescription.setVisible(false);

            //绘制物品价格
            JLabel jLabelPrice = new JLabel("$" + polishPrice);
            jLabelPrice.setFont(font);
            jLabelPrice.setBounds(570, 465, 100, 20);
            add(jLabelPrice);

            //绘制按钮
            JButton jButtonNext = new JButton(new ImageIcon("resources\\shop\\diamond-polish.png"));
            jButtonNext.setBorderPainted(false);
            jButtonNext.setOpaque(false);
            jButtonNext.setFocusPainted(false);
            jButtonNext.setContentAreaFilled(false);
            jButtonNext.setBounds(545, 320, 100, 117);
            add(jButtonNext);

            //设置点击事件
            jButtonNext.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    jLabelPrice.setVisible(false);
                    jLabelDescription.setVisible(false);
                    jButtonNext.setVisible(false);
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    jLabelDescription.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jLabelDescription.setVisible(false);
                }
            });
        }
    }

    //初始化按钮
    private void initializeButton() {
        //绘制按钮文本
        Font f = new Font("宋体", Font.BOLD, 20);
        JLabel jLabelTest1 = new JLabel("下一关卡");
        jLabelTest1.setFont(f);
        jLabelTest1.setForeground(Color.white);
        jLabelTest1.setBounds(811, 162, 100, 23);
        add(jLabelTest1);

        //绘制按钮点击效果
        JLabel jButtonNextPressed = new JLabel(new ImageIcon("resources\\shop\\next-btn-1.png"));
        jButtonNextPressed.setBounds(778, 141, 150, 61);
        jButtonNextPressed.setVisible(false);
        add(jButtonNextPressed);

        //绘制按钮
        JButton jButtonNext = new JButton(new ImageIcon("resources\\shop\\next-btn-0.png"));
        jButtonNext.setBorderPainted(false);
        jButtonNext.setOpaque(false);
        jButtonNext.setFocusPainted(false);
        jButtonNext.setContentAreaFilled(false);
        jButtonNext.setBounds(778, 142, 150, 61);
        add(jButtonNext);

        //设置点击事件
        jButtonNext.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameWindow.setNextLevelSignal(true);
                //----------------------------------------------------------------------->鼠标点击“下一关卡”按钮，这里书写跳转事件
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

