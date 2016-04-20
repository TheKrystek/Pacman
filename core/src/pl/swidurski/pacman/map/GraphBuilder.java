package pl.swidurski.pacman.map;

import pl.swidurski.pacman.map.elements.Path;
import pl.swidurski.pacman.map.elements.StaticElement;
import pl.swidurski.pacman.map.elements.Teleport;
import pl.swidurski.pacman.utils.Edge;
import pl.swidurski.pacman.utils.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Created by Krystek on 2016-04-17.
 */
public class GraphBuilder {

    HashMap<Integer, Path> nodes;
    List<Edge<Path>> lanes = new ArrayList<>();
    Graph<Path> graph;
    Map map;

    public GraphBuilder(Map map) {
        this.map = map;
        this.nodes = map.getMapElements();
        this.graph = new Graph<>(nodes, lanes);
    }

    public void buildGraph() {
        for (Entry<Integer, Path> entry : nodes.entrySet()) {
            int left = getLeft(entry.getKey());
            int right = getRight(entry.getKey());
            int up = getUp(entry.getKey());
            int down = getDown(entry.getKey());

            addLane(entry.getKey(), left);
            addLane(entry.getKey(), right);
            addLane(entry.getKey(), down);
            addLane(entry.getKey(), up);

            // Dodaj graficzne oznaczenia do path
            entry.getValue().setSouth(down);
            entry.getValue().setNorth(up);
            entry.getValue().setEast(right);
            entry.getValue().setWest(left);
        }

        for (StaticElement<?> element : map.getStaticObjects()) {
            // Dodaj teleporty
            if (element instanceof Teleport) {
                Teleport t = (Teleport) element;
                int nodeId = t.getNodeId();
                int exitId = t.getExit().getNodeId();

                nodes.get(nodeId).setSouth(exitId);
                addLane(nodeId, exitId);
            }
        }
        map.setGraph(graph);
    }


    private void addLane(int source, Integer target) {
        if (target == null || target < 0)
            return;
        Edge lane = new Edge(nodes.get(source), nodes.get(target));
        lanes.add(lane);
    }

    private int getLeft(int nodeId) {
        // Pierwsze w rzędzie
        if (nodeId % map.getCols() == 0)
            return -1;
        return getElement(nodeId - 1);
    }

    private int getRight(int nodeId) {
        // Ostatnie w rzędzie
        if (nodeId + 1 == map.getCols())
            return -1;
        return getElement(nodeId + 1);
    }

    private int getUp(int nodeId) {
        // Ostatni rząd
        if (nodeId >= (map.getRows() - 1) * map.getCols())
            return -1;
        return getElement(nodeId + map.getCols());
    }

    private int getDown(int nodeId) {
        // Pierwszy rząd
        if (nodeId < map.getCols())
            return -1;
        return getElement(nodeId - map.getCols());
    }

    private int getElement(int node) {
        if (nodes.containsKey(node))
            return node;
        return -1;
    }

}
