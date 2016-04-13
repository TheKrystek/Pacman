package pl.swidurski.pacman.map.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by student on 2016-04-10.
 */
public enum MapElements {
    WALL('#'),
    PATH('@'),
    CROSS('X'),
    DOORS('D'),
    POINT('o'),
    BIG_POINT('O'),
    GHOST_RED('R'),
    GHOST_BLUE('B'),
    GHOST_PINK('P'),
    GHOST_YELLOW('Y'),
    PACMAN('S'),
    TELEPORT_ONE('1'),
    TELEPORT_TWO('2'),
    TELEPORT_THREE('3'),
    TELEPORT_FOUR('4'),
    TELEPORT_FIVE('5');

    private static List<MapElements> elements = new ArrayList<MapElements>();

    static {
        elements.add(WALL);
        elements.add(PATH);
        elements.add(PACMAN);
        elements.add(DOORS);
        elements.add(POINT);
        elements.add(BIG_POINT);
        elements.add(GHOST_RED);
        elements.add(GHOST_BLUE);
        elements.add(GHOST_PINK);
        elements.add(GHOST_YELLOW);
        elements.add(TELEPORT_ONE);
        elements.add(TELEPORT_TWO);
        elements.add(TELEPORT_THREE);
        elements.add(TELEPORT_FOUR);
        elements.add(TELEPORT_FIVE);
    }

    private char symbol;

    MapElements(char symbol) {
        this.symbol = symbol;
    }

    public static MapElements getMapElement(char symbol) {
        Optional<MapElements> result = elements.stream().filter(p -> p.getSymbol() == symbol).findFirst();
        if (result.isPresent())
            return result.get();
        return PATH;
    }

    public char getSymbol() {
        return symbol;
    }
}
