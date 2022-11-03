package gamebody.engine;

import gamebody.main.GameWindow;
import gamebody.object.ObjectValueLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class GameObject implements Runnable {

    protected int x;                     // 物体中心横坐标
    protected int y;                     // 物体中心纵坐标
    protected int width;
    protected int height;
    protected double mass = 1;           // 物体质量
    protected double angle = 0;          // 物体转动的角度
    protected double scaleRatio = 1.0;   // 物体的缩放比例，默认为1.0
    protected int value;                 // 物体的价值
    
    protected Image texture;
    protected Rigidbody rigidbody;
    protected AffineTransform affineTransform;
    protected GameObject collidingObject = null;
    protected boolean isColliding = false;
    protected Thread thread;
    protected boolean isAnimated;

    protected ObjectValueLevel objectValueLevel;//物体的价值等级

    public GameObject() {}
    
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameObject(int x, int y, String textureDirectory) {
        this.x = x;
        this.y = y;
        texture = new ImageIcon(textureDirectory).getImage();
        width = texture.getWidth(null);
        height = texture.getHeight(null);
        rigidbody = new Rigidbody(x, y, width, height);
    }

    public GameObject(int x, int y, String textureDirectory, boolean isAnimated) {
        this(x, y, textureDirectory);
        this.isAnimated = isAnimated;
        thread = new Thread(this);
        thread.run();
    }

    protected abstract void update();

    public void render(Graphics graphics) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        affineTransform = AffineTransform.getTranslateInstance(x - width / 2, y - height / 2);
        affineTransform.rotate(angle, width / 2, height / 2);
        affineTransform.scale(scaleRatio, scaleRatio);
        graphics2d.drawImage(texture, affineTransform, null);
    }

    public void vanish() {
        x = 2000;
        y = 2000;
        rigidbody = new Rigidbody(x, y, width, height);
    }

    @Override
    public void run() {
        while (true) {
            update();
            try {
                Thread.sleep(GameWindow.TIME_PER_FRAME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getTexture() {
        return texture;
    }

    public Rigidbody getRigidbody() {
        return rigidbody;
    }

    public double getMass() {
        return mass;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public AffineTransform getAffineTransform() {
        return affineTransform;
    }

    public GameObject getCollidingObject() {
        return collidingObject;
    }

    public boolean isColliding() {
        return isColliding;
    }

    public void setColliding(boolean isColliding) {
        this.isColliding = isColliding;
    }

    public double getScaleRatio() {
        return scaleRatio;
    }

    public void setScaleRatio(double scaleRatio) {
        this.scaleRatio = scaleRatio;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ObjectValueLevel getObjectValueLevel()
    {
        return objectValueLevel;
    }

    public void setObjectValueLevel(ObjectValueLevel objectValueLevel1)
    {
        objectValueLevel=objectValueLevel1;
    }
}
