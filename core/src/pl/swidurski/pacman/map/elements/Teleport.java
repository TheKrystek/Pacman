package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.map.Orientation;

/**
 * Created by student on 2016-04-10.
 */
public class Teleport extends StaticElement<Rectangle> {
    public static final float SIZE = 40;
    private Teleport exit;

    public Teleport(int nodeId, Vector2 position) {
        super(nodeId, position.x, position.y);
        setColor(Color.GREEN);
    }

    public Teleport getExit() {
        return exit;
    }

    public void setExit(Teleport exit) {
        this.exit = exit;
        if (exit.getExit() != this)
            exit.setExit(this);
    }

    public Vector2 getExitPosition(Orientation orientation) {
        Vector2 position = getExit().getPosition().cpy();
        float step = Wall.SIZE + 2;
        switch (orientation) {
            case NORTH:
                position.sub(0, step);
                break;
            case SOUTH:
                position.add(0, step);
                break;
            case WEST:
                position.sub(step, 0);
                break;
            case EAST:
                position.add(step, 0);
                break;
        }
        return position.cpy();
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
