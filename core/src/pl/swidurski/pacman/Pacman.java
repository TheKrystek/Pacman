package pl.swidurski.pacman;

import com.badlogic.gdx.Game;

public class Pacman extends Game {
    @Override
    public void create() {
        SoundManger.setGame(this);
        ScreenManger.setGame(this);
        ScreenManger.showMainMenu();
    }

    @Override
    public void dispose() {
        super.dispose();
        SoundManger.dispose();
    }
}
