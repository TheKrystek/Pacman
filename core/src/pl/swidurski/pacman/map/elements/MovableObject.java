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

    public final float defaultSpeed = 1;
    protected float speed;
    Orientation previousOrientation;
    ArrayList<Action> actions = new ArrayList<Action>();
    boolean hadCollision = false;
    private int node;
    private Rotator rotator;
    private IMapElementAction onCollision;
    private IMapElementAction afterMove;
    private IMapElementAction onIntersection;
    private Map map;
    private AnimatedObject object;

    public MovableObject(int nodeId, AnimatedObject object, Vector2 startPosition) {
        super(nodeId, startPosition);
        this.object = object;
        setRotator(new PacmanRotator());
        setPosition(startPosition);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Orientation getPreviousOrientation() {
        return previousOrientation;
    }

    @Override
    public void setOrientation(Orientation orientation) {
        previousOrientation = getOrientation();
        super.setOrientation(orientation);
    }

    public void setRotator(Rotator rotator) {
        this.rotator = rotator;
        this.rotator.setObject(this);
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public boolean hadCollision() {
        return hadCollision;
    }

    public void setAfterMove(IMapElementAction afterMove) {
        this.afterMove = afterMove;
    }

    public void setOnIntersection(IMapElementAction onIntersection) {
        this.onIntersection = onIntersection;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setOnCollision(IMapElementAction onCollision) {
        this.onCollision = onCollision;
    }

    protected void onIntersection(MapElement<?> element) {
        if (onIntersection != null)
            onIntersection.execute(this, element);
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

        boolean hasNodeChanged = hasNodeChanged(map);

        for (MapElement<?> element : map.getObjects()) {
            if (element == this)
                continue;

            if (element instanceof Intersection && this.collides(element) && hasNodeChanged)
                onIntersection(element);

            if (element.collides(this) && this.collides(element)) {
                onCollision(element);
                hadCollision = true;
                return true;
            }
        }
        if (!hadCollision())
            afterMove();

        hadCollision = false;
        return false;
    }

    private boolean hasNodeChanged(Map map) {
        float half = Wall.SIZE / 2;
        float X = (int) ((this.getPosition().x) / Wall.SIZE);
        float Y = (int) ((this.getPosition().y) / Wall.SIZE);

        Rectangle r = new Rectangle((X * Wall.SIZE) + half, (Y * Wall.SIZE) + half, 8, 8);

        if (r.contains(this.getPosition().cpy().add(half, half))) {
            int node = map.getNodeId(this.getPosition());
            if (node != this.getNode()) {
                this.setNode(node);
                return true;
            }
        }
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
        if (element.getShape() instanceof Circle)
            return Intersector.overlaps(this.getShape(), (Circle) element.getShape());
        return Intersector.overlaps(this.getShape(), (Rectangle) element.getShape());
    }
}
