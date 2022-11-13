package gamebody.main;

import gamebody.scenes.GameWindow;

/**
 * 启动游戏入口
 */
public class GameLauncher {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.launch();
    }
}
