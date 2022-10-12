package com.sxt;

import java.awt.*;

public class Miner implements Runnable {
    private MinerState currentState;
    private Animation animationIdle;
    private Animation animationDig;
    private Animation animationPull;
    private Animation animationStrong;
    private Animation animationThrow;

    private Thread minerThread;
    private Image currentFrame;

    public Miner() {
        animationIdle = new Animation("resources/miner-idle", 1);
        animationDig = new Animation("resources/miner-dig", 4);
        animationPull = new Animation("resources/miner-pull", 16);
        animationStrong = new Animation("resources/miner-strong", 2);
        animationThrow = new Animation("resources/miner-throw", 5);
        currentState = MinerState.IDLE;
        minerThread = new Thread(this);
        minerThread.start();
    }

    public void drawSelf(Graphics graphics) {
        graphics.drawImage(currentFrame, GameWindow.INIT_WIDTH / 2 - 30, 57, null);
    }

    public void updateCurrentFrame() {
        switch (currentState) {
            case IDLE:
                currentFrame = animationIdle.getNextFrame(); break;
            case DIG:
                currentFrame = animationDig.getNextFrame(); break;
            case PULL:
                currentFrame = animationPull.getNextFrame(); break;
            case STRONG:
                currentFrame = animationStrong.getNextFrame(); break;
            case THROW:
                currentFrame = animationThrow.getNextFrame(); break;
        }
    }

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

    public MinerState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(MinerState currentState) {
        restoreFrameIndex();
        this.currentState = currentState;
    }

    @Override
    public void run() {
        while (true) {
            updateCurrentFrame();
            try {
                Thread.sleep(70);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
} 
