package pl.swidurski.pacman.menu.main;

/**
 * Created by Krystek on 2016-04-09.
 */
public enum MenuOptions {

    PLAY("menu.play"),
    TOP_SCORE("menu.topScore"),
    EXIT("menu.exit");

    private String translationKey;

    MenuOptions(String translationKey) {
        this.translationKey = translationKey;
    }

    public String getTranslationKey() {
        return translationKey;
    }


}
