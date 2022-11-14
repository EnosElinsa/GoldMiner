package gamebody.ui;

import gamebody.engine.Audio;
import gamebody.engine.GameObject;
import gamebody.engine.ObjectValueLevel;
import gamebody.scenes.body.GameWindow;

import javax.swing.*;
import java.awt.*;

/**
 * <p>游戏UI类。
 * <p>绘制游戏的基本状态信息记录，包括当前的金钱值、目标金钱值、时间、关卡数、炸药的数量。
 *
 * @author JiajiaPig
 * @author Enos
 * @see GameWindow
 */

public class UI extends GameObject {

    /**
     * {@code GameWindow}游戏窗口主体的引用，用于获取游戏场景下的其他游戏物体。
     */
    private GameWindow gameWindow;

    /**
     * 第一种加分音效。
     */
    private Audio addValueSound = new Audio("sound/sound_wav/score1.wav");

    /**
     * 第二种加分音效。
     */
    private Audio addValueSound2 = new Audio("sound/sound_wav/score2.wav");

    /**
     * 炸药图片的路径。
     */
    private static final String TEXTURE_DIRECTORY = "resources/dynamite.png";

    /**
     * 炸药图标。
     */
    private Image dynamiteIcon= new ImageIcon(TEXTURE_DIRECTORY).getImage();

    /**
     * 唯一的构造方法
     * @param gameWindow {@code GameWindow}游戏窗口主体的引用，用于获取游戏场景下的其他游戏物体
     */
    public UI(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    /**
     * 绘制字体的方法。
     * @param graphics 绘制物体所需的画面上下文信息
     * @param size 大小
     * @param color 函数
     * @param str 内容
     * @param x 位置横坐标
     * @param y 位置纵坐标
     */
    private static void drawWords(Graphics graphics, int size, Color color, String str, int x, int y) {
        graphics.setColor(color);
        graphics.setFont(new Font("等线", Font.BOLD, size));
        graphics.drawString(str, x, y);
    }

    @Override
    protected void update() {}

    @Override
    public void render(Graphics graphics, JPanel panel) {
        // 画炸药。
        graphics.drawImage(dynamiteIcon,620,70,null);
        // 画炸药的数量。
        int dynamiteCount= gameWindow.getDynamiteCount();
        String words0="x"+dynamiteCount;
        drawWords(graphics,26, new Color(239,67,27),words0,640,95);
        // 画文字（金钱、目标、时间、关卡）。
        String words1 = "金钱";
        drawWords(graphics,30, Color.WHITE, words1,50,75);

        //文字“目标”。
        String words2 = "目标";
        drawWords(graphics,32, Color.WHITE, words2,48,125);

        // 已得金钱值。
        int curValue = gameWindow.getRope().getOverallValue();
        String words3 = "$ " + Integer.toString(curValue);
        drawWords(graphics,32, new Color(17,155,56), words3,180,75);

        // 目标金钱值。
        String words4 = Integer.toString(gameWindow.getTarget());
        drawWords(graphics,32, new Color(144,61,161), words4,180,125);

        // 文字“时间”。
        String words5 = "时间";
        drawWords(graphics,32, Color.WHITE, words5,750,75);

        // 文字“关卡”。
        String words6 = "关卡";
        drawWords(graphics,32, Color.WHITE, words6,750,128);
        drawWords(graphics,32, new Color(239,67,27), Integer.toString(gameWindow.getLevel()),850,128);

        // 绘制倒计时。
        long curTime = gameWindow.getTime().countDown();
        if (curTime >= 0) {
            String words7 = "" + curTime;
            drawWords(graphics,32,new Color(239,67,27),words7,850,75);
        }

        // 抓取成功后的加分效果。
        if (gameWindow.getRope().isRetrieved() == true) {
            // 将rope是否正在收回设置为false。
            gameWindow.getRope().setRetrieved(false);
            // 本次抓取增加的分数。
            int addValue = gameWindow.getRope().getGrabValue();
            // 如果碰撞检测成功且抓取到的物体的价值是高价值物体，则播放抓取高价值物体的加分音效。
            if (gameWindow.getRope().getCollidingObject() != null && gameWindow.getRope().getCollidingObject().getObjectValueLevel() == ObjectValueLevel.HIGH) {
                addValueSound.play(1);
            } else {
                // 否则播放抓取其它非高价值物体的加分音效。
                addValueSound2.play(1);
            }
            String words8 = "+" + Integer.toString(addValue);
            drawWords(graphics,30,Color.GREEN,words8,300,75);
        }
    }
}
