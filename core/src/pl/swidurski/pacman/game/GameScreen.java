package pl.swidurski.pacman.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.MapLoader;
import pl.swidurski.pacman.map.elements.Ghost;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.PacmanObject;

/**
 * Created by Krystek on 2016-04-10.
 */
public class GameScreen implements Screen {
    SpriteBatch movableBatch;
    SpriteBatch staticBatch;
    Map map;



    @Override
    public void show() {
        movableBatch = new SpriteBatch();
        staticBatch = new SpriteBatch();
        map = MapLoader.load("core/assets/maps/one.map");

    }


    @Override
    public void render(float delta) {
        getPacman().move();
        map.getGhosts().forEach(Ghost::move);

        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        
        staticBatch.begin();
        for (MapElement<?> mapElement : map.getStaticObjects())
            mapElement.draw(staticBatch);
        staticBatch.end();

        // Draw
        movableBatch.begin();
        for (MapElement<?> mapElement : map.getMovableObjects())
            mapElement.draw(movableBatch);
        movableBatch.end();


    }


    private PacmanObject getPacman() {
        return map.getPacman();
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

    }
}
