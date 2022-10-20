package gamebody.engine;

import java.awt.geom.*;

/**
 * 刚体类：用于碰撞检测
 */
public class Rigidbody {
    private Ellipse2D rigidbody;

    public Rigidbody() {}
    public Rigidbody(int centerX, int centerY, int width, int height) {
        rigidbody = new Ellipse2D.Double(centerX - width / 2, centerY - height / 2, width, height);
    }

    public boolean hasCollisionWith(Rigidbody other) {
        if (rigidbody.intersects(other.rigidbody.getBounds2D())) {
            return true;
        }
        return false;
    }
}
