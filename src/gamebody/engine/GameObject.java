package gamebody.engine;

import java.awt.*;

import javax.swing.ImageIcon;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int mass;

    protected Image texture;
    protected Rigidbody rigidbody;

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

    public void render(Graphics graphics) {
        graphics.drawImage(texture, x, y, null);
    }

    public void vanish() {
        x = 2000;
        y = 2000;
        rigidbody = new Rigidbody(x, y, width, height);
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

    public int getMass() {
        return mass;
    }

    
}
