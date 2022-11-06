package test;

import javax.swing.*;
import java.awt.*;

public class CutscenesJFrame extends JFrame {
    public static final int INIT_WIDTH = 960; // 默认窗口宽度960px
    public static final int INIT_HEIGHT = 540 + 124 + 30; // 默认窗口高度540px(关卡背景高度)+124px(陆地背景高度)+30px(填充)
    int goalScores = 8888;//目标分数


    //构造方法（传入状态码Status，0表示失败，1表示过关，其他数字表示下一关的目标分数）
    public CutscenesJFrame(int status)  {
        //初始化窗口
        initJFrame();
        //初始化文字
        initText(status);
        //初始化静态背景
        initBackground();
        //设置窗口可见
        this.setVisible(true);
    }

    //初始化静态背景
    private void initBackground() {
        //绘制文本框
        JLabel jLabelBox = new JLabel(new ImageIcon("resources\\cutscenes\\cut-scene-banner.png"));
        jLabelBox.setBounds(73, 150, 800, 363);
        this.getContentPane().add(jLabelBox);
        //绘制标题
        JLabel jLabelTitle = new JLabel(new ImageIcon("resources\\cutscenes\\gold-miner-text.png"));
        jLabelTitle.setBounds(150, 10, 700, 104);
        this.getContentPane().add(jLabelTitle);
        //绘制背景
        JLabel jLabelBackground = new JLabel(new ImageIcon("resources\\cutscenes\\cut-scene-background.png"));
        jLabelBackground.setBounds(-3, -20, INIT_WIDTH, INIT_HEIGHT);
        this.getContentPane().add(jLabelBackground);
    }

    //初始化窗口
    private void initJFrame() {
        //设置窗口宽高
        this.setSize(INIT_WIDTH, INIT_HEIGHT);
        //设置窗口标题
        this.setTitle("过场界面测试");
        //设置窗口置顶
        this.setAlwaysOnTop(true);
        //设置窗口居中
        this.setLocationRelativeTo(null);
        //设置窗口默认关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消隐藏容器的默认居中
        this.setLayout(null);
    }

    //初始化文字
    private void initText(int status) {
        //设置字体
        Font f = new Font("宋体", Font.BOLD, 50);

        //根据状态码绘制不同文字（状态码Status，0表示失败，1表示过关，2表示进入下一关）
        if (status == 0) {
            //绘制文字
            JLabel jLabelTest = new JLabel("未达到目标");
            jLabelTest.setFont(f);
            jLabelTest.setForeground(Color.white);
            jLabelTest.setBounds(340, 250, 1000, 50);
            this.getContentPane().add(jLabelTest);
        } else if (status == 1) {
            //绘制文字
            JLabel jLabelTest = new JLabel("完成任务进入下一关");
            jLabelTest.setFont(f);
            jLabelTest.setForeground(Color.white);
            jLabelTest.setBounds(240, 280, 1000, 50);
            this.getContentPane().add(jLabelTest);
        } else if (status == 2) {
            //绘制文字
            JLabel jLabelTest = new JLabel("下个目标为");
            jLabelTest.setFont(f);
            jLabelTest.setForeground(Color.white);
            jLabelTest.setBounds(340, 250, 1000, 50);
            this.getContentPane().add(jLabelTest);
            //绘制目标分数
            JLabel jLabelGoalScores = new JLabel("" + goalScores);
            jLabelGoalScores.setFont(f);
            jLabelGoalScores.setForeground(Color.green);
            jLabelGoalScores.setBounds(460 - 15 * (int) Math.log10(goalScores), 350, 200, 50);
            this.getContentPane().add(jLabelGoalScores);
        }
    }
}
