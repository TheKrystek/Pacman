package pl.swidurski.pacman.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2016-04-10.
 */
public enum Orientation {
    NORTH,
    SOUTH,
    WEST,
    EAST,
    NONE;


    private static List<Orientation> orientations = new ArrayList<>();

    static {
        orientations.add(NORTH);
        orientations.add(SOUTH);
        orientations.add(WEST);
        orientations.add(EAST);

        NORTH.setOposite(SOUTH);
        SOUTH.setOposite(NORTH);
        EAST.setOposite(WEST);
        WEST.setOposite(EAST);
    }

    private Orientation oposite;

    public static List<Orientation> getOrientations() {
        return orientations;
    }

    public static List<Orientation> getOrientationsWithout(Orientation... orientations) {
        List<Orientation> result = new ArrayList<>();

        for (int j = 0; j < orientations.length; j++) {
            if (orientations[j] == null)
                continue;
            for (Orientation orientation : Orientation.orientations)
                if (orientations[j] != orientation)
                    result.add(orientation);
        }
        return result;
    }

    public Orientation getOposite() {
        return oposite;
    }

    public void setOposite(Orientation oposite) {
        this.oposite = oposite;
    }
}
