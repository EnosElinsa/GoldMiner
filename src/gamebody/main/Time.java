package gamebody.main;

public class Time {
    private static int time=60;

    public Time(){}

    public void countDown()
    {
        while (time>=0)
        {
            time--;
        }
        time=60;
    }

    public int getTime()
    {
        return time;
    }
}
