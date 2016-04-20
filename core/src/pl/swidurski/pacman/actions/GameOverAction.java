package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.SoundManger;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;

/**
 * Created by Krystek on 2016-04-19.
 */
public class GameOverAction implements Action {
    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        map.getScorer().setText("GAME OVER!");
        SoundManger.playDeath();
        // Przywroć duchy i pacmana na swoje miejsce i wyłącz ruszanie
        for (MovableObject object : map.getMovableObjects()) {
            object.setPosition(map.getMapElements().get(object.getNodeId()).getPosition().cpy());
            object.setSpeed(0);
        }
    }
}
