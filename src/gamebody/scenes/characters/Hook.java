package gamebody.scenes.characters;

import gamebody.engine.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Hook extends GameObject {

    private static final String TEXTURE_DIRECTORY = "resources/hook.png";
    public static final double SCALE_RATIO = 0.8;
    private Rope rope;

    public Hook(Rope rope) {
        this.rope = rope;
        x = this.rope.getEndX();
        y = this.rope.getEndY();
        texture = new ImageIcon(TEXTURE_DIRECTORY).getImage();
        width = texture.getWidth(null);
        height = texture.getHeight(null);
    }

    @Override
    public void render(Graphics graphics, JPanel panel) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        double rotation = rope.isColliding() ? Math.toRadians(15) : 0;
        affineTransform = AffineTransform.getTranslateInstance(x - width * 0.8, y);
        affineTransform.scale(SCALE_RATIO, SCALE_RATIO);
        affineTransform.rotate(angle - rotation, width, 0);
        graphics2d.drawImage(texture, affineTransform, panel);

        AffineTransform affineTransformMirror = AffineTransform.getTranslateInstance(x + width * 0.8, y);
        affineTransformMirror.scale(-SCALE_RATIO, SCALE_RATIO);
        affineTransformMirror.rotate(-1 * (angle + rotation), width, 0);
        graphics2d.drawImage(texture, affineTransformMirror, null);
    }

    @Override
    protected void update() {
        
    }
}
