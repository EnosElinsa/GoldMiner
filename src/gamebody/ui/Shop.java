package gamebody.ui;

import gamebody.engine.ProductStatus;
import gamebody.scenes.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * <p>游戏商店类。
 * <p>游戏购买道具在这个类中设置，会根据目前所得的分数按照一定的比例生成价格，随机生成可被购买的商品
 * <p>商品可以获得效果加成，分别有炸药、力量饮料、四叶草、石头书、钻石抛光，且除了炸药以外的商品仅限于下一关有效，
 * 炸药只要不被使用可以一直保留到任意关卡。
 * 
 * <p>炸药：由玩家控制是否使用，当使用炸药时，可以将绳索正在抓取的物体以及周围的物体炸开，炸开以后矿工将绳索收回并且不加分
 * <p>能量饮料：获得能量加成，矿工拥有了能量饮料后拉取物体的速度变得更快了，矿工更猛更强更受人爱了；
 * <p>四叶草：拥有四叶草后，抓取到袋子的金钱值会更多；
 * <p>石头书：石头一直都是没有价值的，直到石头书的出现，让石头的价值变为原来的三倍；
 * <p>钻石抛光：钻石本来就是高价值的物体，有了钻石抛光，钻石将进一步被加强，价值更高。
 *
 * @author lll2034006613
 * @author Enos
 * @author JiajiaPig
 * @see GameWindow
 */

public class Shop extends JPanel {

    /**
     * 炸药、力量饮料、四叶草、石头书、钻石抛光是否刷出。
     */
    private boolean dynamite, drink, clover, book, polish;

    /**
     * 对应物品的价格。
     */
    private int dynamitePrice, drinkPrice, cloverPrice, bookPrice, polishPrice;

    /**
     * 分数。
     */
    private int score; 

    /**
     * 购买商品花费的总价钱。
     */
    private int totalMoney;

    /**
     * 商品购买状态。
     */
    private ProductStatus productStatus;

    /**
     * 标识是否完成购买。
     */
    private boolean isBuyFinished;

    /**
     * {@code GameWindow}游戏窗口主体的引用，用于获取游戏场景下的其他游戏物体。
     */
    GameWindow gameWindow;

    /**
     * 唯一构造方法。
     * @param gameWindow {@code GameWindow}游戏窗口主体的引用，用于获取游戏场景下的其他游戏物体。
     */
    public Shop(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setLayout(null);
    }

    /**
     * 显示商店界面。
     */
    public void launchShop() {
        score = gameWindow.getRope().getOverallValue();
        // 初始化道具。
        initializeProps();
        // 初始化道具点击事件。
        initializePropsBuy();
        // 初始化按钮。
        initializeButton();
        // 初始化静态背景。
        initializeBackground();
        // 初始化判断是否购买完毕的状态。
        initializeCheckState();
        // 每次打开商店要把所有物品的购买状态初始化为未购买（因为商品效果只维持一句游戏）。
        productStatus=new ProductStatus();
        // 每次打开商店，购买商品的总价钱要清零。
        totalMoney=0;
    }

    /**
     * 初始化选择状态。
     */
    private void initializeCheckState() {
        isBuyFinished =false;
    }

    /**
     * 初始化静态背景。
     */
    private void initializeBackground() {
        // 绘制文本内容。
        Font font = new Font("宋体", Font.BOLD, 20);
        JLabel jLabelTest2 = new JLabel("单击想要购买的物品.");
        jLabelTest2.setFont(font);
        jLabelTest2.setForeground(Color.black);
        jLabelTest2.setBounds(220, 135, 1000, 40);
        add(jLabelTest2);

        JLabel jLabelTest3 = new JLabel("当准备好继续游戏时,点击\"下一关卡\".");
        jLabelTest3.setFont(font);
        jLabelTest3.setForeground(Color.black);
        jLabelTest3.setBounds(220, 165, 1000, 40);
        add(jLabelTest3);

        // 绘制文本框。
        JLabel jLabelBox = new JLabel(new ImageIcon("resources\\shop\\seller-talk-banner.png"));
        jLabelBox.setBounds(190, 120, 700, 175);
        add(jLabelBox);

        // 绘制标题。
        JLabel jLabelTitle = new JLabel(new ImageIcon("resources\\shop\\gold-miner-text.png"));
        jLabelTitle.setBounds(150, 10, 700, 104);
        add(jLabelTitle);
        
        // 绘制售货员。
        JLabel jLabelSeller = new JLabel(new ImageIcon("resources\\shop\\seller.png"));
        jLabelSeller.setBounds(690, 231, 320, 291);
        add(jLabelSeller);

        // 绘制桌子。
        JLabel jLabelDesk = new JLabel(new ImageIcon("resources\\shop\\desk.png"));
        jLabelDesk.setBounds(-6, 451, 960, 209);
        add(jLabelDesk);

        // 绘制背景。
        JLabel jLabelBackground = new JLabel(new ImageIcon("resources\\shop\\shop-scene-background.png"));
        jLabelBackground.setBounds(-3, -20, GameWindow.INIT_WIDTH, GameWindow.INIT_HEIGHT);
        add(jLabelBackground);
    }

