package gamebody.main;

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
