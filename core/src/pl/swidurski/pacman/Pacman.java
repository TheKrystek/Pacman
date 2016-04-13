package pl.swidurski.pacman;

import com.badlogic.gdx.Game;
import pl.swidurski.pacman.game.GameScreen;

public class Pacman extends Game {
    @Override
    public void create() {
        //setScreen(new MainMenu());
        setScreen(new GameScreen());
    }
}
