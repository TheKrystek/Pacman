package pl.swidurski.pacman.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.swidurski.pacman.Const;
import pl.swidurski.pacman.ScreenManger;
import pl.swidurski.pacman.utils.TranslationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krystek on 2016-04-09.
 */
public class MainMenu implements Screen {
    int selectedIndex = 0;
    float time = 0;
    private Texture pacman = new Texture(Gdx.files.internal(Const.PATHS_PACMAN));
    private Texture logo = new Texture(Gdx.files.internal(Const.PATHS_LOGO));

    private SpriteBatch batch = new SpriteBatch();
    private BitmapFont font = new BitmapFont();

    private List<MenuOptions> menu = new ArrayList<MenuOptions>();

    @Override
    public void show() {
        batch = new SpriteBatch();
        font.setColor(new Color(Color.valueOf(Const.COLORS_WALLS)));
        font.getData().setScale(2);
        menu.add(MenuOptions.PLAY);
        menu.add(MenuOptions.EXIT);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            selectUpper();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            selectLower();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
            changeScene();


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(logo, 200, 600);
        int index = 0;
        for (MenuOptions option : menu) {
            font.draw(batch, TranslationUtil.translate(option.getTranslationKey()), option.getPosition().x, option.getPosition().y);
            if (index++ == selectedIndex)
                batch.draw(pacman, option.getPosition().x - 50, option.getPosition().y - 30);
        }

        batch.end();

        time += Gdx.graphics.getDeltaTime();
    }

    private void changeScene() {
        switch (selectedIndex) {
            case 0:
                ScreenManger.showGameWindow();
                break;
            case 1:
                ScreenManger.showTopScoreWindow();
                break;
            case 2:
                ScreenManger.exitGame();
                break;
        }
    }

    private void selectUpper() {
        if (getDelay()) return;
        selectedIndex = (selectedIndex - 1) % menu.size();
        if (selectedIndex < 0)
            selectedIndex = menu.size() - 1;

    }

    private void selectLower() {
        if (getDelay()) return;
        selectedIndex = (selectedIndex + 1) % menu.size();
    }

    private boolean getDelay() {
        if (time < 0.3)
            return true;
        time = 0;
        return false;
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
