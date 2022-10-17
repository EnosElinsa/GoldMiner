package mario;

public class StaticValue {
	//全部的Mario状态图片
	public static List<BufferedImage> allMarioImage = new ArrayList<BufferedImage>();
	//开局背景图片
	public static BufferedImage startImage = null;
	//最后一关背景图片
	public static BufferedImage endImage = null;
	//普通背景图片
	public static BufferedImage bgImage = null;
	//全部的食人花图片（开，合）
	public static List<BufferedImage> allFlowerImage = new ArrayList<BufferedImage>();
	//全部的栗子怪图片（抬左脚，抬右脚）
	public static List<BufferedImage> allTriangleImage = new ArrayList<BufferedImage>();
	//全部的乌龟图片（左/右伸脚，左/右收脚，龟壳）
	public static List<BufferedImage> allTurtleImage = new ArrayList<BufferedImage>();
	//全部的障碍物（各种砖块）
	public static List<BufferedImage> allObstructionImage = new ArrayList<BufferedImage>();
	//Mario死亡图片
	public static BufferedImage marioDeadImage = null;
	// System.getProperty("user.dir")返回当前项目的路径
	
	// 图片初始化
	public static void init() {
		// 导入Mario的图片
		for (int i = 1; i <= 10; i++) {
			try {
				allMarioImage.add(ImageIO.read(new File("images/"+ i + ".gif")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 导入背景图片
		try {
			startImage = ImageIO.read(new File("images/"+"start.gif"));
			bgImage = ImageIO.read(new File("images/"+ "firststage.gif"));
			endImage = ImageIO.read(new File("images/"+ "firststageend.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 导入所有敌人的图片
		for (int i = 1; i <= 5; i++) {
			try {
				if (i <= 2) {
					allFlowerImage.add(ImageIO.read(new File("images/"+ "flower" + i + ".gif")));
				}
				if (i <= 3) {
					allTriangleImage.add(ImageIO.read(new File( "images/"+"triangle" + i + ".gif")));
				}
				allTurtleImage.add(ImageIO.read(new File("images/"+ "Turtle" + i + ".gif")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 导入所有障碍物的图片
		for(int i=1;i<=14;i++) {
			try {
				allObstructionImage.add(ImageIO.read(new File("images/"+ "ob" + i + ".gif")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			//导入Mario死亡的图片
			marioDeadImage = ImageIO.read(new File("images/over.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

