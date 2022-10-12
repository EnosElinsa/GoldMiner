package com.sxt;

import java.util.Vector;

import javax.swing.ImageIcon;

import java.awt.*;

/**
 * <p> 动画类 </p>
 * <p> 用于储存和播放动画 </p>
 * <p> 属性 </p>
 * <p> 1.动画的图片集 images: Vector<> </p>
 * <p> 2.当前帧的索引 currentFrameIndex: int </p>
 * <p> 方法 </p>
 * <p> 1.获取下一帧 getNextFrame(): Image </p>
 * <p> 2.加载图片集合 loadImage(String directory): void </p>
 * @author Enos
 */

public class Animation {
    //动画图片集合
	public Vector<Image> images;

	//当前帧索引
	private int currentFrameIndex;
	
	//初始化
	public Animation(String directory, int numberOfImages) {
		images = new Vector<Image>();
		currentFrameIndex = -1;
		loadImages(directory, numberOfImages);
	}

	//获取下一帧
	public Image getNextFrame() {
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
			Image image = new ImageIcon(path + "-" + index + ".png").getImage();
			images.add(image);
		}
	}

	public int getCurrentFrameIndex() {
		return currentFrameIndex;
	}

	public void setCurrentFrameIndex(int currentFrameIndex) {
		this.currentFrameIndex = currentFrameIndex;
	}

	
}
