package com.sxt;

import java.awt.*;

public class Miner {
    public Miner() {
        animationIdle = new Animation("miner-dig-", 0);
        animationDig = new Animation("miner-dig-", 4);
        animationPull = new Animation("miner-pull-", 16);
        animationStrong = new Animation("miner-strong-", 2);
        animationThrow = new Animation("miner-throw-", 5);
        currentState = MinerState.IDLE;
    }
    
    private MinerState currentState;
    private Animation animationIdle;
    private Animation animationDig;
    private Animation animationPull;
    private Animation animationStrong;
    private Animation animationThrow;

    public void drawSelf(Graphics graphics, int x, int y) {
        switch (currentState) {
        case IDLE:
            animationIdle.drawNextFrame(graphics, x, y); break;
        case DIG:
            animationDig.drawNextFrame(graphics, x, y); break;
        case PULL:
            animationPull.drawNextFrame(graphics, x, y); break;
        case STRONG:
            animationStrong.drawNextFrame(graphics, x, y); break;
        case THROW:
            animationThrow.drawNextFrame(graphics, x, y); break;
        }
    }

    public MinerState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(MinerState currentState) {
        this.currentState = currentState;
    }
} 
