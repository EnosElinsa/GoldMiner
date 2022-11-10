package gamebody.engine;

import java.awt.geom.Rectangle2D;

/**
 * 刚体类：用于碰撞检测
 */
public class Rigidbody {
    
    private Rectangle2D rigidbody;

    public Rigidbody() {}
    public Rigidbody(int centerX, int centerY, int width, int height) {
        rigidbody = new Rectangle2D.Double(centerX - width / 2, centerY - height / 2, width, height);
    }

    public boolean hasCollisionWith(Rigidbody other) {
        if (other != null && rigidbody.intersects(other.rigidbody.getBounds2D())) {
            return true;
        }
        return false;
    }
}
