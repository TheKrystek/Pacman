package pl.swidurski.pacman.utils;

import pl.swidurski.pacman.map.Orientation;

/**
 * Created by student on 2016-04-10.
 */
public class GhostRotator extends Rotator {
    @Override
    public float getRotation() {
        return 90;
    }
}
