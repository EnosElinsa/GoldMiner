package gamebody.scenes.characters;

import gamebody.engine.*;
import gamebody.scenes.body.GameWindow;

import javax.swing.*;
import java.awt.*;

/**
 * <p>游戏背景下的{@code Rope}绳索。
 * <p>绳索根据{@code RopeState}状态来进行伸缩和摇摆变化，抓取物体。
 * @author Enos
 * @author JiajiaPig
 * @see RopeState
 * @see Hook
 * @see Miner
 * @see GameWindow
 * @see GameObject
 */
public class Rope extends GameObject {

    /**
     * 绳索的固定起点坐标横坐标。
     */
    public static final int START_X = GameWindow.INIT_WIDTH / 2 - 3;

    /**
     * 绳索的固定起点坐标纵坐标。
     */
    public static final int START_Y = 121; 

    /**
     * 绳索能够伸长的的最大长度。
     */
    public static final int MAX_LENGTH = 650;

    /**
     * 伸缩的最短长度，即初始状态下的长度。
     */
    public static final int MIN_LENGTH = 20;

    /**
     * 绳索在伸长时的固定速度。
     */
    public static final int GRAB_RATE = 28;

    /**
     * 绳子在拉取缩短时的初始速度（空载时）。
     */
    public static final int INIT_RETRIEVE_RATE = 60;

    /**
     * 绳索的终点坐标横坐标。   
     */
    private int endX;                                  
    
    /**
     * 绳索的终点坐标纵坐标  。
     */
    private int endY;                   

    /**
     * 绳索的长度，初始时为{@code MIN_LENGTH}最短长度。
     */
    private int length = MIN_LENGTH;

    /**
     * 绳索在摇摆（视为简谐运动）时的时间自变量{@code t}。
     */
    private double timer;

    /**
     * 绳索在拉取缩短时的速度。
     */
    private int retrieveRate = INIT_RETRIEVE_RATE;

    /**
     * 绳索一次抓取成功的物体的价值
     */
    private int grabValue;

    /**
     * 在一次游戏中，由绳索所抓取的所有物体的总价值。
     */
    private int overallValue = 0;

    /**
     * 标记被绳索抓取的物体是否收回成功
     */
    private boolean isRetrieved = false; 

    /**
     * 标记是否抓取物体成功
     */
    private boolean isSuccessed = true;

    /**
     * 标记是否进行更新的信号。（在游戏过场中，绳索应该停止动作）
     */
    private boolean stopSignal = false;

    /**
     * 标记是否商店购买结束
     */
    private boolean isShoppingFinished;

    /**
     * 标记钻石增强，袋子增强，石头增强，力量增强，是否有炸弹
     */
    private boolean diamondPro, treasureBagPro, stonePro, strengthPro, isBoom; 

    /**
     * 与绳索相连的{@code Hook}钩子的引用。
     */
    private Hook hook = new Hook(this);

    /**
     * 由于绳索游戏对象需要实时进行更新，需要有自己的更新状态的线程。
     */
    private Thread ropeThread = new Thread(this);

    /**
     * 绳索的{@code RopeState}状态，初始为{@code RopeState.SWING}摇摆。
     */
    private RopeState currentState = RopeState.SWING;

    /**
     * {@code GameWindow}游戏窗口主体的引用，用于获取游戏场景下的其他游戏物体。
     */
    private GameWindow gameWindow;

    /**
     * 抓取到高价值物体的音效
     */
    private Audio highSound = new Audio("sound/sound_wav/high-value.wav");

    /**
     * 抓取到普通价值物体的音效
     */
    private Audio normalSound = new Audio("sound/sound_wav/normal-value.wav");

    /**
     * 抓取到低价值物体的音效
     */
    private Audio lowSound = new Audio("sound/sound_wav/low-value.wav"); 

    /**
     * 抓取到特殊物体的音效
     */
    private Audio specialSound = new Audio("sound/sound_wav/score3.wav");

    /**
     * 绳索游戏对象的唯一构造方法
     * @param gameWindow 游戏窗口主体的引用
     */
    public Rope(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        // 开启绳索的更新线程
        ropeThread.start();
    }

    @Override
    public void render(Graphics graphics, JPanel panel) {
        Graphics2D graphics2d = (Graphics2D) graphics;

        // 设置绳索的粗细
        BasicStroke stokeLine = new BasicStroke(2.5f);
        graphics2d.setStroke(stokeLine); 
        // 设置绳索的颜色
        Color colorOfRope = new Color(49, 51, 64);
        graphics2d.setColor(colorOfRope);
        // 绘制绳索
        graphics2d.drawLine(START_X, START_Y, endX, endY);
        // 绘制与绳索相连的钩子
        hook.render(graphics2d, panel);
    }

