package gamebody.scenes.items;

import gamebody.engine.Audio;
import gamebody.engine.GameObject;
import gamebody.engine.ItemName;
import gamebody.engine.Rigidbody;
import gamebody.scenes.GameWindow;
import gamebody.scenes.characters.Rope;
import gamebody.scenes.characters.RopeState;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Vector;

public class Dynamite extends GameObject {

    private static final String TEXTURE_DIRECTORY = "resources/dynamite.png";
    private static final int SPAWN_X = Rope.START_X;
    private static final int SPAWN_Y = Rope.START_Y;
    private static final int STEP = 50;
    private static final int RADIUS_OF_EXPLOSION = 50;

    private Audio explosionSound = new Audio("sound/sound_wav/explosive.wav");
    private Image explosionImage = new ImageIcon("resources/dynamite-explosion-0.png").getImage();
    private GameWindow gameWindow;
    private Vector<GameObject> objectsWithinRange = new Vector<>();
    private boolean isTriggered;
    private Thread dynamiteThread = new Thread(this);

    private Image dynamiteIcon= new ImageIcon(TEXTURE_DIRECTORY).getImage();

    public Dynamite(GameWindow gameWindow)  {
        super(SPAWN_X, SPAWN_Y, TEXTURE_DIRECTORY);
        this.gameWindow = gameWindow;
        angle = Math.PI * 2 - gameWindow.getRope().getAngle();
        dynamiteThread.start();
    }

    private GameObject detectCollidingObject() {
        for (GameObject gameObject : gameWindow.getGameobjects()) {
            if (rigidbody.hasCollisionWith(gameObject.getRigidbody()) && gameObject.isOnHook()) {
                return gameObject;
            }
        }
        return null;
    }
    
    private double getDistance(GameObject o1, GameObject o2) {
        return Math.sqrt(Math.pow(o1.getX() - o2.getX(), 2) + Math.pow(o1.getY() - o2.getY(), 2));
    }

    private void getObjectsWithinRange() {
        for (GameObject gameObject : gameWindow.getGameobjects()) {
            if (getDistance(gameObject, this) <= RADIUS_OF_EXPLOSION + gameObject.getHeight() / 2 
            && gameObject != this) {
                objectsWithinRange.add(gameObject);
            }
        }
        objectsWithinRange.add(collidingObject);
    }

    private Thread explodeThread = new Thread(() -> {
        texture = explosionImage;
        new Thread(() -> { explosionSound.musicMain(1); }).start();;
        //explosionSound.musicMain(1);
        for (GameObject gameObject : objectsWithinRange) {
            gameObject.setColliding(true);
            gameObject.setTexture(explosionImage);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (gameObject.getName() != ItemName.EXPLOSIVE) {
                gameObject.vanish();
            }
        }
        vanish();
    });
    
    private void explode() {
        isTriggered = true;
        getObjectsWithinRange();
        explodeThread.start();
        isTerminated = true;
    }

    @Override
    public void render(Graphics graphics, JPanel panel) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        if (isVanished == false) {
            affineTransform = AffineTransform.getTranslateInstance(x - width / 2, y - height / 2);
            affineTransform.rotate(angle - Math.toRadians(40), width / 2, height / 2);
            graphics2d.drawImage(texture, affineTransform, panel);
        }
    }


    @Override
    protected void update() throws InterruptedException {
        x += Math.sin(gameWindow.getRope().getAngle()) * STEP;
        y += Math.cos(Math.abs(gameWindow.getRope().getAngle())) * STEP;
        rigidbody = new Rigidbody(x, y, width, height);
        collidingObject = detectCollidingObject();
        if (collidingObject != null && isTriggered == false) {
            gameWindow.getRope().setCurrentState(RopeState.RETRIEVE);
            gameWindow.getRope().setRetrieveRate(Rope.INIT_RETRIEVE_RATE);
            gameWindow.getRope().setColliding(false);
            explode();
        }
    }

    public Thread getDynamiteThread() {
        return dynamiteThread;
    }

    public Thread getExplodeThread() {
        return explodeThread;
    }
}
