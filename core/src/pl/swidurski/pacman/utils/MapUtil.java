package pl.swidurski.pacman.utils;

import java.util.*;

public class MapUtil {
    public static <K, V extends Comparable<? super V>> List<K>
    sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

        List<K> result = new ArrayList<>();
        for (Map.Entry<K, V> entry : list)
            result.add(entry.getKey());
        return result;
    }
}