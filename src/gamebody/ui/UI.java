package gamebody.ui;

import gamebody.engine.GameObject;
import gamebody.scenes.GameWindow;
import gamebody.scenes.ObjectValueLevel;
import gamebody.engine.Audio;

import java.awt.*;

import javax.swing.JPanel;

public class UI extends GameObject {

    private GameWindow gameWindow;

    private Audio addValueSound = new Audio("sound/sound_wav/score1.wav");
    private Audio addValueSound2 = new Audio("sound/sound_wav/score2.wav");
    public UI(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public static void drawWords(Graphics graphics, int size, Color color, String str, int x, int y) {
        graphics.setColor(color);
        graphics.setFont(new Font("宋体", Font.BOLD, size));
        graphics.drawString(str, x, y);
    }

    @Override
    protected void update() {}

    @Override
    public void render(Graphics graphics, JPanel panel) {
        // 画文字（金钱、目标、时间、关卡）
        String words1 = "金钱";
        drawWords(graphics,30, Color.WHITE, words1,50,75);

        String words2 = "目标";
        drawWords(graphics,32, Color.WHITE, words2,48,125);

        // 已得金钱值
        int curValue = gameWindow.getRope().getOverallValue();
        String words3 = "$" + Integer.toString(curValue);
        drawWords(graphics,32, Color.GREEN, words3,180,75);

        // 目标金钱值
        String words4 = Integer.toString(gameWindow.getTarget());
        drawWords(graphics,32, Color.RED, words4,180,125);

        String words5 = "时间";
        drawWords(graphics,32, Color.WHITE, words5,650,75);

        String words6 = "关卡";
        drawWords(graphics,32, Color.WHITE, words6,650,128);
        drawWords(graphics,32, Color.RED, Integer.toString(gameWindow.getLevel()),750,128);

        long curTime = gameWindow.getTime().countDown();
        if (curTime >= 0) {
            String words7 = "" + curTime;
            drawWords(graphics,32,Color.RED,words7,750,75);
        }

        //抓取成功后的加分效果
        if(gameWindow.getRope().isRetrieved()==true)
        {
            gameWindow.getRope().setRetrieved(false);
            int addValue = gameWindow.getRope().getGrabValue();
            if (gameWindow.getRope().getCollidingObject().getObjectValueLevel() == ObjectValueLevel.HIGH) {
                addValueSound.musicMain(1);
            } else {
                addValueSound2.musicMain(1);
            }
            String words8 = "+" + Integer.toString(addValue);
            drawWords(graphics,30,Color.GREEN,words8,300,75);
        }

        //抓取成功后的加分效果
        if(gameWindow.getRope().isRetrieved() == true)
        {
            gameWindow.getRope().setRetrieved(false);
            int addValue = gameWindow.getRope().getGrabValue();
            String words8 = "+" + Integer.toString(addValue);
            drawWords(graphics,30, Color.GREEN, words8,300,75);
        }
    }
}