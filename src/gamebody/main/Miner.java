package gamebody.main;

import gamebody.engine.Animation;
import gamebody.engine.GameObject;

import java.awt.*;

public class Miner extends GameObject {
    
    private MinerState currentState = MinerState.IDLE; // 开始时设置矿工的状态为静置
    private Animation animationIdle;    // 静置时矿工的动画 
    private Animation animationDig;     // 挖矿时矿工的动画 
    private Animation animationPull;    // 拉线时矿工的动画 
    private Animation animationStrong;  // 获得力量时矿工的动画 
    private Animation animationThrow;   // 扔炸弹时矿工的动画 

    private Thread minerThread = new Thread(this);
    private Image currentFrame;

    public Miner() {
        // 初始化动画
        animationIdle = new Animation("resources/miner-idle", 1);
        animationDig = new Animation("resources/miner-dig", 4);
        animationPull = new Animation("resources/miner-pull", 16);
        animationStrong = new Animation("resources/miner-strong", 2);
        animationThrow = new Animation("resources/miner-throw", 5);
        
        // 开启矿工的动画线程
        minerThread.start();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(currentFrame, GameWindow.INIT_WIDTH / 2 - 30, 57, null);
    }

    /**
     * 根据当前状态，更新对应帧图片
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
                currentFrame = animationThrow.getNextFrame(); break;
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
        restoreFrameIndex(); // 要更新状态之前，要先把当前状态对应的动画的帧索引都置为0
        this.currentState = currentState;
    }
} 
