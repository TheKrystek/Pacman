package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.Ghost;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;
import pl.swidurski.pacman.map.elements.Path;
import pl.swidurski.pacman.utils.DijkstraAlgorithm;

/**
 * Created by Krystek on 2016-04-13.
 */
public class RunAction extends ChaseAction {
    int threshold = 10;

    public RunAction(int offset) {
        super(offset);
    }

    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        if (dijkstra == null)
            dijkstra = new DijkstraAlgorithm(map.getGraph());

        Ghost ghost = (Ghost) source;

        // Pobierz lokalizację startową i obecną duszka
        int currentPosition = source.getNode();
        int startPosition = source.getNodeId();

        // Wskaż start i cel
        dijkstra.execute(map.getMapElements().get(currentPosition));
        ghost.setPath(dijkstra.getPath(map.getMapElements().get(startPosition)));

        // Jeżeli ścieżka jest pusta - nie zmieniaj kierunku
        if (ghost.getPath() == null)
            return;

        // Jeżeli aktualnie jesteśmy na pierwszym polu ścieżki, to pobieramy następne
        Path p = ghost.getPath().get(0);
        if (p.getNodeId() == currentPosition)
            p = ghost.getPath().get(1);

        source.setOrientation(getOrientation(currentPosition, p.getNodeId(), map.getCols()));
    }

}
