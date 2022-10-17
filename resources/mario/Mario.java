package mario;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Mario implements Runnable {
	// Mario坐标
	private int x;
	private int y;
	// 用字符串描述Mario状态
	private String status;
	// 状态图片
	private BufferedImage showImage;
	// 生命数和分数
	private int score;
	private int life;
	// 移动速度属性
	private int xmove = 0;
	// 跳跃速度属性
	private int ymove = 0;
	// 跳跃上升时间
	private int uptime = 0;
	// Mario的死亡标识
	private boolean isDead = false;
	//定义游戏完成的标记
	private boolean isWin = false;
	// 加入线程
	private Thread t = null;
	// 当前移动中显示的图片索引
	private int moving = 0;
	// 定义一个场景对象，保存当前Mario所在的场景（障碍物的位置）
	private BackGround bg;

	// 构造方法
	public Mario(int x, int y) {
		this.x = x;
		this.y = y;
		// 初始化Mario
		this.showImage = StaticValue.allMarioImage.get(0);
		this.score = 0;
		this.life = 3;
		//创建线程
		t = new Thread(this);
		t.start();
		//Mario初始状态为右站立
		this.status = "right--standing";
	}
	//向左移动的方法
	public void leftMove() {
		// 改变速度
		xmove = -5;
		// 改变状态
		// 如果当前已经是跳跃状态，应该保留原有状态，而不能改变为移动状态
		if (this.status.indexOf("jumping") != -1) {
			this.status = "left--jumping";
		} else {
			this.status = "left--moving";
		}

	}
	//向右移动的方法
	public void rightMove() {
		xmove = 5;
		if (this.status.indexOf("jumping") != -1) {
			this.status = "right--jumping";
		} else {
			this.status = "right--moving";
		}
	}
	//左停止
	public void leftStop() {
		xmove = 0;
		if (this.status.indexOf("jumping") != -1) {
			this.status = "left--jumping";
		} else {
			this.status = "left--standing";
		}
	}
	//右停止
	public void rightStop() {
		xmove = 0;
		if (this.status.indexOf("jumping") != -1) {
			this.status = "right--jumping";
		} else {
			this.status = "right--standing";
		}
	}

	// 跳跃的实现
	public void jump() {
		// 判断是否在跳跃 （没在跳）
		if (this.status.indexOf("jumping") == -1) {
			if (this.status.indexOf("left") != -1) {
				// 往左跳
				this.status = "left--jumping";
			} else {
				// 往右跳
				this.status = "right--jumping";
			}
			ymove = -5;
			uptime = 36;
		}
	}

	// 下落的方法
	public void down() {
		if (this.status.indexOf("left") != -1) {
			// 往左跳
			this.status = "left--jumping";
		} else {
			// 往右跳
			this.status = "right--jumping";
		}
		//下落速度
		ymove = 5;
	}

	// Mario死亡的方法
	public void dead() {
		//死亡一次，生命数减1
		this.life--;
		//生命为0
		if (this.life == 0) {
			this.isDead = true;
			this.status = "right--standing";
		} else {
			//复活厚刷新背景和Mario位置
			this.bg.reset();
			this.x = 0;
			this.y = 420;
		}
	}

	// 为了控制持续移动(坐标一直改变)，使用线程
	public void run() {
		while (true) {
			// 如果Mario移动到旗杆处，则由程序自动控制移动，并且游戏结束。
			if (this.bg.isFlag() && this.x >= 510) {
				this.bg.setOver(true);
				if (this.bg.isDown()) {
					this.status = "right--moving";
					//降旗后，Mario开始向城堡移动
					if(this.x <  570 ) {
						this.x += 5;
					}else {
						if(this.y < 480) {
							this.y += 5;
						}
						this.x += 5;
						//Mario到达城堡，游戏结束
						if(x >= 780) {
							this.isWin = true;
						}
					}
				} else {
					// mario自动落到地面,旗帜也落下来
					if (this.y < 480) {
						// 移动太快是因为没有调用线程的sleep方法，放到else外面就好了
						this.y += 3;
					}
					if (this.y > 444) {
						this.y = 444;
						// 改变Mario落地的状态
						this.status = "right--standing";
					}
				}
			} else {
				// 定义允许移动的标记
				boolean canLeft = true;
				boolean canRight = true;
				// 定义Mario是否处于障碍物上的标记(默认可跳跃)
				boolean onLand = false;
				// 判断当前Mario是否与障碍物碰撞
				for (int i = 0; i < this.bg.getAllObstruction().size(); i++) {
					Obstrution ob = this.bg.getAllObstruction().get(i);
					// 直接用60（砖块的尺寸）判断，有时候很难在两块砖块之间跳上去，且下落会与砖块重合，因此设成50
					// 碰撞不允许向右移动
					if (ob.getX() == this.x + 60 && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
						// 往右碰到隐形砖块直接穿过
						if (ob.getType() != 3 && ob.getType() != 11) {
							canRight = false;
						}
					}
					// 不允许向左移动
					if (ob.getX() == this.x - 60 && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
						if (ob.getType() != 3 && ob.getType() != 11) {
							canLeft = false;
						}
					}
					// 在障碍物上才可以跳跃
					if (ob.getY() == this.y + 60 && (ob.getX() + 60 > this.x && ob.getX() - 60 < this.x)) {
						if (ob.getType() != 3 && ob.getType() != 11) {
							onLand = true;
						}
					}
					// 判断Mario跳跃顶到砖块的情况
					if (ob.getY() == this.y - 60 && (ob.getX() + 50 > this.x && ob.getX() - 50 < this.x)) {
						// 顶到砖块（顶的动）
						if (ob.getType() == 0) {
							// 砖块消失
							this.bg.getAllObstruction().remove(ob);
							// 死后重置地图砖块还在，需要将移除的砖块保存到一个集合removeObstrution里
							this.bg.getRemoveObstrution().add(ob);

						}
						// 顶到问号砖块或隐形砖块
						if ((ob.getType() == 4 || ob.getType() == 3) && uptime > 0) {
							ob.setType(2);
							ob.setImage();
						}
						// 顶到砖块就下落
						uptime = 0;
					}
				}
				
				// 判断Mario与敌人的碰撞的情况
				for (int i = 0; i < this.bg.getAllEnemy().size(); i++) {
					Enemy e = this.bg.getAllEnemy().get(i);
					// 跟敌人的左右碰撞
					if (e.getX() + 50 > this.x && e.getX() - 50 < this.x
							&& (e.getY() - 50 < this.y && e.getY() + 50 > this.y)) {
						this.dead();
					}
					// 跟敌人的上面碰撞(要考虑重合的情况)
					if (e.getY() == this.y + 60 && (e.getX() + 60 > this.x && e.getX() - 60 < this.x)) {
						// 踩到蘑菇怪
						if (e.getType() == 1) {
							e.dead();
							// 踩到蘑菇怪上升高度
							this.uptime = 3;
							// 上升速度
							this.ymove = -5;
							// 踩到食人花
						} else if (e.getType() == 2) {
							this.dead();
						}
					}
				}
				// Mario掉到底死亡
				if (this.y >= 600) {
					this.dead();
				}
				// 下落时Mario状态的判断
				if (onLand && uptime == 0) {
					if (this.status.indexOf("left") != -1) {
						if (xmove != 0) {
							this.status = "left--moving";
						} else {
							// 往左静止下落状态
							this.status = "left--standing";
						}
					} else {
						if (xmove != 0) {
							this.status = "right--moving";
						} else {
							// 往右静止下落状态
							this.status = "right--standing";
						}
					}
				} else {
					// 处于上升状态
					if (uptime != 0) {
						uptime--;
					} else {
						this.down();
					}
					y += ymove;
				}
				// 判断人物能移动且在移动才改变横坐标
				if ((canLeft && xmove < 0) || (canRight && xmove > 0)) {
					x += xmove;
					if (x < 0) {
						x = 0;
					}
				}
			}
			// 定义一个图片取得的索引数
			int temp = 0;
			// 当前为向左（对比字符串，相同是1）
			if (this.status.indexOf("left") != -1) {
				temp += 5;
			}

			// 通过循环显示图片实现走路的动态显示
			// 判断当前是否为移动
			if (this.status.indexOf("moving") != -1) {
				temp += this.moving;
				moving++;
				if (moving == 4) {
					moving = 0;
				}
			}
			// 显示跳跃图片
			if (this.status.indexOf("jumping") != -1) {
				temp += 4;
			}

			// 改变显示移动图片
			this.showImage = StaticValue.allMarioImage.get(temp);

			try {
				// 刷新速度（要跟MyFrame里的线程的sleep一起改）
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}

