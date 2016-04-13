package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;

/**
 * Created by Krystek on 2016-04-12.
 */
public interface Action {
    void execute(MovableObject source, MapElement<?> element, Map map);
}
