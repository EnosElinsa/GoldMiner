package gamebody.main;

import gamebody.engine.GameObject;
import gamebody.engine.Rigidbody;
import gamebody.object.ObjectValueLevel;

import java.awt.*;

public class Rope extends GameObject {

    private int startX = GameWindow.INIT_WIDTH / 2 - 3; // 绳索的起点坐标横坐标
    private int startY = 121;                           // 绳索的起点坐标纵坐标
    private int endX;                                   // 绳索的终点坐标横坐标                   
    private int endY;                                   // 绳索的终点坐标纵坐标  
    private int length = MIN_LENGTH;
    private double timer;
    private int retrieveRate = INIT_RETRIEVE_RATE;
    private int overallValue;
    private Thread ropeThread = new Thread(this);
    private RopeState currentState = RopeState.SWING;
    private GameWindow gameWindow;

    public static final int MAX_LENGTH = 650;
    public static final int MIN_LENGTH = 16;
    public static final int GRAB_RATE = 23;
    public static final int INIT_RETRIEVE_RATE = 35;

    //判断物体是否收回成功
    private boolean isRetrieved = false;

    private int grabValue;

    private Sound highSound=new Sound("sound/sound_wav/high-value.wav");//抓取到高价值物体的音效
    private Sound normalSound=new Sound("sound/sound_wav/normal-value.wav");//抓取到普通价值物体的音效
    private Sound lowSound=new Sound("sound/sound_wav/low-value.wav");//抓取到低价值物体的音效

    private boolean isSuccessed=true;//标记是否抓取物体成功


    public Rope(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        ropeThread.start();
    }

    @Override
    public void render(Graphics graphics) {
        Graphics2D graphics2d = (Graphics2D) graphics;

        BasicStroke stokeLine = new BasicStroke(1.6f);
        graphics2d.setStroke(stokeLine); // 设置线的粗细
        Color colorOfRope = new Color(49, 51, 64);

        graphics2d.setColor(colorOfRope);
        graphics2d.drawLine(startX, startY, endX, endY);
    }

    @Override
    public void update() {
        switch (currentState) {
            case SWING:
                angle = 1.3 * Math.cos(timer); // 用简谐运动方程近似模拟钩子的单摆运动
                // System.out.println(Math.toDegrees(angle));
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
                    length -= retrieveRate;
                }
                else {
                    length = MIN_LENGTH;
                    currentState = RopeState.SWING;
                }
                break;
        }

        endX = (int)(startX + length * Math.sin(angle));
        endY = (int)(startY + length * Math.cos(angle));
        rigidbody = new Rigidbody(endX, endY, 5, 5);

        //碰撞失败，即未抓取到物体
        if (isColliding == false && (collidingObject = detectCollision()) != null) {
            isColliding = true;
            currentState = RopeState.RETRIEVE;
            retrieveRate /= collidingObject.getMass();
        }
        //碰撞成功，即抓取到物体
        if (isColliding && collidingObject != null) {
            grabValue = collidingObject.getValue();
            if (isSuccessed==true)
            {
                isSuccessed=false;
                //抓取到高价值的物体
                if (collidingObject.getObjectValueLevel()== ObjectValueLevel.HIGH)
                {
                    highSound.musicMain(1);
                }
                //抓取到普通价值的物体
                if (collidingObject.getObjectValueLevel()== ObjectValueLevel.NORMAL)
                {
                    normalSound.musicMain(1);
                }
                //抓取到低价值的物体
                if (collidingObject.getObjectValueLevel()== ObjectValueLevel.LOW)
                {
                    lowSound.musicMain(1);
                }
            }
            collidingObject.setX(endX);
            collidingObject.setY(endY + collidingObject.getHeight() / 2 - 3);
            collidingObject.setAngle(-1 * angle);
            //如果现在状态是摆动状态，抓取返回，加分
            if (currentState == RopeState.SWING) {
                grabValue  = collidingObject.getValue();
                isColliding = false;
                retrieveRate = INIT_RETRIEVE_RATE;
                try {
                    Thread.sleep(GameWindow.TIME_PER_FRAME * 9);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //抓取成功，物体消失
                collidingObject.vanish();
                //收回成功
                isRetrieved = true;
                isSuccessed=true;
                overallValue += collidingObject.getValue();
            }
        }
    }

    //返回碰撞检测成功的那个物体
    public GameObject detectCollision() {
        for (GameObject object : gameWindow.getGameobjects()) {
            if (rigidbody.hasCollisionWith(object.getRigidbody())) {
                return object;
            }
        }
        return null;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
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
}
