package pl.swidurski.pacman.utils;

import pl.swidurski.pacman.map.elements.MovableObject;

/**
 * Created by student on 2016-04-10.
 */
public abstract class Rotator {
    MovableObject object;

    public MovableObject getObject() {
        return object;
    }

    public void setObject(MovableObject object) {
        this.object = object;
    }

    abstract public float getRotation();
}
