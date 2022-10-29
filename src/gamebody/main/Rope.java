package gamebody.main;

import java.awt.*;

import gamebody.engine.*;

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

    public static final int MAX_LENGTH = 500;
    public static final int MIN_LENGTH = 16;
    public static final int GRAB_RATE = 23;
    public static final int INIT_RETRIEVE_RATE = 35;
    
    public Rope(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        ropeThread.start();
    }

    @Override
    public void render(Graphics graphics) {
        Graphics2D graphics2d = (Graphics2D) graphics;

        BasicStroke stokeLine = new BasicStroke(1.6f);
        graphics2d.setStroke(stokeLine); // 设置线的粗细
        Color colorOfRope = new Color(49, 52, 64);
        
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
        
        if (isColliding == false && (collidingObject = detectCollision()) != null) {
            isColliding = true;
            currentState = RopeState.RETRIEVE;
            retrieveRate /= collidingObject.getMass();
        }
        if (isColliding && collidingObject != null) {
            collidingObject.setX(endX);
            collidingObject.setY(endY + collidingObject.getHeight() / 2 - 3);
            collidingObject.setAngle(-1 * angle);
            if (currentState == RopeState.SWING) {
                isColliding = false;
                retrieveRate = INIT_RETRIEVE_RATE;
                try {
                    Thread.sleep(GameWindow.TIME_PER_FRAME * 9);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                collidingObject.vanish();
                overallValue += collidingObject.getValue();
            }
        }
    }

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
}
