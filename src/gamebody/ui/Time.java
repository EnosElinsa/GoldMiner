package gamebody.ui;

import gamebody.scenes.Background;
import gamebody.scenes.GameWindow;

/**
 * <p>游戏时间类。
 * <p>一个简单的倒计时功能，在每一关卡的游戏中记录游戏时间
 *
 * @author JiajiaPig
 * @see GameWindow
 */

public class Time {
    
    private long startTime;
    private long endTime;

    public Time() {
        startTime = System.currentTimeMillis();
    }

    public long countDown() {
        endTime = System.currentTimeMillis();
        long time= 60 - (endTime-startTime) / 1000;
        return time;
    }
}
