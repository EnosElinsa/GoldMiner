package gamebody.scenes.characters;

import gamebody.engine.*;
import gamebody.scenes.GameWindow;
import gamebody.scenes.ObjectValueLevel;

import javax.swing.*;
import java.awt.*;

public class Rope extends GameObject {

    public static final int START_X = GameWindow.INIT_WIDTH / 2 - 3; // 绳索的起点坐标横坐标
    public static final int START_Y = 121;                           // 绳索的起点坐标纵坐标
    private int endX;                                   // 绳索的终点坐标横坐标                   
    private int endY;                                   // 绳索的终点坐标纵坐标  
    private int length = MIN_LENGTH;
    private double timer;
    private int retrieveRate = INIT_RETRIEVE_RATE;
    private int overallValue=0;

    private Hook hook = new Hook(this);
    private Thread ropeThread = new Thread(this);
    private RopeState currentState = RopeState.SWING;
    private GameWindow gameWindow;

    public static final int MAX_LENGTH = 650;
    public static final int MIN_LENGTH = 20;
    public static final int GRAB_RATE = 28;
    public static final int INIT_RETRIEVE_RATE = 60;

    private boolean isRetrieved = false;   // 判断物体是否收回成功
    private boolean isSuccessed = true;    // 标记是否抓取物体成功
    private boolean stopSignal = false;    // 是否进行更新的信号
    private int grabValue;
    //标记钻石增强，袋子增强，石头增强，力量增强，是否有炸弹
    private boolean diamondPro, treasureBagPro, stonePro, strengthPro, isBoom; 

    private Audio highSound = new Audio("sound/sound_wav/high-value.wav"); // 抓取到高价值物体的音效
    private Audio normalSound = new Audio("sound/sound_wav/normal-value.wav"); // 抓取到普通价值物体的音效
    private Audio lowSound = new Audio("sound/sound_wav/low-value.wav"); // 抓取到低价值物体的音效
    private Audio specialSound = new Audio("sound/sound_wav/score3.wav");

    public Rope(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        ropeThread.start();
    }

    @Override
    public void render(Graphics graphics, JPanel panel) {
        Graphics2D graphics2d = (Graphics2D) graphics;

        BasicStroke stokeLine = new BasicStroke(1.6f);
        graphics2d.setStroke(stokeLine); // 设置线的粗细
        Color colorOfRope = new Color(49, 51, 64);

        graphics2d.setColor(colorOfRope);
        graphics2d.drawLine(START_X, START_Y, endX, endY);
        hook.render(graphics2d, panel);
    }

    @Override
    protected void update() {
        if (stopSignal) {
            collidingObject = null;
            isColliding = false;
            currentState = RopeState.SWING;
            length = MIN_LENGTH;
            return;
        }
        
        switch (currentState) {
            case SWING:
                angle = 1.3 * Math.cos(timer); // 用简谐运动方程近似模拟钩子的单摆运动
                timer += (double)GameWindow.TIME_PER_FRAME / 600;
                retrieveRate = INIT_RETRIEVE_RATE;
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
                    //这里判断一下最后一次减去retrievRate如果比最小长度都要小的话，说明减过头了，这里直接让length等于最小长度，可以避免拉回时的回弹效果
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

        endX = (int)(START_X + length * Math.sin(angle));
        endY = (int)(START_Y + length * Math.cos(angle));
        hook.setX(endX);
        hook.setY(endY);
        hook.setAngle(-1 * angle);
        rigidbody = new Rigidbody(endX, endY, 5, 5);

        if (isColliding == false && currentState == RopeState.GRAB && (collidingObject = detectCollision()) != null) {
            isColliding = true;
            collidingObject.setOnHook(true);
            currentState = RopeState.RETRIEVE;
            //如果购买了能量饮料，则力量变强
            if (strengthPro) {
                retrieveRate = (int)((retrieveRate/collidingObject.getMass()) + 20);
            }
            //如果没有购买能量饮料，则正常拉取
            else {
                retrieveRate /= collidingObject.getMass();
            }

        }
        //碰撞成功，即抓取到物体
        if (isColliding && collidingObject != null) {
            grabValue = collidingObject.getValue();
            if (isSuccessed) {
                isSuccessed = false;
                //抓取到高价值的物体
                if (collidingObject.getObjectValueLevel() == ObjectValueLevel.HIGH) {
                    highSound.musicMain(1);
                }
                //抓取到普通价值的物体
                if (collidingObject.getObjectValueLevel() == ObjectValueLevel.NORMAL) {
                    normalSound.musicMain(1);
                }
                //抓取到低价值的物体
                if (collidingObject.getObjectValueLevel() == ObjectValueLevel.LOW) {
                    lowSound.musicMain(1);
                }
                
                if (collidingObject.getObjectValueLevel() == ObjectValueLevel.SPECIAL) {
                    specialSound.musicMain(1);
                }
            }

            collidingObject.setX(endX);
            collidingObject.setY(endY + collidingObject.getHeight() / 2 - 3 + 6);
            collidingObject.setAngle(-1 * angle);
            //如果现在状态是摆动状态，抓取返回，加分
            if (currentState == RopeState.SWING) {
                //如果购买了钻石抛光并且抓到的是钻石，则钻石价值由600变为900
                if (diamondPro && collidingObject.getName() == ItemName.DIAMOND) {
                    grabValue = collidingObject.getValue() + 300;
                }
                //如果购买了四叶草并且抓到的是袋子，则袋子价值增加500
                else if (treasureBagPro && collidingObject.getName() == ItemName.TREASUREBAG) {
                    grabValue = collidingObject.getValue() + 500;
                }
                //如果购买了石头收藏家书籍并且抓到的是石头，则石头的价值变为原来的三倍
                else if (stonePro==true&&collidingObject.getName()== ItemName.STONE)
                {
                    grabValue = collidingObject.getValue()*3;
                }
                else
                {
                    grabValue = collidingObject.getValue();
                }
                isColliding = false;

                gameWindow.delay(GameWindow.TIME_PER_FRAME * 9);
                //抓取成功，物体消失
                collidingObject.vanish();
                //收回成功
                isRetrieved = true;
                isSuccessed = true;
                overallValue += grabValue;
            }
        }
    }

    //返回碰撞检测成功的那个物体
    public GameObject detectCollision() {
        for (GameObject object : gameWindow.getGameobjects()) {
            if (rigidbody.hasCollisionWith(object.getRigidbody())) {
                object.setColliding(true);
                return object;
            }
        }
        return null;
    }

    //设置商品的特性
    public void setProduct(ProductStatus productStatus) {
        diamondPro = productStatus.getIsPolish();
        treasureBagPro = productStatus.getIsClover();
        stonePro = productStatus.getIsBook();
        strengthPro = productStatus.getIsDrink();
        isBoom = productStatus.getIsDynamite();
    }

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
}
