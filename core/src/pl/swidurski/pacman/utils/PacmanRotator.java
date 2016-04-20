package pl.swidurski.pacman.utils;

/**
 * Created by student on 2016-04-10.
 */
public class PacmanRotator extends Rotator {
    @Override
    public float getRotation() {
        switch (object.getOrientation()) {
            case SOUTH:
                return 0;
            case EAST:
                return 90;
            case NORTH:
                return 180;
            case WEST:
                return 270;
            default:
                return 0;
        }
    }
}
