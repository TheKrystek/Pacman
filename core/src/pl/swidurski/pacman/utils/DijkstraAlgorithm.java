package pl.swidurski.pacman.utils;

import java.util.*;

/**
 * Created by Krystek on 2016-04-15.
 */
public class DijkstraAlgorithm<T> {

    private final HashMap<Integer, T> nodes;
    private final List<Edge<T>> edges;
    private Set<T> settledNodes;
    private Set<T> unSettledNodes;
    private Map<T, T> predecessors;
    private Map<T, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new HashMap<>(graph.getVertexes());
        this.edges = new ArrayList<>(graph.getEdges());
    }

    public void execute(T source) {
        settledNodes = new HashSet<T>();
        unSettledNodes = new HashSet<T>();
        distance = new HashMap<T, Integer>();
        predecessors = new HashMap<T, T>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            T node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(T node) {
        List<T> adjacentNodes = getNeighbors(node);
        for (T target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(T node, T target) {
        for (Edge<T> edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<T> getNeighbors(T node) {
        List<T> neighbors = new ArrayList<T>();
        for (Edge<T> edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private T getMinimum(Set<T> vertexes) {
        T minimum = null;
        for (T vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(T vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(T destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<T> getPath(T target) {
        LinkedList<T> path = new LinkedList<T>();
        T step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
