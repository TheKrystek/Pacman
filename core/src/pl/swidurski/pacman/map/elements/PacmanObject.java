package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.AnimatedObject;
import pl.swidurski.pacman.actions.EatAction;
import pl.swidurski.pacman.actions.TeleportAction;
import pl.swidurski.pacman.map.Map;

/**
 * Created by student on 2016-04-10.
 */
public class PacmanObject extends MovableObject {

    public PacmanObject(int nodeId, Vector2 start, Map map) {
        super(nodeId, new AnimatedObject("core/assets/pacmansheet.png"), start);
        setMap(map);
        setSpeed(1);
        // Zdefiniuj akcje pacmana
        actions.add(new EatAction()); // Pacman może się teleportować
        actions.add(new TeleportAction()); // Pacman może się teleportować
        // new SleepAction(5).execute(this,this,map);
    }

    @Override
    public Circle move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            return moveUp(speed);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            return moveDown(speed);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            return moveLeft(speed);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            return moveRight(speed);
        return getShape();
    }
}