    /**
     * 初始化道具点击事件。
     */
    private void initializeProps() {
        // 随机生成两个以上物品。
        do {
            dynamite = new Random().nextBoolean();
            drink = new Random().nextBoolean();
            clover = new Random().nextBoolean();
            book = new Random().nextBoolean();
            polish = new Random().nextBoolean();
        } while ((dynamite ? 1 : 0) + (drink ? 1 : 0) + (clover ? 1 : 0) + (book ? 1 : 0) + (polish ? 1 : 0) < 2);

        // 随机生成价格。
        dynamitePrice = Math.abs(new Random().nextInt()) % score / 70 + 2;
        drinkPrice = Math.abs(new Random().nextInt()) % score / 24 + 50;
        cloverPrice = Math.abs(new Random().nextInt()) % score / 100 + 2;
        bookPrice = Math.abs(new Random().nextInt()) % score / 80 + 2;
        polishPrice = Math.abs(new Random().nextInt()) % score / 15 + 120;
    }

    /**
     * 初始化道具点击事件。
     */
    private void initializePropsBuy() {
        // 设置字体样式。
        Font font = new Font("宋体", Font.BOLD, 20);
        if (dynamite) {
            // 绘制物品描述。
            JLabel jLabelDescription = new JLabel("在使用钳子抓到一些东西时,扔出炸弹并炸开它");
            jLabelDescription.setFont(font);
            jLabelDescription.setBounds(70, 520, 1000, 20);
            add(jLabelDescription);
            jLabelDescription.setVisible(false);

            // 绘制物品价格。
            JLabel jLabelPrice = new JLabel("$" + dynamitePrice);
            jLabelPrice.setFont(font);
            jLabelPrice.setBounds(80, 465, 100, 20);
            add(jLabelPrice);

            // 绘制按钮。
            JButton jButtonNext = new JButton(new ImageIcon("resources\\shop\\dynamite.png"));
            jButtonNext.setBorderPainted(false);
            jButtonNext.setOpaque(false);
            jButtonNext.setFocusPainted(false);
            jButtonNext.setContentAreaFilled(false);
            jButtonNext.setBounds(80, 320, 56, 112);
            add(jButtonNext);

            // 设置点击事件。
            jButtonNext.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    // 设置炸弹状态为已购买。
                    productStatus.setDynamite(true);
                    // 把总价钱给加上。
                    totalMoney+=dynamitePrice;
                    jLabelPrice.setVisible(false);
                    jLabelDescription.setVisible(false);
                    jButtonNext.setVisible(false);
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

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
            // 绘制物品描述。
            JLabel jLabelDescription = new JLabel("能量饮料.矿工将在下一关卡中更快地卷起抓到的物品.饮料仅持续一个关卡.");
            jLabelDescription.setFont(font);
            jLabelDescription.setBounds(70, 520, 1000, 20);
            add(jLabelDescription);
            jLabelDescription.setVisible(false);

            // 绘制物品价格。
            JLabel jLabelPrice = new JLabel("$" + drinkPrice);
            jLabelPrice.setFont(font);
            jLabelPrice.setBounds(185, 465, 100, 20);
            add(jLabelPrice);

            // 绘制按钮。
            JButton jButtonNext = new JButton(new ImageIcon("resources\\shop\\strength-drink.png"));
            jButtonNext.setBorderPainted(false);
            jButtonNext.setOpaque(false);
            jButtonNext.setFocusPainted(false);
            jButtonNext.setContentAreaFilled(false);
            jButtonNext.setBounds(170, 320, 100, 109);
            add(jButtonNext);

            // 设置点击事件。
            jButtonNext.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    // 设置能量饮料状态为已购买。
                    productStatus.setDrink(true);
                    // 把总价钱给加上。
                    totalMoney += drinkPrice;
                    jLabelPrice.setVisible(false);
                    jLabelDescription.setVisible(false);
                    jButtonNext.setVisible(false);
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

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
            // 绘制物品描述。
            JLabel jLabelDescription = new JLabel("幸运四叶草.此物品会增加下一关卡抓住袋子获得更好东西的几率.仅在一个关卡内有效.");
            jLabelDescription.setFont(font);
            jLabelDescription.setBounds(70, 520, 1000, 20);
            add(jLabelDescription);
            jLabelDescription.setVisible(false);

            // 绘制物品价格。
            JLabel jLabelPrice = new JLabel("$" + cloverPrice);
            jLabelPrice.setFont(font);
            jLabelPrice.setBounds(315, 465, 100, 20);
            add(jLabelPrice);

            // 绘制按钮。
            JButton jButtonNext = new JButton(new ImageIcon("resources\\shop\\lucky-clover.png"));
            jButtonNext.setBorderPainted(false);
            jButtonNext.setOpaque(false);
            jButtonNext.setFocusPainted(false);
            jButtonNext.setContentAreaFilled(false);
            jButtonNext.setBounds(300, 320, 70, 124);
            add(jButtonNext);

            // 设置点击事件。
            jButtonNext.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    // 设置四叶草状态为已购买。
                    productStatus.setClover(true);
                    // 把总价钱给加上。
                    totalMoney += cloverPrice;
                    jLabelPrice.setVisible(false);
                    jLabelDescription.setVisible(false);
                    jButtonNext.setVisible(false);
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

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
            // 绘制物品描述。
            JLabel jLabelDescription = new JLabel("石头收藏家书籍.石头在下一关卡将会是平时价值的三倍.仅在一个关卡内有效.");
            jLabelDescription.setFont(font);
            jLabelDescription.setBounds(70, 520, 1000, 20);
            add(jLabelDescription);
            jLabelDescription.setVisible(false);
            
            // 绘制物品价格。
            JLabel jLabelPrice = new JLabel("$" + bookPrice);
            jLabelPrice.setFont(font);
            jLabelPrice.setBounds(433, 465, 100, 20);
            add(jLabelPrice);

            // 绘制按钮。
            JButton jButtonNext = new JButton(new ImageIcon("resources\\shop\\rock-collectors-book.png"));
            jButtonNext.setBorderPainted(false);
            jButtonNext.setOpaque(false);
            jButtonNext.setFocusPainted(false);
            jButtonNext.setContentAreaFilled(false);
            jButtonNext.setBounds(400, 320, 115, 108);
            add(jButtonNext);

            // 设置点击事件。
            jButtonNext.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    // 设置石头收藏家书籍状态为已购买.
                    productStatus.setBook(true);
                    // 把总价钱给加上。
                    totalMoney += bookPrice;
                    jLabelPrice.setVisible(false);
                    jLabelDescription.setVisible(false);
                    jButtonNext.setVisible(false);
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

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
            // 绘制物品描述。
            JLabel jLabelDescription = new JLabel("钻石抛光.在下一关卡中的钻石会更加值钱.仅在一个关卡内有效.");
            jLabelDescription.setFont(font);
            jLabelDescription.setBounds(70, 520, 1000, 20);
            add(jLabelDescription);
            jLabelDescription.setVisible(false);

            // 绘制物品价格。
            JLabel jLabelPrice = new JLabel("$" + polishPrice);
            jLabelPrice.setFont(font);
            jLabelPrice.setBounds(570, 465, 100, 20);
            add(jLabelPrice);

            // 绘制按钮。
            JButton jButtonNext = new JButton(new ImageIcon("resources\\shop\\diamond-polish.png"));
            jButtonNext.setBorderPainted(false);
            jButtonNext.setOpaque(false);
            jButtonNext.setFocusPainted(false);
            jButtonNext.setContentAreaFilled(false);
            jButtonNext.setBounds(545, 320, 100, 117);
            add(jButtonNext);

            // 设置点击事件。
            jButtonNext.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    // 设置钻石抛光状态为已购买。
                    productStatus.setPolish(true);
                    // 把总价钱给加上。
                    totalMoney+=polishPrice;
                    jLabelPrice.setVisible(false);
                    jLabelDescription.setVisible(false);
                    jButtonNext.setVisible(false);
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

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

    /**
     * 初始化按钮。
     */
    private void initializeButton() {
        // 绘制按钮文本。
        Font f = new Font("宋体", Font.BOLD, 20);
        JLabel jLabelTest1 = new JLabel("下一关卡");
        jLabelTest1.setFont(f);
        jLabelTest1.setForeground(Color.white);
        jLabelTest1.setBounds(811, 162, 100, 23);
        add(jLabelTest1);

        // 绘制按钮点击效果。
        JLabel jButtonNextPressed = new JLabel(new ImageIcon("resources\\shop\\next-btn-1.png"));
        jButtonNextPressed.setBounds(778, 141, 150, 61);
        jButtonNextPressed.setVisible(false);
        add(jButtonNextPressed);

        // 绘制按钮。
        JButton jButtonNext = new JButton(new ImageIcon("resources\\shop\\next-btn-0.png"));
        jButtonNext.setBorderPainted(false);
        jButtonNext.setOpaque(false);
        jButtonNext.setFocusPainted(false);
        jButtonNext.setContentAreaFilled(false);
        jButtonNext.setBounds(778, 142, 150, 61);
        add(jButtonNext);

        // 设置点击事件。
        jButtonNext.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameWindow.setNextLevel(true);
                isBuyFinished = true;
                removeAll();
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

    public boolean getIsBuyFinish() {
        return isBuyFinished;
    }

    public ProductStatus getProductStatus()
    {
        return productStatus;
    }

    public int getTotalMoney() {
        return totalMoney;
    }
}

