package gamebody.main;

import gamebody.scenes.body.GameWindow;

/**
 * 启动游戏入口
 */
public class GameLauncher {
    /**
     * 主方法。
     * @param args
     */
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.launch();
    }
}
