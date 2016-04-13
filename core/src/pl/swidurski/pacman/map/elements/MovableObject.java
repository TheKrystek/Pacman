package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.AnimatedObject;
import pl.swidurski.pacman.actions.Action;
import pl.swidurski.pacman.map.IMapElementAction;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.Orientation;
import pl.swidurski.pacman.utils.PacmanRotator;
import pl.swidurski.pacman.utils.Rotator;

import java.util.ArrayList;

/**
 * Created by student on 2016-04-10.
 */
public class MovableObject extends MapElement<Circle> implements Movable {
    public void setRotator(Rotator rotator) {
        this.rotator = rotator;
        this.rotator.setObject(this);
    }


    private Rotator rotator;
    protected float speed;
    ArrayList<Action> actions = new ArrayList<Action>();
    private IMapElementAction onCollision;

    public boolean hadCollision() {
        return hadCollision;
    }

    boolean hadCollision = false;

    public void setAfterMove(IMapElementAction afterMove) {
        this.afterMove = afterMove;
    }

    private IMapElementAction afterMove;


    private Map map;
    private AnimatedObject object;

    public MovableObject(AnimatedObject object, Vector2 startPosition) {
        super(startPosition);
        this.object = object;
        setRotator(new PacmanRotator());
        setPosition(startPosition);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }




    public void setOnCollision(IMapElementAction onCollision) {
        this.onCollision = onCollision;
    }

    protected void onCollision(MapElement<?> element) {
        if (onCollision != null)
            onCollision.execute(this, element);
    }

    protected void afterMove() {
        if (afterMove != null)
            afterMove.execute(this, null);
    }

    public AnimatedObject getObject() {
        return object;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }


    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2 position) {
        float radius = object.getWidth() / 2;
        this.position = position;
        shape.setPosition(this.position);
        shape.setRadius(radius);
    }

    @Override
    public Circle move() {
        return getShape();
    }

    @Override
    public Circle moveUp(float y) {
        return move(0, y, Orientation.NORTH);
    }

    @Override
    public Circle moveDown(float y) {
        return move(0, -y, Orientation.SOUTH);
    }

    @Override
    public Circle moveLeft(float x) {
        return move(-x, 0, Orientation.WEST);
    }

    @Override
    public Circle moveRight(float x) {
        return move(x, 0, Orientation.EAST);
    }

    private Circle move(float x, float y, Orientation orientation) {
        this.position.add(x, y);
        setOrientation(orientation);
        if (checkCollisions(map))
            return move(-x, -y, getOrientation());
        return getShape();
    }

    @Override
    public boolean checkCollisions(Map map) {
        if (map == null)
            return false;

        for (MapElement<?> element : map.getObjects()) {
            if (element == this)
                continue;

            if (element.collides(this)) {
                onCollision(element);
                hadCollision = true;
                return true;
            }
        }
        if (!hadCollision)
            afterMove();
        hadCollision = false;
        return false;
    }

    public Circle getShape() {
        shape.setX(position.x + shape.radius);
        shape.setY(position.y + shape.radius);
        return shape;
    }

    @Override
    protected void init() {
        shape = new Circle();
    }

    @Override
    public void draw(Batch batch) {
        // Obrót wokół środka koła
        batch.draw(object.getFrame(), position.x, position.y, shape.radius, shape.radius, object.getWidth(), object.getHeight(), 1, 1, rotator.getRotation(), true);
    }

    @Override
    protected boolean collides(MapElement<?> element) {
        if (element.getShape() instanceof  Circle)
            return Intersector.overlaps(this.getShape(), (Circle) element.getShape());
        return Intersector.overlaps(this.getShape(), (Rectangle) element.getShape());
    }
}
