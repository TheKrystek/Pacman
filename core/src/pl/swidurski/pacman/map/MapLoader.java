package pl.swidurski.pacman.map;


import pl.swidurski.pacman.map.elements.MapElements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.OptionalInt;


/**
 * Created by Krystek on 2016-04-10.
 */
public class MapLoader {
    static Map map;
    static MapBuilder builder;
    static GraphBuilder graphBuilder;
    static int rows, cols;
    static char[][] array;
    private static int points;

    public static Map load(String fileName) {
        points = 0;
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            rows = lines.size();
            OptionalInt c = lines.stream().mapToInt(p -> p.length()).max();
            if (c.isPresent())
                cols = c.getAsInt();

            array = new char[rows][cols];

            for (int row = 0; row < rows; row++)
                for (int col = 0; col < lines.get(row).length(); col++)
                    array[row][col] = lines.get(row).charAt(col);

        } catch (IOException e) {
            System.err.println("Cannot load map: " + fileName);
        }

        map = new Map(rows, cols);
        parse();
        graphBuilder = new GraphBuilder(map);
        graphBuilder.buildGraph();

        map.setPointsLeft(points);
        System.out.println(points);
        return map;
    }

    private static void parse() {
        builder = new MapBuilder();
        builder.setMap(map);
        for (int row = 0; row < rows; row++) {
            int r = rows - row - 1;
            for (int col = 0; col < cols; col++) {
                MapElements element = MapElements.getMapElement(array[r][col]);
                if (element == MapElements.POINT || element == MapElements.BIG_POINT)
                    points++;

                // Stwórz element na podstawie danych odczytanch z pliku
                builder.buildElement(element, map, col, row);
                if (isPath(r, col))
                    builder.buildElement(getPathType(r, col), map, col, row);
                builder.incrementCounter();
            }
        }
    }


    private static boolean isPath(int row, int col) {
        return getElement(row, col) != MapElements.WALL;
    }

    private static MapElements getPathType(int row, int col) {
        MapElements left = getElement(row, col - 1);
        MapElements right = getElement(row, col + 1);
        MapElements up = getElement(row - 1, col);
        MapElements down = getElement(row + 1, col);

        boolean vertical = (left == MapElements.WALL && right == MapElements.WALL && up != MapElements.WALL && down != MapElements.WALL);
        boolean horizontal = (left != MapElements.WALL && right != MapElements.WALL && up == MapElements.WALL && down == MapElements.WALL);
        if (horizontal || vertical)
            return MapElements.PATH;
        return MapElements.INTERSECTION;
    }


    private static MapElements getElement(int row, int col) {
        if (col < 0 || col >= cols || row < 0 || row >= rows)
            return MapElements.WALL;
        return MapElements.getMapElement(array[row][col]);
    }
}
