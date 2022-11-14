package gamebody.scenes.body;

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
 * <p><b>游戏的所有活动都在这个窗口进行。</b>
 * <p>包括窗口、背景图、矿工、绳索、炸药、其他的游戏物品对象和UI文字的绘制
 * <p>以及菜单界面、商店界面、过关提示界面和失败界面的切换。
 *
 * @author Enos
 * @author JiajiaPig
 */

public class GameWindow extends JFrame implements Runnable, KeyListener {

    /**
     * 窗口默认固定宽度。
     */
    public static final int INIT_WIDTH = 960;

    /**
     * 窗口默认固定高度。540px(关卡背景高度) + 124px(陆地背景高度) + 30px(填充)
     */
    public static final int INIT_HEIGHT = 540 + 124 + 30; 

    /**
     * 更新一帧所需的时间（单位是微秒/millis）。
     */
    public static final int TIME_PER_FRAME = 80; 

    /**
     * 10种不同的关卡场景。
     */
    public static final int LEVEL_NUMBER = 10;

    /**
     * 当前的关卡数。开始时为1。
     */
    private static int level = 1;

    /**
     * 每一关的目标分数，随着关卡数的改变而改变。
     * <p>每一关的目标分数与关卡数呈现这样的关系：
     * <b><p>{@code target} = 105 + 545 * {@code level} + 135 * ({@code level} - 1) * ({@code level} - 2)
     */
    private static int target = 105 + 545 * level + 135 * (level - 1) * (level - 2);

     /**
     * 标识当前游戏是否在闯关界面。
     * <p>当{@code isInMainScene == false}时，游戏不在闯关界面，{@link#update}和{@link#repaint}方法不执行，
     * 不更新闯关界面中的游戏对象。
     */
    private boolean isInMainScene;

    /**
     * 用于标识游戏是否要进入下一关。
     * <p>当{@code isNextLevel == true}时，会调用{@link#levelUp}方法，显示下一关的目标分数界面后加载下一关游戏场景后
     * 将游戏界面切换至下一关的闯关界面。
     */
    private boolean isNextLevel;

    /**
     * 封装窗口的宽度和高度。
     */
    private Dimension dimension = new Dimension(INIT_WIDTH, INIT_HEIGHT);

    /**
     * 窗口图标。
     */
    private Image icon = new ImageIcon("resources/miner-dig-0.png").getImage();

    /**
     * 存放各个游戏界面的{@code JPanel}类型的容器，布局管理器会被设置为{@code CardLayout}，然后根据游戏过程
     * 设置不同的界面。
     * <p>存放的界面有{@link#menu}、{@link#gameScenePanel}、{@link#shop}、{@link#cutscene0}、
     * {@link#cutscene1}和{@link#cutscene2}。
     */
    private JPanel windowPanel = new JPanel();

    /**
     * {@link#windowPanel}的卡片布局管理器。
     */
    private CardLayout cardLayout = new CardLayout();

    /**
     * 游戏的菜单。
     */
    private Menu menu = new Menu(this);

    /**
     * 游戏闯关时的游戏对象所在的面板。
     * <p>绘制在这个面板的游戏对象有{@link#miner}、{@link#rope}和在{@link#gameobjects}中的游戏对象。
     */
    private JPanel gameScenePanel = new JPanel();

    /**
     * 商店。
     */
    private Shop shop = new Shop(this);

    /**
     * 未达到目标，游戏失败时的画面。
     */
    private Cutscene cutscene0 = new Cutscene(0);

    /**
     * 达到目标分数后，游戏显示的画面。
     */
    private Cutscene cutscene1 = new Cutscene(1);

    /**
     * 进入下一关前，游戏显示的目标分数画面。
     */
    private Cutscene cutscene2 = new Cutscene(2);

    /**
     * 用于双缓存渲染绘制游戏对象时的辅助画板。
     */
    private Image offScreenImage; 

    /**
     * 游戏的场景。
     */
    private Scene scene = new Scene(this);  

    /**
     * 矿工。在闯关界面才会更新和渲染。
     */
    private Miner miner = new Miner();  

    /**
     * 绳索。在闯关界面才会更新和渲染。
     */
    private Rope rope = new Rope(this); 

    /**
     * 炸弹。在有触发动作时才会实例化。
     */
    private Dynamite dynamite;

    /**
     * 炸弹的数量。
     */
    private int dynamiteCount;

    /**
     * 用于存放当前关卡所包含的游戏对象的集合。
     */
    private Vector<GameObject> gameobjects = scene.getGameObjects(0);

