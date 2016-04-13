package pl.swidurski.pacman.actions;

import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.Orientation;
import pl.swidurski.pacman.map.elements.Ghost;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;

/**
 * Created by Krystek on 2016-04-13.
 */
public class LookForPacman implements Action {



    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        Ghost ghost = (Ghost) source;

        // Pobierz lokalizację pacmana
        Vector2 currentPacmanPosition = map.getPacman().getPosition();

        // Pobierz własną lokalizację
        Vector2 myPosition = element.getPosition();

        // Oblicz w którą stronę iść
        Orientation orientation = getOrientation(myPosition, currentPacmanPosition);
        source.setOrientation(orientation);

        if (ghost.getCollisionsCounter() > 10)
            ghost.setRandomOrientation();
    }

    private Orientation getOrientation(Vector2 myPosition, Vector2 currentPacmanPosition) {
        // Dodatnie w doł, ujemne w gore
        double upDown = myPosition.y - currentPacmanPosition.y;
        // Dodatnie w lewo, ujemne w prawo
        double leftRight = myPosition.x - currentPacmanPosition.x;

        Orientation vertical = upDown >=0 ? Orientation.SOUTH : Orientation.NORTH;
        Orientation horizontal = leftRight >=0 ? Orientation.WEST : Orientation.EAST;
        upDown = Math.abs(upDown);
        leftRight = Math.abs(leftRight);

        if (upDown > leftRight)
            return vertical;
        return horizontal;
    }

}
