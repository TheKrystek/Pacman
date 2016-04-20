package pl.swidurski.pacman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import pl.swidurski.pacman.game.GameScreen;
import pl.swidurski.pacman.game.MainMenu;

/**
 * Created by Krystek on 2016-04-19.
 */
public class ScreenManger {

    private static Game game;

    public static void setGame(Pacman game) {
        ScreenManger.game = game;
    }

    public static void showMainMenu() {
        game.setScreen(new MainMenu());
    }

    public static void showGameWindow() {
        game.setScreen(new GameScreen(true));
    }

    public static void exitGame() {
        Gdx.app.exit();
    }

    public static void showTopScoreWindow() {
    }

    public static void nextLevel() {
        game.setScreen(new GameScreen());
    }
}
