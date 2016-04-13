package pl.swidurski.pacman.menu.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.utils.TranslationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krystek on 2016-04-09.
 */
public class MainMenu implements Screen {

    Vector2 start = new Vector2(100, 200);
    Vector2 step = new Vector2(0, 50);
    private SpriteBatch batch = new SpriteBatch();
    private BitmapFont font = new BitmapFont();
    private List<MenuOptions> menu = new ArrayList<MenuOptions>();

    @Override
    public void show() {
        batch = new SpriteBatch();
        font.setColor(Color.RED);
        menu.add(MenuOptions.PLAY);
        menu.add(MenuOptions.TOP_SCORE);
        menu.add(MenuOptions.EXIT);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        Vector2 start = this.start.cpy();
        for (MenuOptions option : menu) {
            System.out.println(start);
            font.draw(batch, TranslationUtil.translate(option.getTranslationKey()), start.x, start.y);
            start.add(step);
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
