package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.ScreenManger;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;

/**
 * Created by Krystek on 2016-04-19.
 */
public class NexLevelAction implements Action {
    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        Map.getScorer().setLevel(Map.getScorer().getLevel() + 1);
        ScreenManger.nextLevel();
    }
}
