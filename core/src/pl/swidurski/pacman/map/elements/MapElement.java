package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.map.Orientation;

/**
 * Created by student on 2016-04-10.
 */
abstract public class MapElement<T> {
    protected Vector2 position;
    protected T shape;
    protected Color color;
    protected int nodeId;
    private Orientation orientation = Orientation.EAST;

    public MapElement(int nodeId, Vector2 position) {
        this.nodeId = nodeId;
        this.position = position;
        init();
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public T getShape() {
        return shape;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    abstract protected void init();

    public abstract void draw(Batch batch);

    abstract protected boolean collides(MapElement<?> element);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
