package gamebody.scenes.characters;

import gamebody.engine.Animation;
import gamebody.engine.GameObject;
import gamebody.scenes.GameWindow;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏背景下的主角{@code Miner}矿工类。
 * @author Enos
 * @see MinerState
 * @see Animation
 * @see Rope
 */
public class Miner extends GameObject {
    
    /**
     * 生成的矿工的固定横坐标位置。
     */
    public static final int SPAWN_X = GameWindow.INIT_WIDTH / 2 - 30;

    /**
     * 生成的矿工的固定纵坐标位置。
     */
    public static final int SPAWN_Y = 57;

    /**
     * 矿工的当前状态。
     * <p>开始时设置矿工的状态为{@code MinerState.IDLE}静置。
     */
    private MinerState currentState = MinerState.IDLE;

    /**
     * 静置时矿工的动画。 
     */
    private Animation animationIdle;    

    /**
     * 挖矿时矿工的动画。 
     */
    private Animation animationDig;

    /**
     * 拉线时矿工的动画。
     */
    private Animation animationPull; 

    /**
     * 获得力量时矿工的动画。 
     */
    private Animation animationStrong;

    /**
     * 扔炸弹时矿工的动画。
     */
    private Animation animationThrow;

    /**
     * 当前的动画帧。
     */
    private Image currentFrame;
    
    /**
     * 由于矿工游戏对象需要实时进行更新，需要有自己的更新状态的线程。
     */
    private Thread minerThread = new Thread(this);

    /**
     * 矿工的默认构造方法。
     */
    public Miner() {
        x = SPAWN_X;
        y = SPAWN_Y;
        // 初始化动画
        animationIdle = new Animation("resources/miner-idle", 1);
        animationDig = new Animation("resources/miner-dig", 4);
        animationPull = new Animation("resources/miner-pull", 16);
        animationStrong = new Animation("resources/miner-strong", 2);
        animationThrow = new Animation("resources/miner-throw", 5);
        
        // 开启矿工的动画线程
        minerThread.start();
    }

    /**
     * 根据{@code currentFrame}当前的动画帧来渲染绘制自己。
     */
    @Override
    public void render(Graphics graphics, JPanel panel) {
        graphics.drawImage(currentFrame, SPAWN_X, SPAWN_Y, panel);
    }

    /**
     * 根据当前状态，更新对应帧图片。
     */
    public void updateCurrentFrame() {
        switch (currentState) {
            case IDLE:
                currentFrame = animationIdle.getNextFrame(); break;
            case DIG:
                currentFrame = animationDig.getNextFrame(); 
                // 在这里不希望矿工挖取的动画重复从头播放，而是停留在最后一帧
                if (animationDig.getCurrentFrameIndex() == animationDig.getImages().size() - 1) {
                    animationDig.setCurrentFrameIndex(2);
                }
                break;
            case PULL:
                currentFrame = animationPull.getNextFrame(); break;
            case STRONG:
                currentFrame = animationStrong.getNextFrame(); break;
            case THROW:
                currentFrame = animationThrow.getNextFrame(); 
                if (animationThrow.getCurrentFrameIndex() == animationThrow.getImages().size() - 1) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentState = MinerState.PULL;
                }
                break;
        }
    }

    /**
     * 将当前状态对应的动画的帧索引都置为0
     */
    public void restoreFrameIndex() {
        switch (currentState) {
            case IDLE:
                animationIdle.setCurrentFrameIndex(0);
            case DIG:
                animationDig.setCurrentFrameIndex(0);
            case PULL:
                animationPull.setCurrentFrameIndex(0); 
            case STRONG:
                animationStrong.setCurrentFrameIndex(0);
            case THROW:
                animationThrow.setCurrentFrameIndex(0); 
        }
    }

    @Override
    protected void update() {
        updateCurrentFrame();
    }

    public MinerState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(MinerState currentState) {
        // 要更新状态之前，要先把当前状态对应的动画的帧索引都置为0
        restoreFrameIndex(); 
        this.currentState = currentState;
    }
} 
