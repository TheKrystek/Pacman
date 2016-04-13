package pl.swidurski.pacman.map;

import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;

/**
 * Created by Krystek on 2016-04-11.
 */
public interface IMapElementAction {

    void execute(MovableObject source, MapElement<?> element);

}
