package gamebody.engine;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * <p>动画类 {@code Animation} 用于储存和播放动画 。
 * <p><strong>当前设计只支持png格式，且图片的命名的格式应该为：描述-序号.png （序号必须以0开始，且同一种动画的描述应该一致）。</strong>
 * <p>属性 
 * <p>1.动画的图片集 {@code images}
 * <p>2.当前帧的索引 {@code currentFrameIndex}
 * <p>方法
 * <p>1.获取下一帧图片 {@code getNextFrame()}
 * @author Enos
 */
public class Animation {
    /**
	 * 动画图片集合。
	 */
	private Vector<Image> images;

	/**
	 * 当前帧在动画图片集合{@code images}的索引。
	 */
	private int currentFrameIndex;
	
	/**
	 * 动画类的唯一构造方法。
	 * @param directory 目录要求为：父路径+动画图片的描述 <p>如：resources/tnt-explosion
	 * @param numberOfImages 动画图片的个数
	 */
	public Animation(String directory, int numberOfImages) {
		images = new Vector<Image>();
		currentFrameIndex = -1;
		loadImages(directory, numberOfImages);
	}

	/**
	 * 根据{@code currentFrameIndex}获取当前动画图片集合{@code images}的下一帧。
	 * @return 获取的动画的下一帧
	 */
	public Image getNextFrame() {
		currentFrameIndex = (currentFrameIndex + 1) % images.size();
		return (Image)images.elementAt(currentFrameIndex);
	}
	
	/**
	 * 加载图片集合。
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

	public Vector<Image> getImages() {
		return images;
	}
}
