package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.Orientation;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;
import pl.swidurski.pacman.map.elements.Path;
import pl.swidurski.pacman.utils.DijkstraAlgorithm;

import java.util.LinkedList;

/**
 * Created by Krystek on 2016-04-13.
 */
public class HuntAction implements Action {

    private final int offset;

    DijkstraAlgorithm dijkstra;
    LinkedList<Path> path;

    public HuntAction(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        if (dijkstra == null)
            dijkstra = new DijkstraAlgorithm(map.getGraph());

        // Pobierz lokalizację pacmana i duszka
        int currentPosition = source.getNode();
        int pacmanCurrentNode = map.getPacman().getNode();
        if (pacmanCurrentNode == 0)
            pacmanCurrentNode = map.getPacman().getNodeId();
        pacmanCurrentNode = (pacmanCurrentNode + offset) % (map.getCols() * map.getRows());

        // Wskaż start i cel
        dijkstra.execute(map.getMapElements().get(source.getNode()));
        path = dijkstra.getPath(map.getMapElements().get(pacmanCurrentNode));


        // Jeżeli ścieżka jest pusta - nie zmieniaj kierunku
        if (path == null)
            return;

        // Jeżeli aktualnie jesteśmy na pierwszym polu ścieżki, to pobieramy następne
        Path p = path.poll();
        if (p.getNodeId() == currentPosition)
            p = path.poll();

        source.setOrientation(getOrientation(currentPosition, p.getNodeId(), map.getCols()));
    }


    protected Orientation getOrientation(int source, int target, int cols) {
        int sourceRow = source / cols;
        int targetRow = target / cols;
        // Wyżej, lub po prawej jeżeli większe
        if (target > source)
            return sourceRow == targetRow ? Orientation.EAST : Orientation.NORTH;
            // Niżej, lub po lewej jeżeli większe
        else
            return sourceRow == targetRow ? Orientation.WEST : Orientation.SOUTH;
    }
}
