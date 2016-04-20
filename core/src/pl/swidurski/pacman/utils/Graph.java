package pl.swidurski.pacman.utils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Krystek on 2016-04-15.
 */
public class Graph<T> {
    private final HashMap<Integer, T> vertexes;
    private final List<Edge<T>> edges;

    public Graph(HashMap<Integer, T> vertexes, List<Edge<T>> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public HashMap<Integer, T> getVertexes() {
        return vertexes;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }


}
