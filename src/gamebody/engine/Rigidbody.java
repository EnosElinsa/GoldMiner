package gamebody.engine;

import java.awt.geom.Rectangle2D;

/**
 * 刚体类，用于游戏物体间碰撞检测。
 * @author Enos
 */
public class Rigidbody {
    
    /**
     * 用一个{@code Rectangle2D}的矩形来表示物体的碰撞箱子。
     */
    private Rectangle2D rigidbody;

    public Rigidbody() {}
    
    /**
     * 根据物体的位置以及大小信息构造物体的碰撞体。
     * @param centerX 物体的中心位置的横坐标
     * @param centerY 物体的中心位置的纵坐标
     * @param width 物体的宽度
     * @param height 物体的高度
     */
    public Rigidbody(int centerX, int centerY, int width, int height) {
        rigidbody = new Rectangle2D.Double(centerX - width / 2, centerY - height / 2, width, height);
    }

    /**
     * <p>检测物体是否与其他物体有碰撞。
     * <p>两个物体之间是否产生碰撞，即两个物体的碰撞箱之间是否有交集，
     * 这里使用{@link Rectangle2D#intersects(Rectangle2D)}方法来进行检测。
     * @param other 其他物体的碰撞体
     * @return {@code true}表示与{@code other}物体有碰撞，反之亦然
     */
    public boolean hasCollisionWith(Rigidbody other) {
        if (other != null && rigidbody.intersects(other.rigidbody.getBounds2D())) {
            return true;
        }
        return false;
    }
}
