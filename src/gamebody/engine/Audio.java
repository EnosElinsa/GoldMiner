package gamebody.engine;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>音效类 {@code Audio} 用于保存和播放游戏中的音效及背景音乐。
 * <p>为了实现的方便，该音效类的实现用到了从JDK9开始弃用的{@code Applet}。
 * @author JiajiaPig
 */
@SuppressWarnings("deprecation")
public class Audio {
    
    /**
     * 要播放的音效片段。
     */
    private AudioClip aau;

    /**
     * 音效文件所在的文件路径。
     */
    private String url;


    /**
     * 构造音效类 {@code Audio} 的唯一构造方法。
     * @param url 音效文件所在的文件路径
     */
    public Audio(String url) {
        this.url = url;
        initialize();
    }
    
    /**
     * 读取音效文件构造{@code aau}。
     */
    private void initialize() {
        try {
            URL cb;
            // 可以在项目里创建一个Source folder包，将音乐文件放到这个包里，再把路径给它。
            File f = new File(url); 
            cb = f.toURL();
            aau = Applet.newAudioClip(cb);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 控制音效的播放以及停止
     * @param playMode 播放模式 
     * <p>{@code 1} 开始播放（只播放一次）
     * <p>{@code 2} 停止正在播放的音乐片段
     * <p>{@code 3} 循环播放
     */
    public void play(int playMode) {
        switch (playMode) {
            case 1: aau.play(); break;
            case 2: aau.stop(); break;
            case 3: aau.loop(); break;
            default: break;
        }
    }
}