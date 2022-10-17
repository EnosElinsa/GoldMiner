package mario;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BackGround {
	// 当前场景的显示图片
	private BufferedImage bgImage = null;
	// 场景的循序
	private int sort;
	// 当前是否为最后一个场景
	private boolean flag;
	// 定义游戏结束的标记
	private boolean isOver = false;
	// 定义降旗结束的标记
	private boolean isDown = false;
	//是否发射子弹的标记
	public boolean isShoot = false;
	// 全部的敌人
	private List<Enemy> allEnemy = new ArrayList<Enemy>();
	// 全部的障碍物
	private List<Obstrution> allObstruction = new ArrayList<Obstrution>();
	// 被消灭的敌人
	private List<Enemy> removeEnemy = new ArrayList<Enemy>();
	// 被消灭障碍物
	private List<Obstrution> removeObstrution = new ArrayList<Obstrution>();

	// 构造方法
	public BackGround(int sort, boolean flag) {
		this.sort = sort;
		this.flag = flag;
		if (flag) {
			bgImage = StaticValue.endImage;
		} else {
			bgImage = StaticValue.bgImage;
		}
		// 第一个场景的地图配置
		if (sort == 1) {
			for (int i = 0; i < 15; i++) {
				// 绘制地面
				this.allObstruction.add(new Obstrution(i * 60, 540, 9, this));
			}
			// 绘制蘑菇
			// this.allEnemy.add(new Enemy(535, 360, true, 2, 485, 535, this));
			// 绘制砖块
			this.allObstruction.add(new Obstrution(120, 360, 4, this));
			this.allObstruction.add(new Obstrution(300, 360, 0, this));
			this.allObstruction.add(new Obstrution(360, 360, 4, this));
			this.allObstruction.add(new Obstrution(420, 360, 0, this));
			this.allObstruction.add(new Obstrution(480, 360, 4, this));
			this.allObstruction.add(new Obstrution(540, 360, 0, this));
			this.allObstruction.add(new Obstrution(420, 180, 4, this));
			// 绘制水管
			this.allObstruction.add(new Obstrution(660, 540, 6, this));
			this.allObstruction.add(new Obstrution(720, 540, 5, this));
			this.allObstruction.add(new Obstrution(660, 480, 8, this));
			this.allObstruction.add(new Obstrution(720, 480, 7, this));
			// 绘制隐的砖块
			this.allObstruction.add(new Obstrution(660, 300, 3, this));
			// 绘制栗子怪
			this.allEnemy.add(new Enemy(600, 480, true, 1, this));
			// 绘制食人花
			this.allEnemy.add(new Enemy(690, 480, true, 2, 420, 540, this));
		}
		// 第二个场景的配置
		if (sort == 2) {
			for (int i = 0; i < 15; i++) {
				if (i != 10 && i != 11) {
					this.allObstruction.add(new Obstrution(i * 60, 540, 9, this));
				}
			}
			// 绘制第一根水管
			this.allObstruction.add(new Obstrution(60, 540, 6, this));
			this.allObstruction.add(new Obstrution(120, 540, 5, this));
			this.allObstruction.add(new Obstrution(60, 480, 6, this));
			this.allObstruction.add(new Obstrution(120, 480, 5, this));
			this.allObstruction.add(new Obstrution(60, 420, 8, this));
			this.allObstruction.add(new Obstrution(120, 420, 7, this));
			// 绘制第二根水管
			this.allObstruction.add(new Obstrution(240, 540, 6, this));
			this.allObstruction.add(new Obstrution(300, 540, 5, this));
			this.allObstruction.add(new Obstrution(240, 480, 6, this));
			this.allObstruction.add(new Obstrution(300, 480, 5, this));
			this.allObstruction.add(new Obstrution(240, 420, 6, this));
			this.allObstruction.add(new Obstrution(300, 420, 5, this));
			this.allObstruction.add(new Obstrution(240, 360, 8, this));
			this.allObstruction.add(new Obstrution(300, 360, 7, this));
			// 绘制栗子怪
			this.allEnemy.add(new Enemy(600, 480, true, 1, this));
		}
		//第三个场景
		if (sort == 3) {
			for (int i = 0; i < 15; i++) {
				this.allObstruction.add(new Obstrution(i * 60, 540, 9, this));
			}
			// 绘制旗帜
			this.allObstruction.add(new Obstrution(550, 190, 11, this));
			this.allObstruction.add(new Obstrution(535, 505, 12, this));
			//绘制障碍物
			this.allObstruction.add(new Obstrution(360, 480, 1, this));
			this.allObstruction.add(new Obstrution(360, 420, 1, this));
			this.allObstruction.add(new Obstrution(360, 360, 1, this));
			this.allObstruction.add(new Obstrution(300, 480, 1, this));
			this.allObstruction.add(new Obstrution(300, 420, 1, this));
			this.allObstruction.add(new Obstrution(240, 480, 1, this));
		}
	}

	// 重置方法,Mario复活后将所有的障碍物和敌人放回到原有坐标，并将其状态也修改
	public void reset() {
		// 放回障碍物和敌人
		this.allEnemy.addAll(this.removeEnemy);
		this.allObstruction.addAll(this.removeObstrution);
		// 重置敌人和障碍物
		for (int i = 0; i < this.allEnemy.size(); i++) {
			this.allEnemy.get(i).reset();
		}
		for (int i = 0; i < this.allObstruction.size(); i++) {
			this.allObstruction.get(i).reset();
		}
	}

	// 使此场景的每个敌人都开始移动
	public void enemyStartMove() {
		for (int i = 0; i < this.allEnemy.size(); i++) {
			this.allEnemy.get(i).startMove();
		}
	}
}

