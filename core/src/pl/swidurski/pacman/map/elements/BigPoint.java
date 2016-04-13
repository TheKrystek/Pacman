package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by student on 2016-04-10.
 */
public class BigPoint extends Point{
    public final static int RADIUS = 10;

    public BigPoint(Vector2 position) {
        super(position);
    }

    @Override
    protected void init() {
        shape = new Circle(position.x, position.y, RADIUS);
    }


    @Override
    public double getPoints() {
        return 50;
    }
}
