package mario;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MyFrame extends JFrame implements KeyListener, Runnable {
	// 存放背景的集合
	private List<BackGround> allBG = new ArrayList<BackGround>();
	private BackGround nowBG = null;
	private Mario mario = null;
	// 是否开始游戏的标记
	private boolean isStart = false;
	//人物移动刷新地图的线程
	private Thread t1 = new Thread(this);
	
	public static void main(String[] args) {
		new MyFrame();
	}

	public MyFrame() {
		//定义窗体的尺寸
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		setSize(900, 600);
		setTitle("超級马里奥");
		setLocation((width - 900) / 2, (height - 600) / 2);
		setDefaultCloseOperation(3);
		setResizable(false);

		// 初始化全部的图片
		StaticValue.init();
		// 创建全部的场景(3个)
		for (int i = 1; i <= 3; i++) {
			this.allBG.add(new BackGround(i, i == 3 ? true : false));
		}
		// 将第一个场景设置为当前场景
		this.nowBG = this.allBG.get(0);
		// Mario的初始化
		this.mario = new Mario(0, 480);
		// 将场景放入Mario对象的属性中
		this.mario.setBg(nowBG);
		//重绘
		this.repaint();
		this.addKeyListener(this);
		//开始线程
		t1.start();
		setVisible(true);
	}
	//画笔
	public void paint(Graphics g) {
		// 建立临时缓冲图片 （红绿蓝三原色类型）
		BufferedImage image = new BufferedImage(900, 600, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g2 = image.getGraphics();
		
		if (this.isStart) {
			// 绘制背景
			g2.drawImage(this.nowBG.getBgImage(), 0, 0, this);
			g2.setFont(new Font("微软雅黑", Font.BOLD, 15));
			g2.drawString("生命 : " + this.mario.getLife(), 60, 60);
			g2.drawString("分数 : " + this.mario.getScore(), 500, 60);
			// 添加敌人
			Iterator<Enemy> iterEnemy = this.nowBG.getAllEnemy().iterator();
			//遍历画出敌人，如果有下一个
			while (iterEnemy.hasNext()) {
				Enemy e = iterEnemy.next();
				g2.drawImage(e.getShowImage(), e.getX(), e.getY(), this);
			}
			// 添加障碍物
			Iterator<Obstrution> iterator = this.nowBG.getAllObstruction().iterator();
			while (iterator.hasNext()) {
				Obstrution obstrution = iterator.next();
				g2.drawImage(obstrution.getShowImage(), obstrution.getX(), obstrution.getY(), this);
			}
			// 添加Mario
			g2.drawImage(this.mario.getShowImage(), this.mario.getX(), this.mario.getY(), this);
		} else {
			g2.drawImage(StaticValue.startImage, 0, 0, this);
		}
		// 把缓冲图片绘制到窗体中
		g.drawImage(image, 0, 0, this);
	}

	// 上：38 下：40 左：37 右：39 空格（跳跃）：32
	// 按下按键则持续移动，释放停止移动
	@Override
	public void keyPressed(KeyEvent e) {
		//按下空格之前不能实现按键功能
		if (this.isStart) {
			if (e.getKeyCode() == 39) {
				this.mario.rightMove();
			}
			if (e.getKeyCode() == 37) {
				this.mario.leftMove();
			}
			if (e.getKeyCode() == 32) {
				this.mario.jump();
			}
		} else {
			//按下空格才可以开始游戏
			if (e.getKeyCode() == 32) {
				this.isStart = true;
				this.nowBG.enemyStartMove();
			}
		}
	}
	//释放按键
	public void keyReleased(KeyEvent e) {
		if (this.isStart) {
			if (e.getKeyCode() == 39) {
				this.mario.rightStop();
			}
			if (e.getKeyCode() == 37) {
				this.mario.leftStop();
			}
		}
	}
	// 人物移动一下，刷新一次地图
	public void run() {
		while (true) {
			//刷新地图
			this.repaint();
			try {
				//Mario线程速度
				Thread.sleep(30);
				if (this.mario.getX() >= 840) {
					// 切换场景，改变Mario状态
					this.nowBG = this.allBG.get(this.nowBG.getSort());
					this.mario.setBg(this.nowBG);
					//Mario在下一个的位置
					this.mario.setX(0);
					//此处场景的敌人开始移动
					this.nowBG.enemyStartMove();
				}
				//Mario死亡后在界面上的变化
				if (this.mario.isDead()) {
					JOptionPane.showMessageDialog(this, "Game Over ！");
					this.mario.setDead(false);
					this.isStart = false;
					int result = JOptionPane.showConfirmDialog(this, "是否重新开始游戏？");
					if(result == 0) {
						this.mario.setLife(3);
						this.mario.setScore(0);
					}
				}
				//Mario通关时界面的变化
				if(this.mario.isWin()) {
					JOptionPane.showMessageDialog(this, "恭喜你，游戏通关！");
					System.exit(0);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