    @Override
    protected void update() {
        // 绳索需要停止动作
        if (stopSignal) {
            collidingObject = null;
            isColliding = false;
            currentState = RopeState.SWING;
            length = MIN_LENGTH;
            return;
        }

        // 如果购买了炸药，炸药数量要增加
        if(isBoom && isShoppingFinished) {
            isShoppingFinished = false;
            gameWindow.setDynamiteCount(gameWindow.getDynamiteCount()+1);
        }
        
        switch (currentState) {
            case SWING:
                // 用简谐运动方程近似模拟钩子的单摆运动
                angle = 1.3 * Math.cos(timer); 

                timer += (double)GameWindow.TIME_PER_FRAME / 600;
                retrieveRate = INIT_RETRIEVE_RATE;
                isSuccessed=true;
                break;
            case GRAB:
                if (length <= MAX_LENGTH) {
                    length += GRAB_RATE;
                }
                else {
                    currentState = RopeState.RETRIEVE;
                }
                break;
            case RETRIEVE:
                if (length >= MIN_LENGTH) {
                    // 这里判断一下最后一次减去retrievRate如果比最小长度都要小的话，说明减过头了，这里直接让length等于最小长度，可以避免拉回时的回弹效果
                    if (length - retrieveRate < MIN_LENGTH) {
                        length = MIN_LENGTH;
                        currentState = RopeState.SWING;
                    } else {
                        length -= retrieveRate;
                    }
                } else {
                    length = MIN_LENGTH;
                    currentState = RopeState.SWING;
                }
                break;
        }

        // 更新绳子末端的位置
        endX = (int)(START_X + length * Math.sin(angle));
        endY = (int)(START_Y + length * Math.cos(angle));
        rigidbody = new Rigidbody(endX, endY, 5, 5);
        // 更新钩子的位置
        hook.setX(endX);
        hook.setY(endY);
        hook.setAngle(-1 * angle);

        if (isColliding == false && currentState == RopeState.GRAB 
            && (collidingObject = detectCollision()) != null) 
        {
            // 检测到碰撞
            isColliding = true;
            collidingObject.setOnHook(true);
            currentState = RopeState.RETRIEVE;
            // 如果购买了能量饮料，则力量变强
            if (strengthPro) {
                retrieveRate = (int)((retrieveRate/collidingObject.getMass()) + 20);
            }
            // 如果没有购买能量饮料，则正常拉取
            else {
                retrieveRate /= collidingObject.getMass();
            }

        }
        // 碰撞成功，即抓取到物体
        if (isColliding && collidingObject != null) {
            grabValue = collidingObject.getValue();
            if (isSuccessed) {
                isSuccessed = false;
                // 抓取到高价值的物体
                if (collidingObject.getObjectValueLevel() == ObjectValueLevel.HIGH) {
                    highSound.play(1);
                }
                // 抓取到普通价值的物体
                if (collidingObject.getObjectValueLevel() == ObjectValueLevel.NORMAL) {
                    normalSound.play(1);
                }
                // 抓取到低价值的物体
                if (collidingObject.getObjectValueLevel() == ObjectValueLevel.LOW) {
                    lowSound.play(1);
                }
                // 抓取到特殊的物体
                if (collidingObject.getObjectValueLevel() == ObjectValueLevel.SPECIAL) {
                    specialSound.play(1);
                }
            }

            // 更新被抓取到的物体的位置和角度
            collidingObject.setX(endX);
            collidingObject.setY(endY + collidingObject.getHeight() / 2 - 3 + 6);
            collidingObject.setAngle(-1 * angle);
            // 如果现在状态是摆动状态，抓取返回，加分
            if (currentState == RopeState.SWING) {
                // 如果购买了钻石抛光并且抓到的是钻石，则钻石价值由600变为900
                if (diamondPro && collidingObject.getName() == ItemName.DIAMOND) {
                    grabValue = collidingObject.getValue() + 300;
                }
                // 如果购买了四叶草并且抓到的是袋子，则袋子价值增加500
                else if (treasureBagPro && collidingObject.getName() == ItemName.TREASUREBAG) {
                    grabValue = collidingObject.getValue() + 500;
                }
                // 如果购买了石头收藏家书籍并且抓到的是石头，则石头的价值变为原来的三倍
                else if (stonePro==true&&collidingObject.getName()== ItemName.STONE) {
                    grabValue = collidingObject.getValue()*3;
                }
                else {
                    grabValue = collidingObject.getValue();
                }
                isColliding = false;

                gameWindow.delay(GameWindow.TIME_PER_FRAME * 9);
                // 抓取成功，物体消失
                collidingObject.vanish();
                // 收回成功
                isRetrieved = true;
                isSuccessed = true;
                overallValue += grabValue;
            }
        }
    }

    /**
     * 如果有检测到碰撞，则返回与其碰撞的物体的引用，否则返回{@code null}。
     */
    public GameObject detectCollision() {
        for (GameObject object : gameWindow.getGameobjects()) {
            if (rigidbody.hasCollisionWith(object.getRigidbody())) {
                object.setColliding(true);
                return object;
            }
        }
        return null;
    }

    /**
     * 设置商品的特性
     */
    public void setProduct(ProductStatus productStatus) {
        diamondPro = productStatus.getIsPolish();
        treasureBagPro = productStatus.getIsClover();
        stonePro = productStatus.getIsBook();
        strengthPro = productStatus.getIsDrink();
        isBoom = productStatus.getIsDynamite();
    }

    // getters and setters
    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public RopeState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(RopeState currentState) {
        this.currentState = currentState;
    }

    public int getRetrieveRate() {
        return retrieveRate;
    }

    public void setRetrieveRate(int retrieveRate) {
        this.retrieveRate = retrieveRate;
    }

    public Rigidbody getRigidbody() {
        return rigidbody;
    }

    public int getOverallValue() {
        return overallValue;
    }
    
    public boolean isRetrieved() {
        return isRetrieved;
    }

    public void setRetrieved(boolean isRetrieved) {
        this.isRetrieved = isRetrieved;
    }

    public int getGrabValue() {
        return grabValue;
    }

    public void setStopSignal(boolean stopSignal) {
        this.stopSignal = stopSignal;
    }

    public void setOverallValue(int overallValue) {
        this.overallValue = overallValue;
    }

    public void setIsSuccessed(boolean IsSuccessed) {
        isSuccessed=IsSuccessed;
    }

    public void setIsShop(boolean shop) {
        isShoppingFinished = shop;
    }
}
