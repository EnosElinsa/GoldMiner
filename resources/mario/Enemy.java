package mario;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Enemy implements Runnable {
	// 障碍物坐标
	private int x;
	private int y;
	// 食人花初始坐标
	private int startX;
	private int startY;
	// 图片类型
	private int type;
	// 显示图片
	private BufferedImage showImage;
	// 移动方向
	private boolean isLeftOrUp = true;
	// 移动的极限范围
	private int upMax = 0;
	private int downMax = 0;
	//控制敌人开始移动的线程
	private Thread t2 = new Thread(this);
	// 定义图片状态
	private int imageType = 0;
	//背景
	private BackGround bg;

	// 构造栗子怪
	public Enemy(int x, int y, boolean isLeft, int type, BackGround bg) {
		this.x = x;
		this.y = y;
		this.startX = x;
		this.startY = y;
		this.isLeftOrUp = isLeft;
		this.type = type;
		this.bg = bg;
		if (type == 1) {
			this.showImage = StaticValue.allTriangleImage.get(0);
		}
		t2.start();
		// 线程不能立即启动，要等按下空格开始才启动
		t2.suspend();
	}

	// 构造敌人
	public Enemy(int x, int y, boolean isUp, int type, int upMax, int downMax, BackGround bg) {
		//初始化属性
		this.x = x;
		this.y = y;
		this.startX = x;
		this.startY = y;
		this.isLeftOrUp = isUp;
		this.type = type;
		this.upMax = upMax;
		this.downMax = downMax;
		this.bg = bg;
		if (type == 2) {
			this.showImage = StaticValue.allFlowerImage.get(0);
		}
		//启动/挂起线程
		t2.start();
		t2.suspend();
	}
	
	public void run() {
		while (true) {
			// 蘑菇怪左右移动
			if (type == 1) {
				if (this.isLeftOrUp) {
					// 移动速度
					this.x -= 2;
				} else {
					this.x += 2;
				}
				// 变换图片，形成走路的效果
				if (imageType == 0) {
					imageType = 1;
				} else {
					imageType = 0;
				}
				//定义标记
				boolean canLeft = true;
				boolean canRight = true;
				boolean onLand = false;

				// 判断当前敌人是否与障碍物碰撞
				for (int i = 0; i < this.bg.getAllObstruction().size(); i++) {
					Obstrution ob = this.bg.getAllObstruction().get(i);
					// 碰撞不允许向右移动
					if (ob.getX() == this.x + 60 && (ob.getY() - 50 < this.y && ob.getY() + 50 > this.y)) {
						canRight = false;
					}
					// 不允许向左移动
					if (ob.getX() == this.x - 60 && (ob.getY() - 50 < this.y && ob.getY() + 50 > this.y)) {
						canLeft = false;
					}
				}
				// 敌人碰到障碍物自动转向
				if (this.isLeftOrUp && !canLeft || this.x == 0) {
					this.isLeftOrUp = false;
				} else if (this.isLeftOrUp && !canRight || this.x == 840) {
					this.isLeftOrUp = true;
				}
				//改变转向后敌人图片
				this.showImage = StaticValue.allTriangleImage.get(imageType);
			}
			// 食人花上下移动
			if (type == 2) {
				if (this.isLeftOrUp) {
					this.y -= 2;
				} else {
					this.y += 2;
				}
				if (imageType == 0) {
					imageType = 1;
				} else {
					imageType = 0;
				}
				if (this.isLeftOrUp && this.y == this.upMax) {
					this.isLeftOrUp = false;
				}
				if (!this.isLeftOrUp && this.y == this.downMax) {
					this.isLeftOrUp = true;
				}
				//改变食人花图片
				this.showImage = StaticValue.allFlowerImage.get(imageType);
			}
			// 同步移动速度
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	//重置敌人的方法
	public void reset() {
		// 将坐标重置
		this.x = this.startX;
		this.y = this.startY;
		// 将显示图片重置
		if (this.type == 1) {
			this.showImage = StaticValue.allTriangleImage.get(0);
		} else if (this.type == 2) {
			this.showImage = StaticValue.allFlowerImage.get(0);
			this.isLeftOrUp = true;
		}
	}

	// 敌人死亡
	public void dead() {
		// 显示死亡图片
		this.showImage = StaticValue.allTriangleImage.get(2);
		// 敌人消失
		this.bg.getAllEnemy().remove(this);
		// 重置回来
		this.bg.getRemoveEnemy().add(this);
	}

	// 敌人开局开始移动(打开线程)
	public void startMove() {
		t2.resume();
	}
}

