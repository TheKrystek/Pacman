package pl.swidurski.pacman.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Krystek on 2016-04-09.
 */
public enum MenuOptions {

    PLAY("menu.play", new Vector2(400, 500)),
    EXIT("menu.exit", new Vector2(400, 450));

    private final Vector2 position;
    private String translationKey;

    MenuOptions(String translationKey, Vector2 position) {
        this.translationKey = translationKey;
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public String getTranslationKey() {
        return translationKey;
    }


}
