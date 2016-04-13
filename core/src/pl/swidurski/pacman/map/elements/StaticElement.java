package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by student on 2016-04-10.
 */
abstract public class StaticElement<T> extends MapElement<T> {
    protected ShapeRenderer shapeRenderer;

    public StaticElement(float x, float y) {
        this(new Vector2(x, y));
    }

    public StaticElement(Vector2 position) {
        super(position);
        shapeRenderer = new ShapeRenderer();
    }

    protected abstract void draw();

    @Override
    public void draw(Batch batch) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if (color != null)
        shapeRenderer.setColor(color);
        draw();
        shapeRenderer.end();
    }
}
