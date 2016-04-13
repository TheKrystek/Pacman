package pl.swidurski.pacman.map.elements;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.AnimatedObject;
import pl.swidurski.pacman.Const;
import pl.swidurski.pacman.actions.LookForPacman;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.Orientation;
import pl.swidurski.pacman.utils.GhostRotator;

import java.util.Random;

/**
 * Created by Krystek on 2016-04-10.
 */
public class Ghost extends MovableObject implements Eatable {

    Random generator = new Random();

    public int getCollisionsCounter() {
        return collisionsCounter;
    }

    int collisionsCounter = 0;

    public void incrementCollisionCounter(){
        collisionsCounter++;
        System.out.println("Collisions: " + collisionsCounter);
    }

    public void resetCollisinCounter(){
        if (collisionsCounter == 0)
            return;
        System.out.println("RESET");
        collisionsCounter = 0;
    }

    public Ghost(String filename, Vector2 start, Map map) {
        super(new AnimatedObject(filename), start);
        setMap(map);
        getObject().setDuration(1f);
        setSpeed(1);
        setRotator(new GhostRotator());
        setRandomOrientation();
        actions.add(new LookForPacman());
    }

    public static Ghost createGhost(GhostColors ghostColor, Vector2 start, Map map) {
        return new Ghost(ghostColor.getFilename(), start, map);
    }

    @Override
    public double getPoints() {
        return 10;
    }

    @Override
    public boolean isEatable() {
        return false;
    }

    public enum GhostColors {
        BLUE("blue_ghost.png"),
        YELLOW("yellow_ghost.png"),
        RED("red_ghost.png"),
        PINK("pink_ghost.png");

        private final String filename;

        GhostColors(String filename) {
            this.filename = filename;
        }

        public String getFilename() {
            return String.format("%s%s", Const.PATHS_ASSETS, filename);
        }
    }

    @Override
    public Circle move() {
        switch (getOrientation()) {
            case NORTH:
                return moveUp(speed);
            case SOUTH:
                return moveDown(speed);
            case WEST:
                return moveLeft(speed);
            case EAST:
                return moveRight(speed);
        }
        return getShape();
    }

    public void setRandomOrientation(){
        Orientation orientation = Orientation.NONE;
        int r = Math.abs(generator.nextInt())%4;
        switch (r){
            case 0: orientation = Orientation.NORTH; break;
            case 1: orientation = Orientation.EAST; break;
            case 2: orientation = Orientation.SOUTH; break;
            case 3: orientation = Orientation.WEST; break;
        }
        System.out.println(r);
        setOrientation(orientation);
    }

}
