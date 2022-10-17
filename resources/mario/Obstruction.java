package mario;

public class Obstrution implements Runnable{
	//坐标
	private int x;
	private int y;
	//图片的类型
	private int type;
	// 初始的类型
	private int startType;
	// 显示图片
	private BufferedImage showImage = null;
	//当前障碍物所在的背景
	private BackGround bg;	
	//创建线程控制旗帜降落
	private Thread t = new Thread(this);

	// 构造方法（障碍物对象的初始化）
	public Obstrution(int x, int y, int type ,BackGround bg) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.startType = type;
		this.bg = bg;
		setImage();
		//碰到旗帜的时候启动线程
		if(this.type == 11) {
			t.start();
		}
	}

	// 重置方法
	public void reset() {
		// 修改为初始类型
		this.type = startType;
		// 改变显示图片
		this.setImage();
	}

	// 根据类型显示图片
	public void setImage() {
		showImage = StaticValue.allObstructionImage.get(type);
	}
	
	//线程控制旗帜的降落
	public void run() {
		while(true){
			if(this.bg.isOver()) {
				if(this.y < 444) {
					this.y += 5;
				}else {
					this.bg.setDown(true);
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