    /**
     * 在闯关界面时的倒计时器。
     */
    private Time time;

    /**
     * 游戏的UI界面。
     */
    private UI ui = new UI(this);

    /**
     * 窗口进行实时更新的线程。
     */
    private Thread gameWindowThread = new Thread(this); 
    
    public GameWindow() {
        // 开启窗口实时更新的线程
        gameWindowThread.start(); 
    }

    /**
     * 开启游戏窗口时的初始化。
     */
    public void launch() {
        // 添加不同的游戏画面到windowPanel容器中。
        windowPanel.setLayout(cardLayout);
        windowPanel.add("gameScenePanel", gameScenePanel);
        windowPanel.add("menu", menu);
        windowPanel.add("shop", shop);
        windowPanel.add("cutscene0", cutscene0);
        windowPanel.add("cutscene1", cutscene1);
        windowPanel.add("cutscene2", cutscene2);
        add(windowPanel);

        // JFrame窗口设置。
        setVisible(true);
        setSize(getDimension());
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("GoldMiner");
        setIconImage(icon);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 给窗口注册键盘事件监听器。
        addKeyListener(this); 
        startGame();
    }

    /**
     * 开始新的游戏。
     */
    public void startGame() {
        // 显示进入下一关的画面。
        windowPanel.setVisible(true);
        // 传入并显示目标分数值。
        cutscene2.setGoalScore(target);
        cardLayout.show(windowPanel, "cutscene2"); 
        cutscene2.getCutSceneSound2().play(1);
        // 画面显示2s，2s后加载新的游戏画面。
        delay(2000);

        // 加载游戏场景的物体。
        loadGameObjects(); 
        // 让绳索开始实时更新。
        rope.setStopSignal(false);
        cardLayout.show(windowPanel, "gameScenePanel");
        // 当前是主游戏界面。
        isInMainScene = true;
        // 时间重置。
        time = new Time(); 
    }

    /**
     * 根据关卡数{@code level}加载游戏物体。
     */
    private void loadGameObjects() {
        gameobjects = scene.getGameObjects((level - 1) % LEVEL_NUMBER);
    }
    
    /**
     * 延迟一定的时间。
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
     * 停止当前所有进行更新的对象的活动。
     */
    private void stopCurrentActivity() {
        // 停止声音
        miner.getDigSound().play(2);
        miner.getPullSound().play(2);
        // 停止绳索和矿工的动作
        rope.setStopSignal(true);
        miner.setCurrentState(MinerState.IDLE);   
    }

    /**
     * 实时更新游戏对象的状态。
     */
    private void update() {
        if (rope.getCurrentState() == RopeState.RETRIEVE
            && miner.getCurrentState() != MinerState.PULL) {     // 当绳索状态处于“收取”时
            miner.getPullSound().play(3);
            // 将矿工的状态设置为“拉”。
            miner.setCurrentState(MinerState.PULL);              
        }
        else if (rope.getCurrentState() == RopeState.SWING) {    // 当绳索状态处于“摇摆”时
            miner.getPullSound().play(2);
            // 将矿工的状态设置为“静置”。
            miner.setCurrentState(MinerState.IDLE);              
        }

        // 如果时间到了，跳转到判断是否过关。
        if (time.countDown() < 0) {
            settle();
        }
    }

    /**
     * 判断是否达到过关条件，如果达到了，就显示商店界面购买道具；如果没达到，就显示游戏失败界面。
     */
    private void settle () {
        // 不在游戏闯关界面，设置isInMainScene为false。
        isInMainScene = false;
        // 不在游戏闯关界面，停止正在实时更新的游戏对象的更新。
        stopCurrentActivity();                
        // 达到下一关的条件。
        if (rope.getOverallValue() >= target) {
            // 关卡数增加。
            level++;
            target = 105 + 545 * level + 135 * (level - 1) * (level - 2);//根据公式重置新的目标分数

            // 显示过关的界面。
            cardLayout.show(windowPanel, "cutscene1");
            cutscene1.getCutSceneSound1().play(1);
            // 过关界面显示2s。
            delay(2000);

            // 显示商店界面。
            shop.launchShop();
            cardLayout.show(windowPanel, "shop");
        }
        else {
            // 如果未达到目标，则显示游戏失败界面。
            cardLayout.show(windowPanel, "cutscene0");
            // 失败界面显示3s
            delay(3000);
            // 关卡数设置为1，需要重新开始。
            level = 1;
            // 重置目标分数。
            target = 105 + 545 * level + 135 * (level - 1) * (level - 2);
            // 重设第一关的游戏场景。
            scene.createScene(1);
            // 加载游戏物体。
            loadGameObjects();
            cardLayout.show(windowPanel, "menu");
        }
    }

