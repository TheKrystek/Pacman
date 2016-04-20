package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.Ghost;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;
import pl.swidurski.pacman.map.elements.PacmanObject;

/**
 * Created by Krystek on 2016-04-12.
 */

public class EatPacmanAction implements Action {
    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        if (!(element instanceof PacmanObject && source instanceof Ghost))
            return;
        Ghost ghost = (Ghost) source;
        // Jeżeli duszek jest jadalny, to przy kolizji z pacmanem to pacman wygrywa
        if (ghost.isEatable())
            return;

        // Zmniejsz liczbę żyć oraz wykonaj akcję przyracjącą początkowy układ mapy
        Map.getScorer().decreaseLives();
        new DecreaseLivesAction().execute(source, element, map);
    }
}
