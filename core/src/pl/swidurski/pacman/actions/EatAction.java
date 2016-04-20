package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.SoundManger;
import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.*;

public class EatAction implements Action {
    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        if (!(element instanceof Eatable))
            return;
        Eatable food = (Eatable) element;

        if (!food.isEatable())
            return;

        // Jeżeli to duży punkt, to zmień zachowanie duszków
        if (element instanceof BigPoint)
            new ChangeBehaviourAction(10).execute(source, element, map);

        // Jeżeli to punkt, to usuń go z mapy
        if (element instanceof Point) {
            map.removeObject(element);
            map.decrementPoints();
            SoundManger.playChomp();
        }

        // Jeżeli zjedzono wszystkie punkty to przenieś do następnego poziomu
        if (map.getPointsLeft() == 0)
            new NexLevelAction().execute(source, element, map);

        // Duszki przenieś do "domku" środku ekranu
        if (element instanceof Ghost) {
            SoundManger.playEatGhost();
            Ghost ghost = (Ghost) element;
            ghost.setPosition(map.getMapElements().get(ghost.getNodeId()).getPosition().cpy());
            ghost.setEatable(false);
        }

        Map.getScorer().addPoints(food.getPoints());
    }
}
