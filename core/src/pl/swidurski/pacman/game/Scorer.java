package pl.swidurski.pacman.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.Const;
import pl.swidurski.pacman.utils.TranslationUtil;

/**
 * Created by Krystek on 2016-04-18.
 */
public class Scorer {


    private BitmapFont scoreFont = new BitmapFont();
    private BitmapFont levelFont = new BitmapFont();
    private Texture pacman = new Texture(Gdx.files.internal(Const.PATHS_PACMAN));


    private int lives;
    private Vector2 start;
    private int level;
    private int points;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;
    public Scorer(Vector2 start) {
        this.start = start;
        reset();
        scoreFont.getData().setScale(1.2f);
        levelFont.getData().setScale(1.8f);
    }

    public void reset() {
        this.lives = 3;
        this.points = 0;
        this.level = 1;
        this.text = "";
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }


    public void draw(Batch batch) {
        levelFont.draw(batch, String.format("%s: %s", TranslationUtil.translate("game.level"), getLevel()), start.x, start.y);
        scoreFont.draw(batch, String.format("%s: %s", TranslationUtil.translate("game.score"), getPoints()), start.x, start.y - 40);
        for (int i = 0; i < lives; i++) {
            batch.draw(pacman, start.x + i * 36, start.y - 100);
        }

        // Wyswietl tekst na srodku ekranu
        if (text != null && !text.equals(""))
            levelFont.draw(batch, text, start.x / 2 - 5 - 8*text.length(), start.y /2 + 10);
    }


    public void decreaseLives() {
        lives--;
    }

    public void increaseLives() {
        lives++;
    }
}
