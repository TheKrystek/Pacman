package pl.swidurski.pacman.actions;

import com.badlogic.gdx.math.Vector2;
import pl.swidurski.pacman.actions.Action;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;
import pl.swidurski.pacman.map.elements.Teleport;

/**
 * Created by Krystek on 2016-04-12.
 */

public class TeleportAction implements Action {
    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        if (!(element instanceof Teleport))
            return;

        Teleport teleport = (Teleport) element;
        System.out.printf("Teleport Entry=%s, Exit=%s\r\n", teleport.getPosition(), teleport.getExit().getPosition());

        Vector2 exit = teleport.getExitPosition(map.getPacman().getOrientation());
        map.getPacman().setPosition(exit);
    }
}
