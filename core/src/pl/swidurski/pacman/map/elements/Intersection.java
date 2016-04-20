package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by student on 2016-04-10.
 */
public class Intersection extends Path {

    public Intersection(int nodeId, Vector2 position) {
        super(nodeId, position);
        setColor(Color.ORANGE);
    }

    @Override
    protected void init() {
        shape = new Rectangle(position.x, position.y, SIZE, SIZE);
    }

    @Override
    protected boolean collides(MapElement<?> element) {
        return false;
    }
}
