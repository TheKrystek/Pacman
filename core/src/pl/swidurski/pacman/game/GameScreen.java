package pl.swidurski.pacman.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.actions.CountDownAction;
import pl.swidurski.pacman.actions.HideDoorsAction;
import pl.swidurski.pacman.actions.SleepAction;
import pl.swidurski.pacman.actions.StartGameAction;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.MapLoader;
import pl.swidurski.pacman.map.elements.*;

/**
 * Created by Krystek on 2016-04-10.
 */
public class GameScreen implements Screen {
    SpriteBatch movableBatch;
    SpriteBatch staticBatch;
    SpriteBatch debugBatch;
    SpriteBatch scorerBatch;
    Map map;


    boolean debug = false;

    public GameScreen(boolean newGame) {
        if (newGame)
            Map.getScorer().reset();
    }

    public GameScreen() {
        this(false);
    }

    private String getMapToLoad() {
        return String.format("core/assets/maps/%s.map", Map.getScorer().getLevel());
    }

    @Override
    public void show() {
        movableBatch = new SpriteBatch();
        staticBatch = new SpriteBatch();
        debugBatch = new SpriteBatch();
        scorerBatch = new SpriteBatch();
        map = MapLoader.load(getMapToLoad());

        new StartGameAction().execute(null, null, map);
    }


    @Override
    public void render(float delta) {
        moveObjects();

        clear();
        drawMap();
        drawMovableObjects();
        drawDebugInfo();


        scorerBatch.begin();
        Map.getScorer().draw(scorerBatch);

        scorerBatch.end();
    }

    private void clear() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void moveObjects() {
        getPacman().move();
        map.getGhosts().forEach(Ghost::move);
    }

    private void drawMap() {
        staticBatch.begin();
        for (MapElement<?> mapElement : map.getStaticObjects()) {
            if (debug && !(mapElement instanceof Point))
                mapElement.draw(staticBatch);
            if (!debug)
                mapElement.draw(staticBatch);
        }
        staticBatch.end();
    }

    private void drawMovableObjects() {
        // Draw
        movableBatch.begin();
        for (MapElement<?> mapElement : map.getMovableObjects())
            mapElement.draw(movableBatch);
        movableBatch.end();
    }

    private void drawDebugInfo() {
        // Na ostatniej warstwie rysuj informacje do debugowania
        if (debug) {
            debugBatch.begin();
            for (MapElement<?> mapElement : map.getStaticObjects())
                if (mapElement instanceof Intersection)
                    mapElement.draw(debugBatch);
            debugBatch.end();
        }
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
