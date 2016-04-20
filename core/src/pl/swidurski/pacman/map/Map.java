package pl.swidurski.pacman.map;

import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.game.Scorer;
import pl.swidurski.pacman.map.elements.*;
import pl.swidurski.pacman.utils.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Krystek on 2016-04-10.
 */
public class Map {

    private static Scorer scorer = new Scorer(new Vector2(21.5f * Wall.SIZE, 21 * Wall.SIZE));
    private final int cols;
    private final int rows;
    PacmanObject pacman;
    List<MapElement<?>> objects = new ArrayList<>();
    List<Ghost> ghosts = new ArrayList<>();
    List<MovableObject> movableObjects = new ArrayList<>();
    List<StaticElement<?>> staticObjects = new ArrayList<>();
    HashMap<Integer, Path> mapElements = new HashMap<>();
    Graph<Path> graph;
    private int pointsLeft;

    public Map(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public static Scorer getScorer() {
        return scorer;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public void setPointsLeft(int pointsLeft) {
        this.pointsLeft = pointsLeft;
    }

    public void decrementPoints() {
        pointsLeft--;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public List<MapElement<?>> getObjects() {
        return objects;
    }

    public void setObjects(List<MapElement<?>> objects) {
        this.objects = objects;
    }

    public List<MovableObject> getMovableObjects() {
        return movableObjects;
    }

    public List<StaticElement<?>> getStaticObjects() {
        return staticObjects;
    }

    public PacmanObject getPacman() {
        return pacman;
    }

    public void setPacman(PacmanObject pacman) {
        this.pacman = pacman;
    }

    public void addObject(MapElement<?> object) {
        if (object instanceof StaticElement)
            staticObjects.add((StaticElement<?>) object);
        if (object instanceof MovableObject)
            movableObjects.add((MovableObject) object);
        if (object instanceof Ghost)
            ghosts.add((Ghost) object);
        if (object instanceof Path)
            mapElements.put(object.getNodeId(), (Path) object);
        objects.add(object);
    }

    public void removeObject(MapElement<?> object) {
        if (object instanceof StaticElement)
            staticObjects.remove(object);
        if (object instanceof MovableObject)
            movableObjects.remove(object);

        if (object instanceof Ghost)
            ghosts.remove(object);

        objects.remove(object);
    }

    public HashMap<Integer, Path> getMapElements() {
        return mapElements;
    }

    public Graph<Path> getGraph() {
        return graph;
    }

    public void setGraph(Graph<Path> graph) {
        this.graph = graph;
    }


    public int getNodeId(Vector2 position) {
        int x = (int) ((position.x) / Wall.SIZE);
        int y = (int) ((position.y) / Wall.SIZE);
        return x + y * cols;
    }
}