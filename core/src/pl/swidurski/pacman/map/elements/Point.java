package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by student on 2016-04-10.
 */
public class Point extends StaticElement<Circle> implements Eatable{
    public final static int RADIUS = 5;

    public Point(Vector2 position) {
        super(position);
        setColor(Color.WHITE);
    }

    @Override
    protected void init() {
        shape = new Circle(position.x, position.y, RADIUS);
    }

    @Override
    protected void draw() {
        shapeRenderer.circle(shape.x, shape.y, shape.radius);
    }

    @Override
    protected boolean collides(MapElement<?> element) {
        if (element.getShape() instanceof  Circle)
            return Intersector.overlaps(this.getShape(), (Circle) element.getShape());
        return Intersector.overlaps(this.getShape(), (Rectangle) element.getShape());
    }

    @Override
    public double getPoints() {
        return 10;
    }

    @Override
    public boolean isEatable() {
        return true;
    }
}
