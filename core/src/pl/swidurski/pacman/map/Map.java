package pl.swidurski.pacman.map;

import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.map.elements.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krystek on 2016-04-10.
 */
public class Map {
    // TODO wywalic


    public void setPacman(PacmanObject pacman) {
        this.pacman = pacman;
        this.addObject(pacman);
    }

    PacmanObject pacman;
    List<MapElement<?>> objects = new ArrayList<>();
    List<MovableObject> movableObjects = new ArrayList<>();
    List<StaticElement<?>> staticObjects = new ArrayList<>();

    public List<Ghost> getGhosts() {
        return ghosts;
    }


    List<Ghost> ghosts = new ArrayList<>();


    double score = 0;

    public List<MapElement<?>> getObjects() {
        return objects;
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



    public void addObject(MapElement<?> object) {
        if (object instanceof StaticElement)
            staticObjects.add((StaticElement<?>) object);
        if (object instanceof MovableObject)
            movableObjects.add((MovableObject) object);
        if (object instanceof  Ghost)
            ghosts.add((Ghost) object);

        objects.add(object);
    }

    public void removeObject(MapElement<?> object) {
        if (object instanceof StaticElement)
            staticObjects.remove(object);
        if (object instanceof MovableObject)
            movableObjects.remove(object);

        if (object instanceof  Ghost)
            ghosts.remove(object);

        objects.remove(object);
    }

}
