package gamebody.body.characters;

/**
 * 绳索的3种状态 
 * <p>{@code SWING},    摆动
 * <p>{@code GRAB},     抓取
 * <p>{@code RETRIEVE}  收回
 */
public enum RopeState {
    /**
     * 摆动
     */
    SWING,  
    
    /**
     * 抓取
     */
    GRAB,
    
    /**
     * 收回
     */
    RETRIEVE
}
