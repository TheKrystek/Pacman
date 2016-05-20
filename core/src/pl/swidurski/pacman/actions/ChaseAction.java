package pl.swidurski.pacman.actions;

import pl.swidurski.pacman.map.Map;
import pl.swidurski.pacman.map.Orientation;
import pl.swidurski.pacman.map.elements.Ghost;
import pl.swidurski.pacman.map.elements.MapElement;
import pl.swidurski.pacman.map.elements.MovableObject;
import pl.swidurski.pacman.map.elements.Path;
import pl.swidurski.pacman.utils.DijkstraAlgorithm;

import java.util.LinkedList;

/**
 * Created by Krystek on 2016-04-13.
 */
public class ChaseAction implements Action {

    private final int offset;

    DijkstraAlgorithm dijkstra;


    public ChaseAction(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute(MovableObject source, MapElement<?> element, Map map) {
        if (dijkstra == null)
            dijkstra = new DijkstraAlgorithm(map.getGraph());

        Ghost ghost = (Ghost) source;

        // Pobierz lokalizację pacmana i duszka
        int currentPosition = source.getNode();
        int pacmanCurrentNode = map.getPacman().getNode();
        if (pacmanCurrentNode == 0)
            pacmanCurrentNode = map.getPacman().getNodeId();
        pacmanCurrentNode = (pacmanCurrentNode + offset) % (map.getCols() * map.getRows());

        // Wskaż start i cel
        dijkstra.execute(map.getMapElements().get(source.getNode()));
        ghost.setPath(dijkstra.getPath(map.getMapElements().get(pacmanCurrentNode)));


        // Jeżeli ścieżka jest pusta - nie zmieniaj kierunku
        if (ghost.getPath() == null)
            return;

        // Jeżeli aktualnie jesteśmy na pierwszym polu ścieżki, to pobieramy następne
        Path p = ghost.getPath().get(0);
        if (p.getNodeId() == currentPosition)
            p = ghost.getPath().get(1);

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
