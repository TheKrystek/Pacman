package pl.swidurski.pacman.utils;

/**
 * Created by Krystek on 2016-04-15.
 */
public class Edge<T> {

    private final T source;
    private final T destination;
    private final int weight;

    public Edge(T source, T destination) {
        this(source, destination, 0);
    }

    public Edge(T source, T destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }


    public T getDestination() {
        return destination;
    }

    public T getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " -> " + destination;
    }
}