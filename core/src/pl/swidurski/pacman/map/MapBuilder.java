package pl.swidurski.pacman.map;

import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.map.elements.*;

import java.util.HashMap;

/**
 * Created by Krystek on 2016-04-11.
 */
public class MapBuilder {
    Map map;
    int counter = 0;

    private java.util.Map<Integer, Teleport> teleports = new HashMap<>();

    private static Vector2 getObjectsPosition(float x, float y) {
        return new Vector2(x * Wall.SIZE, y * Wall.SIZE);
    }

    private static Vector2 getSmallObjectsPosition(float x, float y) {
        x = x * Wall.SIZE + (Wall.SIZE / 2);
        y = y * Wall.SIZE + Wall.SIZE / 2;
        return new Vector2(x, y);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void incrementCounter() {
        counter++;
    }

    private MapElement<?> build(MapElements element, float x, float y) {
        switch (element) {
            case WALL:
                return buildWall(x, y);
            case PATH:
                return buildPath(x, y, false);
            case INTERSECTION:
                return buildPath(x, y, true);
            case DOORS:
                return buildDoors(x,y);
            case POINT:
                return buildPoint(x, y);
            case BIG_POINT:
                return buildBigPoint(x, y);
            case GHOST_RED:
                return createGhost(Ghost.GhostColors.RED, x, y);
            case GHOST_BLUE:
                return createGhost(Ghost.GhostColors.BLUE, x, y);
            case GHOST_PINK:
                return createGhost(Ghost.GhostColors.PINK, x, y);
            case GHOST_YELLOW:
                return createGhost(Ghost.GhostColors.YELLOW, x, y);
            case PACMAN:
                return createPacman(x, y);
            case TELEPORT_ONE:
                return buildTeleport(x, y, 1);
            case TELEPORT_TWO:
                return buildTeleport(x, y, 2);
            case TELEPORT_THREE:
                return buildTeleport(x, y, 3);
            case TELEPORT_FOUR:
                return buildTeleport(x, y, 4);
            case TELEPORT_FIVE:
                return buildTeleport(x, y, 5);
        }
        return null;
    }

    private MapElement<?> buildDoors(float x, float y) {
        return new Doors(counter, getObjectsPosition(x, y));
    }

    private MapElement<?> createPacman(float x, float y) {
        PacmanObject pacman = new PacmanObject(counter, getObjectsPosition(x, y), map);

        // Dla pacmana wykonujemy akcje przy kolizji z innymi obiektami
        pacman.setOnCollision(((source, element) -> {
            source.getActions().forEach(action -> action.execute(pacman, element, map));
        }));

        map.setPacman(pacman);
        return pacman;
    }

    private MapElement<?> createGhost(Ghost.GhostColors ghostColor, float x, float y) {
        Ghost ghost = Ghost.createGhost(counter, ghostColor, getObjectsPosition(x, y), map);

        ghost.setOnCollision((source, element) -> {

            ghost.getActions().forEach(action -> action.execute(ghost, element, map));
            if (element instanceof Wall)
                ghost.getMovingAction().execute(source, element, map);
        });

        ghost.setOnIntersection((source, element) -> {
            ghost.getMovingAction().execute(source, element, map);
        });
        return ghost;
    }

    private MapElement<?> buildPoint(float x, float y) {
        return new Point(counter, getSmallObjectsPosition(x, y));
    }

    private MapElement<?> buildBigPoint(float x, float y) {
        return new BigPoint(counter, getSmallObjectsPosition(x, y));
    }

    private MapElement<?> buildWall(float x, float y) {
        return new Wall(counter, getObjectsPosition(x, y));
    }

    private MapElement<?> buildPath(float x, float y, boolean intersection) {
        return intersection ?
                new Intersection(counter, getObjectsPosition(x, y)) :
                new Path(counter, getObjectsPosition(x, y));
    }


    private MapElement<?> buildTeleport(float x, float y, int number) {
        Teleport teleport = new Teleport(counter, getObjectsPosition(x, y));

        if (!teleports.containsKey(number))
            teleports.put(number, teleport);
        else
            teleport.setExit(teleports.get(number));
        return teleport;
    }


    public MapElement buildElement(MapElements element, Map map, int col, int row) {
        MapElement<?> newElement = build(element, col, row);
        if (newElement != null)
            map.addObject(newElement);
        return newElement;
    }
}