    /**
     * 进入下一关。
     */
    private void levelUp() {
        // 进入下一关前，恢复上一个关卡的游戏场景。
        scene.createScene(level - 1); 
        // 显示下一关目标界面。
        cutscene2.setGoalScore(target);
        cardLayout.show(windowPanel, "cutscene2"); 
        cutscene2.getCutSceneSound2().play(1);
        // 判断是否购买完毕。
        if (shop.getIsBuyFinish()) {
            // 告诉rope那边商店购买已经结束了，可以生成商品属性效果了。
            rope.setIsShop(true); 
            // 将shop对象里的所有商品是否被购买的状态传递给rope对象，道具生效需要在rope对象里执行。
            rope.setProduct(shop.getProductStatus());
            // 把购买商品总共花费的钱扣除。
            rope.setOverallValue(rope.getOverallValue() - shop.getTotalMoney()); 
        }
        // 点击下一关后延迟2s。
        delay(2000);
        // 显示游戏界面。
        loadGameObjects();
        rope.setStopSignal(false);
        isInMainScene = true;
        isNextLevel = false;
        cardLayout.show(windowPanel, "gameScenePanel");
        time = new Time();
    }
    
    @Override
    public void paint(Graphics graphics) {
        if (isInMainScene) {
            // 每次进行绘制的时候，都重新创建一个辅助画板
            offScreenImage = createImage(INIT_WIDTH, INIT_HEIGHT); 
            // 将游戏元素都绘制在辅助画板上。
            Graphics graphics2 = offScreenImage.getGraphics();
            // 绘制游戏场景（游戏背景以及游戏物品对象）。
            for (GameObject object : gameobjects) {
                object.render(graphics2, gameScenePanel);
            }
            // 绘制矿工。
            miner.render(graphics2, gameScenePanel);
            // 绘制绳索。
            rope.render(graphics2, gameScenePanel);
            // 绘制UI。
            ui.render(graphics2, gameScenePanel);
            //如果dynamite对象不为空，则画出该对象。
            if (dynamite != null) {
                dynamite.render(graphics2, gameScenePanel);
            }
            // 如果炸药消失了，即炸开了，将dynamite对象置为空，重新设置rope的速度为正常速度，碰撞检测为false。
            if (dynamite != null && dynamite.isVanished()) {
                dynamite = null;
                rope.setRetrieveRate(Rope.INIT_RETRIEVE_RATE);
                rope.setColliding(false);
            }
            // 将辅助画板绘制在原本的画板上。
            graphics.drawImage(offScreenImage, 0, 0, gameScenePanel);
        }
    }

    @Override
    public void run() {
        while (true) {
            setFocusable(true);
            if (isInMainScene) {
                repaint(); 
                update();
            }
            if (isNextLevel) {
                levelUp();
            }
            delay(TIME_PER_FRAME);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {            // 当按下“↓”键时。
            if (rope.getCurrentState() == RopeState.SWING) { // 当绳索处于摆动状态的时候。
                miner.getDigSound().play(1);
                // 将绳索的状态设为“抓取”。
                rope.setCurrentState(RopeState.GRAB);  
                // 将矿工的状态设为“挖”。      
                miner.setCurrentState(MinerState.DIG);       
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP && rope.getCurrentState() == 
            RopeState.RETRIEVE && rope.isColliding())  // 当按下“↑”键时且rope目前的状态为抓取状态，且碰撞检测成功时。
        {
            // 如果炸药数量大于0，则会触发爆炸效果。
            if (dynamiteCount > 0) {
                dynamite = new Dynamite(this);
                // 将矿工的状态设置为扔炸弹。
                miner.setCurrentState(MinerState.THROW);
                // 炸药数量减少一个。
                dynamiteCount--;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {}

    @Override
    public void keyTyped(KeyEvent arg0) {}

    // getters and setters
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

    public boolean isNextLevel() {
        return isNextLevel;
    }

    public void setNextLevel(boolean isNextLevel) {
        this.isNextLevel = isNextLevel;
    }

    public void setDynamiteCount(int dynamiteCount) {
        this.dynamiteCount = dynamiteCount;
    }

    public int getDynamiteCount() {
        return dynamiteCount;
    }
}

