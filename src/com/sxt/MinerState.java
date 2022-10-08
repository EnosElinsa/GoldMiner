package com.sxt;

// 矿工的四种状态
public enum MinerState {
    IDLE,   // 静置
    DIG,    // 挖（钩子往下）
    PULL,   // 拉（钩子往上）
    STRONG, // 获得力量
    THROW   // 扔炸弹
}