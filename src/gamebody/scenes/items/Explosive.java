package gamebody.scenes.items;

import gamebody.engine.Animation;
import gamebody.engine.Audio;
import gamebody.engine.GameObject;
import gamebody.engine.ItemName;
import gamebody.scenes.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Explosive extends GameObject {

    private static final String TEXTURE_DIRECTORY = "resources/explosive.png";
    private static final String TEXTURE_DIRECTORY_2 = "resources/explosive-2.png";
    private static final int RADIUS_OF_EXPLOSION = 120;
    private static final int EXPLOSIVE_VALUE = 2;
    private static final int EXPLOSIVE_MASS = 2;

    private Image brokenTexture = new ImageIcon(TEXTURE_DIRECTORY_2).getImage();
    private Audio explosionSound = new Audio("sound/sound_wav/explosive.wav");
    private Animation explosionAnimation = new Animation("resources/tnt-explosion", 10);

    private GameWindow gameWindow;
    private Thread explosiveThread = new Thread(this);
    private Vector<GameObject> objectsWithinRange = new Vector<>();
    private boolean isTriggered;
    private int initialX;
    private int initialY;

    public Explosive() {}

    public Explosive(int x, int y, GameWindow gameWindow) {
        super(x, y, TEXTURE_DIRECTORY);
        mass = EXPLOSIVE_MASS;
        value = EXPLOSIVE_VALUE;
        name = ItemName.EXPLOSIVE;
        initialX = x;
        initialY = y;
        this.gameWindow = gameWindow;
        explosiveThread.start();
    }

    private void getObjectsWithinRange() {
        for (GameObject gameObject : gameWindow.getGameobjects()) {
            if (getDistance(gameObject, this) <= RADIUS_OF_EXPLOSION + gameObject.getHeight() / 2 
                && gameObject != this) {
                objectsWithinRange.add(gameObject);
            }
        }
    }

    private void explode() {
        getObjectsWithinRange();
        for (GameObject gameObject : objectsWithinRange) {
            System.out.println(gameObject);
            gameObject.setColliding(true);
            gameObject.setTexture(explosionAnimation.getNextFrame());
            if (gameObject.getName() != ItemName.EXPLOSIVE) gameObject.vanish();
            explosionSound.musicMain(1);
        }
        texture = brokenTexture;
    }

    private double getDistance(GameObject o1, GameObject o2) {
        return Math.sqrt(Math.pow(o1.getX() - o2.getX(), 2) + Math.pow(o1.getY() - o2.getY(), 2));
    }

    @Override
    public void render(Graphics graphics, JPanel jPanel) {
        super.render(graphics, jPanel);
        if (isTriggered && isVanished == false) {
            Image explosionImage = explosionAnimation.getNextFrame();
            graphics.drawImage(explosionImage, initialX - explosionImage.getWidth(jPanel) / 2, 
                initialY - explosionImage.getHeight(jPanel) / 2, jPanel);
        }
    }

    @Override
    protected void update() {
        if (explosionAnimation.getCurrentFrameIndex() == explosionAnimation.getImages().size() - 1) {
            vanish();
        }
        if (isColliding && isTriggered == false) {
            explosionSound.musicMain(1);
            isTriggered = true;
            explode();
        }
    }
}
