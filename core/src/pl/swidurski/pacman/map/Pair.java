package pl.swidurski.pacman.map;

import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MapElements;

/**
 * Created by Krystek on 2016-04-15.
 */
public class Pair {
    MapElements type;
    MapElement<?> element;
    int number;

    public Pair(MapElements type, MapElement<?> element) {
        this.type = type;
        this.element = element;
    }

    public Pair(MapElements type, int number) {
        this.type = type;
        this.element = element;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public MapElement<?> getElement() {
        return element;
    }

    public void setElement(MapElement<?> element) {
        this.element = element;
    }

    public MapElements getType() {
        return type;
    }

    public void setType(MapElements type) {
        this.type = type;
    }


}

