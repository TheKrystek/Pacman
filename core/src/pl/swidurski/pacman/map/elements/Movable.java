package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.math.Circle;
import pl.swidurski.pacman.map.Map;

/**
 * Created by student on 2016-04-10.
 */
public interface Movable {
    Circle move();

    Circle moveUp(float y);

    Circle moveDown(float y);

    Circle moveLeft(float x);

    Circle moveRight(float x);

    boolean checkCollisions(Map map);
}
