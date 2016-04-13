package pl.swidurski.pacman.map;

import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.Pacman;
import pl.swidurski.pacman.map.elements.*;

import java.util.HashMap;

/**
 * Created by Krystek on 2016-04-11.
 */
public class MapBuilder {
     Map map;

    public  void setMap(Map map) {
        this.map = map;
    }

    private  java.util.Map<Integer,Teleport> teleports = new HashMap<>();


    private  MapElement<?> build(MapElements element, float x, float y) {
        switch (element) {
            case WALL:
                return buildWall(x, y);
            case PATH:
                return buildPath(x,y, false);
            case CROSS:
                return buildPath(x,y, true);
            case DOORS:
                break;
            case POINT:
                return buildPoint(x, y);
            case BIG_POINT:
                return buildBigPoint(x, y);
            case GHOST_RED:
                return createGhost(Ghost.GhostColors.RED,x, y);
            case GHOST_BLUE:
                return createGhost(Ghost.GhostColors.BLUE,x, y);
            case GHOST_PINK:
                return createGhost(Ghost.GhostColors.PINK,x, y);
            case GHOST_YELLOW:
                return createGhost(Ghost.GhostColors.YELLOW,x, y);
            case PACMAN:
                return createPacman(x, y);
            case TELEPORT_ONE:
                return buildTeleport(x, y,1);
            case TELEPORT_TWO:
                return buildTeleport(x, y,2);
            case TELEPORT_THREE:
                return buildTeleport(x, y,3);
            case TELEPORT_FOUR:
                return buildTeleport(x, y,4);
            case TELEPORT_FIVE:
                return buildTeleport(x, y,5);
        }
        return null;
    }


    private static Vector2 getObjectsPosition(float x, float y) {
        return new Vector2(x *  Wall.SIZE, y *  Wall.SIZE);
    }

    private static Vector2 getSmallObjectsPosition(float x, float y) {
        x = x * Wall.SIZE + (Wall.SIZE / 2);
        y = y * Wall.SIZE + Wall.SIZE / 2;
        return new Vector2(x, y);
    }

    private  MapElement<?> createPacman(float x, float y) {
        PacmanObject pacman = new PacmanObject(getObjectsPosition(x, y), map);


        pacman.setOnCollision(((source, element) -> {
            System.err.printf("Kolizja %s z obiektem typu: %s\r\n", source.getClass().getSimpleName(), element.getClass().getSimpleName());

            source.getActions().forEach(action ->  action.execute(pacman, element, map));

            if (element instanceof Eatable)
            {
                Eatable food = (Eatable) element;
                if (food.isEatable()) {
                    map.removeObject(element);
                }
            }
        }));

        map.setPacman(pacman);
        return pacman;
    }

    private  MapElement<?> createGhost(Ghost.GhostColors ghostColor, float x, float y) {
        Ghost ghost = Ghost.createGhost(ghostColor, getObjectsPosition(x, y), map);
        if (ghostColor == Ghost.GhostColors.RED)
        ghost.setOnCollision((source, element) -> {
            ghost.incrementCollisionCounter();
            ghost.getActions().forEach(action -> action.execute(ghost, element,map));
        });

        ghost.setAfterMove((source, element) -> {
            ghost.resetCollisinCounter();
        });

        return ghost;
    }

    private  MapElement<?> buildPoint(float x, float y) {
        return new Point(getSmallObjectsPosition(x,y));
    }

    private  MapElement<?> buildBigPoint(float x, float y) {
        return new BigPoint(getSmallObjectsPosition(x,y));
    }

    private  MapElement<?> buildWall(float x, float y) {
        return new Wall(getObjectsPosition(x,y));
    }

    private  MapElement<?> buildPath(float x, float y, boolean cross) {
        Path path =  new Path(getObjectsPosition(x,y));
        path.setCross(cross);
        return path;
    }


    private  MapElement<?> buildTeleport(float x, float y, int number) {
        Teleport teleport = new Teleport(getObjectsPosition(x,y));

        if (!teleports.containsKey(number))
            teleports.put(number, teleport);
        else
            teleport.setExit(teleports.get(number));
        return teleport;
    }



    public  void buildElement(MapElements element, Map map, int col, int row) {
        MapElement<?> newElement = build(element, col, row);
        if (newElement != null)
            map.addObject(newElement);
    }

    private void getElementAt(int col, int row){

    }
}
