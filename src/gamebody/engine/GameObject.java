package gamebody.engine;

import gamebody.scenes.GameWindow;
import gamebody.scenes.ObjectValueLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * <p>游戏物体的基类{@code GameObject}。
 * <p>该类包含了一个游戏物体在游戏场景下的各种基本属性和基本方法。
 * @author Enos
 * @author JiajiaPig
 * @see {@link Rigidbody}
 * @see {@link AffineTransform}
 * @see {@link ItemName}
 */
public abstract class GameObject implements Runnable {

    /**
     * 物体中心横坐标。
     */
    protected int x;

    /**
     * 物体中心纵坐标。
     */
    protected int y; 

    /**
     * 物体的宽度，一般为该游戏物体贴图{@code texture}的宽度决定。
     */
    protected int width;

    /**
     * 物体的高度，一般为该游戏物体贴图{@code texture}的高度决定。
     */
    protected int height;

    /**
     * 物体的质量，默认为{@code 1}。
     */
    protected double mass = 1;

    /**
     * 物体转动的角度，默认为{@code 0}。
     */
    protected double angle = 0; 
    
    /**
     * 物体的贴图{@code texture}的缩放比例，默认为{@code 1.0}。
     */
    protected double scaleRatio = 1.0;  
    
    /**
     * 物体的价值。
     */
    protected int value;                 
    
    /**
     * 物体的名字标识，为{@link ItemName}的枚举类型。
     */
    protected ItemName name;

    /**
     * 物体的贴图。
     */
    protected Image texture;

    /**
     * 物体的碰撞体{@link Rigidbody}。
     */
    protected Rigidbody rigidbody;

    /**
     * 如果当前没有物体与本物体产生碰撞，则值为{@code null}，否则则为与其碰撞的物体的引用。
     */
    protected GameObject collidingObject;
    
    /**
     * 标识物体是否与其他物体发生碰撞。
     */
    protected boolean isColliding;

    /**
     * 2D仿射变换，用于保存游戏物体的位置、缩放和旋转等信息。
     */
    protected AffineTransform affineTransform;

    /**
     * 如果该物体是实时更新的，{@code isTerminated == true}表示更新停止。
     */
    protected boolean isTerminated;

    /**
     * 用于标识物体是否还存在在游戏场景下。
     */
    protected boolean isVanished;

    /**
     * 用于标识物体是否在{@code Hook}钩子下。
     */
    protected boolean isOnHook;

    /**
     * 物体的价值等级。
     */
    protected ObjectValueLevel objectValueLevel;

    public GameObject() {}
    

    /**
     * 生成一个只有位置信息的游戏物体。
     * @param x 横坐标
     * @param y 纵坐标
     */
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 生成一个包含位置信息、贴图和碰撞体的物体。
     * <p>默认情况下碰撞体的大小与贴图大小对应。
     * @param x 横坐标
     * @param y 纵坐标
     * @param textureDirectory 游戏贴图的路径
     */
    public GameObject(int x, int y, String textureDirectory) {
        this.x = x;
        this.y = y;
        texture = new ImageIcon(textureDirectory).getImage();
        width = texture.getWidth(null);
        height = texture.getHeight(null);
        rigidbody = new Rigidbody(x, y, width, height);
    }

    /**
     * <p>该方法需要继承{@code GameObject}的类进行覆盖。
     * <p>该方法体内应包含需要实时更新的代码。
     * @throws InterruptedException
     */
    protected abstract void update() throws InterruptedException;

    /**
     * 物体的渲染方法。
     * <p>默认会把物体根据位置、旋转和缩放信息渲染绘制在游戏场景下。
     * @param graphics 绘制物体所需的画面上下文信息
     * @param panel 游戏场景所在的面板
     */
    public void render(Graphics graphics, JPanel panel) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        affineTransform = AffineTransform.getTranslateInstance(x - width / 2, y - height / 2);
        affineTransform.rotate(angle, width / 2, height / 2);
        affineTransform.scale(scaleRatio, scaleRatio);
        graphics2d.drawImage(texture, affineTransform, panel);
    }

    /**
     * 将物体移到游戏场景外，把物体标识为消失并且停止物体的更新（如果物体是实时更新的话）。
     */
    public void vanish() {
        x = GameWindow.INIT_WIDTH * 2;
        y = GameWindow.INIT_HEIGHT * 2;
        // 碰撞体也要更新
        rigidbody = new Rigidbody(x, y, width, height);
        isVanished = true;
        isTerminated = true;
    }

    /**
     * 物体线程的{@code run}方法，在{@code isTerminated == false}的情况下会持续运行，
     * 通过物体的{@code update}方法进行对物体状态的实时更新。
     */
    @Override
    public void run() {
        while (!isTerminated) {
            try {
                update();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(GameWindow.TIME_PER_FRAME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // getters and setters.
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

    public ItemName getName() {
        return name;
    }

    public void setName(ItemName name) {
        this.name = name;
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

    public boolean isOnHook() {
        return isOnHook;
    }

    public void setOnHook(boolean isOnHook) {
        this.isOnHook = isOnHook;
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean isTerminated) {
        this.isTerminated = isTerminated;
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

    public ObjectValueLevel getObjectValueLevel() {
        return objectValueLevel;
    }

    public void setObjectValueLevel(ObjectValueLevel objectValueLevel1) {
        objectValueLevel=objectValueLevel1;
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    public boolean isVanished() {
        return isVanished;
    }

    public void setVanished(boolean isVanished) {
        this.isVanished = isVanished;
    }
}


