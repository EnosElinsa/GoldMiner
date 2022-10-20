package gamebody.engine;

import java.awt.*;

import javax.swing.ImageIcon;

public abstract class GameObject {

    private int x;
    private int y;
    private int width;
    private int height;

    private Image texture;
    private Rigidbody rigidbody;

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

    public void drawSelf(Graphics graphics) {
        graphics.drawImage(texture, x, y, null);
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
}
