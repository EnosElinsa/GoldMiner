package com.sxt;

import java.util.Vector;
import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

/**
 * <p> 动画类 </p>
 * <p> 用于储存和播放动画 </p>
 * <p> 属性 </p>
 * <p> 1.动画的图片集 images: Vector<> </p>
 * <p> 2.当前帧的索引 currentFrameIndex: int </p>
 * <p> 方法 </p>
 * <p> 1.绘制下一帧 drawNextFrame(Graphics g, int x, int y, JPanel panel) </p>
 * <p> 2.获取下一帧 getNextFrame(): Image </p>
 * <p> 3.加载图片集合 loadImage(String directory): void </p>
 * @author Enos
 */

public class Animation {
    //动画图片集合
	public Vector<Image> images;

	//当前帧索引
	private int currentFrameIndex;
	
	//尝试双缓冲的图片
	private Image buffer; 
	private Graphics2D og; //Graphics2D设备可以进行一些图片处理
	
	//初始化
	public Animation(String directory, int numberOfImages) {
		images = new Vector<Image>();
		currentFrameIndex = 0;
		loadImages(directory, numberOfImages);
	}

	/**
	 * @param graphics 当前面板的上下文设备
	 * @param x 动画绘制的x坐标（图片的左上角点）
	 * @param y 动画绘制的y坐标
	 */
	public void drawNextFrame(Graphics graphics, int x, int y) {
		//得到需要绘制的图片
		Image image = this.getNextFrame();
		
		// 绘制图片
		if(image != null) {
			graphics.drawImage(image, x, y, null);
		}
	}
		
    //绘制反向下一帧，这里大致操作和上一个方法相同，只是多了一个把图片对称的操作。
	public void drawFilpFrame(Graphics g, int x, int y, JFrame panel) {
		//获取需要绘制的图片
		Image img = this.getNextFrame();
		
		//双缓冲绘制，获取当前需要绘制的面板
		buffer = panel.createImage(panel.getWidth(), panel.getHeight());
	
		og = (Graphics2D) buffer.getGraphics();
		
		//对图片进行对称变换
		AffineTransform trans = new AffineTransform(); //矩阵
		trans.scale(-1, 1); //根据y轴对称
		trans.translate(-(2 * x+ img.getWidth(null)), 0); //因为是整个画布移动所以要平移回去
		og.setTransform(trans);
		
	
		if(img != null) {
			og.drawImage(img, x, y, panel);
			g.drawImage(buffer, 0, 0, null);
		}
	}

	//获取下一帧
	private Image getNextFrame() {
		currentFrameIndex = (currentFrameIndex + 1) % images.size();
		return (Image)images.elementAt(currentFrameIndex);
	}
	
	/**
	 * 加载图片集合
	 * @param path 图片集的文件夹路径
	 * @param numberOfImages 图片个数
	 */
	private void loadImages(String path, int numberOfImages) {
		for(int index = 0; index < numberOfImages; index++) {
			Image image = Toolkit.getDefaultToolkit().getImage(path + "-" + index);
			images.add(image);
		}
	}
}
