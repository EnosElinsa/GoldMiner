package gamebody.scenes.characters;


/**
 *  <p>矿工的四种状态
 *  <p>{@code IDLE},   静置
 *  <p>{@code DIG},    挖（钩子往下）
 *  <p>{@code PULL},   拉（钩子往上）
 *  <p>{@code STRONG}, 获得力量
 *  <p>{@code THROW}   扔炸弹
 */
public enum MinerState {
    
    /**
     * 静置
     */
    IDLE,

    /**
     * 挖（钩子往下）
     */
    DIG, 

    /**
     * 拉（钩子往上）
     */
    PULL,  

    /**
     * 获得力量
     */
    STRONG,

    /**
     * 扔炸弹
     */
    THROW 
}