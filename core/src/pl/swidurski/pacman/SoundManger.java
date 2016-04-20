package pl.swidurski.pacman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import pl.swidurski.pacman.game.GameScreen;
import pl.swidurski.pacman.game.MainMenu;

/**
 * Created by Krystek on 2016-04-19.
 */
public class SoundManger {

    private static Game game;
    private static Sound intro;
    private static Sound death;
    private static Sound chomp;
    private static Sound eatghost;

    public static void setGame(Pacman game) {
        SoundManger.game = game;
        loadSounds();
    }

    private static void loadSounds(){
        intro = Gdx.audio.newSound(Gdx.files.internal(Const.SOUNDS_INTRO));
        death = Gdx.audio.newSound(Gdx.files.internal(Const.SOUNDS_DEATH));
        chomp = Gdx.audio.newSound(Gdx.files.internal(Const.SOUNDS_CHOMP));
        eatghost = Gdx.audio.newSound(Gdx.files.internal(Const.SOUNDS_EATGHOST));
    }

    public static void playIntro(){
        intro.play();
    }

    public static void playDeath(){
        death.play();
    }

    public static void playChomp(){
        chomp.play();
    }
    public static void playEatGhost(){
        eatghost.play();
    }

    public static void dispose(){
        intro.dispose();
        death.dispose();
        chomp.dispose();
        eatghost.dispose();
    }
}
