package com.mememan.persistency.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class CollectionUtil {

    private CollectionUtil() {
        throw new IllegalAccessError("Attempted to construct a Util Class!");
    }

    /**
     * Merges (or "zips") lists into a map while maintaining K-V pair-ordering. Shoutout to that one dude on SO for helping me out:
     * <a href="https://stackoverflow.com/questions/1839668/what-is-the-best-way-to-combine-two-lists-into-a-map-java">...</a>.
     *
     * @param keys The key list that represents the key set of the resulting map.
     * @param values The value list that represents the value set of the resulting map.
     *
     * @return A new {@link Map} comprised of the keys and values specified in both aforementioned lists, maintaining K-V pair-ordering according to index.
     *
     * @param <K> Key object type.
     * @param <V> Value object type.
     */
    public static <K, V> Map<K, V> zipToMap(List<K> keys, List<V> values) {
        Iterator<K> keyIter = keys.iterator();
        Iterator<V> valIter = values.iterator();

        return IntStream.range(0, keys.size()).boxed().collect(Collectors.toMap(nextKey -> keyIter.next(), nextVal -> valIter.next()));
    }
}
