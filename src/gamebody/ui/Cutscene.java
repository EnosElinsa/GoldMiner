package gamebody.ui;

import gamebody.scenes.GameWindow;

import javax.swing.*;
import java.awt.*;

public class Cutscene extends JPanel {

    private JLabel jLabelGoalScore = new JLabel();

    //构造方法（传入状态码Status，0表示失败，1表示过关，其他数字表示下一关的目标分数）
    public Cutscene(int status)  {
        setLayout(null);
        //初始化文字
        initText(status);
        //初始化静态背景
        initBackground();
        //设置窗口可见
        setVisible(true);
    }

    //初始化静态背景
    private void initBackground() {
        //绘制文本框
        JLabel jLabelBox = new JLabel(new ImageIcon("resources\\cutscenes\\cut-scene-banner.png"));
        jLabelBox.setBounds(73, 150, 800, 363);
        add(jLabelBox);

        //绘制标题
        JLabel jLabelTitle = new JLabel(new ImageIcon("resources\\cutscenes\\gold-miner-text.png"));
        jLabelTitle.setBounds(150, 10, 700, 104);
        add(jLabelTitle);

        //绘制背景
        JLabel jLabelBackground = new JLabel(new ImageIcon("resources\\cutscenes\\cut-scene-background.png"));
        jLabelBackground.setBounds(-3, -20, GameWindow.INIT_WIDTH, GameWindow.INIT_HEIGHT);
        add(jLabelBackground);
    }

    //初始化文字
    private void initText(int status) {
        //设置字体
        Font font = new Font("宋体", Font.BOLD, 50);

        //根据状态码绘制不同文字（状态码Status，0表示失败，1表示过关，2表示进入下一关）
        if (status == 0) {
            //绘制文字
            JLabel jLabel = new JLabel("未达到目标");
            jLabel.setFont(font);
            jLabel.setForeground(Color.white);
            jLabel.setBounds(340, 250, 1000, 50);
            add(jLabel);
        } else if (status == 1) {
            //绘制文字
            JLabel jLabel = new JLabel("完成任务进入下一关");
            jLabel.setFont(font);
            jLabel.setForeground(Color.white);
            jLabel.setBounds(240, 280, 1000, 50);
            add(jLabel);
        } else if (status == 2) {
            //绘制文字
            JLabel jLabel = new JLabel("下个目标为");
            jLabel.setFont(font);
            jLabel.setForeground(Color.white);
            jLabel.setBounds(340, 250, 1000, 50);
            add(jLabel);
            //绘制目标分数
            jLabelGoalScore.setFont(font);
            jLabelGoalScore.setForeground(Color.green);
            add(jLabelGoalScore); 
        }
    }

    public void setGoalScore(int goalScore) {
        jLabelGoalScore.setText("" + goalScore);
        jLabelGoalScore.setBounds(460 - 15 * (int) Math.log10(goalScore), 350, 200, 50);
    }
}
