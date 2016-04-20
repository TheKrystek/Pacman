package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.SoundManger;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;

/**
 * Created by Krystek on 2016-04-19.
 */
public class DecreaseLivesAction implements Action {

    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        // Sprawdz czy wyswietlic game over
        if (Map.getScorer().getLives() == 0) {
            new GameOverAction().execute(source, element, map);
            return;
        }

        SoundManger.playDeath();
        // Przywroć duchy i pacmana na swoje miejsce i każ im czekać 3s
        for (MovableObject object : map.getMovableObjects()) {
            object.setPosition(map.getMapElements().get(object.getNodeId()).getPosition().cpy());
            new SleepAction(2).execute(object, element, map);
            new CountDownAction(2, "Go!").execute(null, null, map);
        }
    }
}
