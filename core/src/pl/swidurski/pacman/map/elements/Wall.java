package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.Const;

/**
 * Created by student on 2016-04-10.
 */
public class Wall extends StaticElement<Rectangle> {
    public static final float SIZE = 40;

    public Wall(Vector2 position) {
        super(position);
        setColor(new Color(Color.valueOf(Const.COLORS_WALLS)));
    }

    @Override
    protected void init() {
        shape = new Rectangle(position.x, position.y, SIZE, SIZE);
    }

    @Override
    protected void draw() {
        shapeRenderer.rect(position.x, position.y, shape.getWidth(), shape.getHeight());
    }


    @Override
    protected boolean collides(MapElement<?> element) {
        return Intersector.overlaps((Circle) element.getShape(), this.getShape());
    }
}
