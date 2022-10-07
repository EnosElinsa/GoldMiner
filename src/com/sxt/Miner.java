package com.sxt;

import java.awt.*;

public class Miner {
    public Miner() {
        animationIdle = new Animation("miner-dig-", 0);
        animationDig = new Animation("miner-dig-", 4);
        animationPull = new Animation("miner-pull-", 16);
        animationStrong = new Animation("miner-strong-", 2);
        animationThrow = new Animation("miner-throw-", 5);
        currentState = CharacterState.IDLE;
    }
    // 矿工的四种状态
    public static enum CharacterState {
        IDLE,   // 静置
        DIG,    // 挖（钩子往下）
        PULL,   // 拉（钩子往上）
        STRONG, // 获得力量
        THROW   // 扔炸弹
    }

    private CharacterState currentState;
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

    public CharacterState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(CharacterState currentState) {
        this.currentState = currentState;
    }
} 
