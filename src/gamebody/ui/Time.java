package gamebody.ui;

import gamebody.scenes.body.GameWindow;

/**
 * <p>游戏时间类。
 * <p>一个简单的倒计时功能，在每一关卡的游戏中记录游戏时间
 *
 * @author JiajiaPig
 * @see GameWindow
 */

public class Time {
    
    /**
     * 开始时的时间。
     */
    private long startTime;

    /**
     * 结束时的时间。
     */
    private long endTime;

    public Time() {
        startTime = System.currentTimeMillis();
    }

    /**
     * @return 返回倒计时的时间。
     */
    public long countDown() {
        endTime = System.currentTimeMillis();
        long time = 60 - (endTime-startTime) / 1000;
        return time;
    }

    // getters and setters
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
