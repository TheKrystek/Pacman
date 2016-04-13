package pl.swidurski.pacman.map;


import pl.swidurski.pacman.map.elements.MapElements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;


/**
 * Created by Krystek on 2016-04-10.
 */
public class MapLoader {
    static Map map;
    static MapBuilder builder;
    static int rows, cols;

    static char [][] array;

    public static Map load(String fileName) {
        map = new Map();
        builder = new MapBuilder();
        builder.setMap(map);
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            rows = lines.size();
            OptionalInt c = lines.stream().mapToInt(p-> p.length()).max();
            if (c.isPresent())
                cols = c.getAsInt();

            array = new char[rows][cols];

            for (int row = 0; row < rows; row++)
                for (int col = 0; col < lines.get(row).length(); col++)
                    array[row][col] = lines.get(row).charAt(col);

        } catch (IOException e) {
            e.printStackTrace();
        }

        parse();
        return map;
    }



    private static void parse() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++)
            {
                System.out.println(array[row][col]);
                MapElements element = MapElements.getMapElement(array[row][col]);
                if (element == MapElements.PATH){
                    if (isCrossing(row,col))
                        element = MapElements.CROSS;
                }

                builder.buildElement(element, map, col, row);
            }
        }
    }


    private static boolean isCrossing(int row, int col){
        int walls = 0;
        // Góra
        if (getElement(row -1, col) == MapElements.WALL) walls++;
        // Doł
        if (getElement(row + 1, col) == MapElements.WALL) walls++;
        // Lewo
        if (getElement(row, col -1) == MapElements.WALL) walls++;
        // Prawo
        if (getElement(row, col + 1) == MapElements.WALL) walls++;

        System.out.println("Walls: " + walls);
        return walls < 2;
    }

    private static MapElements getElement(int row, int col)
    {
        if (col < 0 || col >= cols || row < 0 || row >= rows)
            return MapElements.WALL;
        return MapElements.getMapElement(array[row][col]);
    }


}
