package gamebody.body.characters;

import gamebody.engine.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * <p> 游戏背景下的{@code Hook}钩子，与{@code Rope}绳索相连接，且有对应的抓取动作。
 * @author Enos
 * @see Rope
 * @see AffineTransform
 */
public class Hook extends GameObject {

    /**
     * 贴图的路径，为了方便修改与标识，这里使用静态常量来保存。
     */
    private static final String TEXTURE_DIRECTORY = "resources/hook.png";

    /**
     * 为了适应绳索的粗细程度，这里的缩放比例设置为{@code 0.8}。
     */
    public static final double SCALE_RATIO = 0.8;

    /**
     * 钩子在抓取物体时的旋转角度，默认设置为15°
     */
    public static final int ROTATION = 15;

    /**
     * {@code Rope}绳索的引用，用于与{@code Hook}钩子的交互。
     */
    private Rope rope;

    /**
     * {@code Hook}的唯一构造方法。
     * @param rope {@code Rope}绳索的引用
     */
    public Hook(Rope rope) {
        super(rope.getEndX(), rope.getEndY(), TEXTURE_DIRECTORY);
        this.rope = rope;
    }

    /**
     * 为了实现钩子的抓取动作，这里覆盖了{@code GameObject}基类的{@code render}方法。
     */
    @Override
    public void render(Graphics graphics, JPanel panel) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        // 如果钩子有抓取到物体，则有一定的旋转角度
        double rotation = rope.isColliding() ? Math.toRadians(ROTATION) : 0;
        affineTransform = AffineTransform.getTranslateInstance(x - width * 0.8, y);
        affineTransform.scale(SCALE_RATIO, SCALE_RATIO);
        affineTransform.rotate(angle - rotation, width, 0);
        graphics2d.drawImage(texture, affineTransform, panel);

        // 绘制镜像的另一半钩子
        AffineTransform affineTransformMirror = AffineTransform.getTranslateInstance(x + width * 0.8, y);
        affineTransformMirror.scale(-SCALE_RATIO, SCALE_RATIO);
        affineTransformMirror.rotate(-1 * (angle + rotation), width, 0);
        graphics2d.drawImage(texture, affineTransformMirror, null);
    }

    @Override
    protected void update() {}
}
