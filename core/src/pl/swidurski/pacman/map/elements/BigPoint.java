package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by student on 2016-04-10.
 */
public class BigPoint extends Point {
    public final static int RADIUS = 10;

    public BigPoint(int counter, Vector2 position) {
        super(counter, position);
    }

    @Override
    protected void init() {
        shape = new Circle(position.x, position.y, RADIUS);
    }


    @Override
    public int getPoints() {
        return 50;
    }
}
