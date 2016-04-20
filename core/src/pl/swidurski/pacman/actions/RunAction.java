package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;
import pl.swidurski.pacman.map.elements.Path;
import pl.swidurski.pacman.utils.DijkstraAlgorithm;

/**
 * Created by Krystek on 2016-04-13.
 */
public class RunAction extends HuntAction {
    int threshold = 10;

    public RunAction(int offset) {
        super(offset);
    }

    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        if (dijkstra == null)
            dijkstra = new DijkstraAlgorithm(map.getGraph());

        // Pobierz lokalizację startową i obecną duszka
        int currentPosition = source.getNode();
        int startPosition = source.getNodeId();

        // Wskaż start i cel
        dijkstra.execute(map.getMapElements().get(currentPosition));
        path = dijkstra.getPath(map.getMapElements().get(startPosition));

        // Jeżeli ścieżka jest pusta - nie zmieniaj kierunku
        if (path == null)
            return;

        // Jeżeli aktualnie jesteśmy na pierwszym polu ścieżki, to pobieramy następne
        Path p = path.poll();
        if (p.getNodeId() == currentPosition)
            p = path.poll();

        source.setOrientation(getOrientation(currentPosition, p.getNodeId(), map.getCols()));
    }

}
