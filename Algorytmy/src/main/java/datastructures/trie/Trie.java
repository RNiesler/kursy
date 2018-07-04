package datastructures.trie;

import java.util.Optional;

/**
 * Trie datastructure interface
 * @param <T> Element type
 * @param <U> Type of elements that the main type can be split into (for example Character for String)
 */
public interface Trie<T, U> {
    interface Splitter<V, W> {
        Iterable<W> split(V t);

        V join(Iterable<W> segments);
    }

    boolean contains(T t);

    Optional<T> get(T t);

    boolean hasPrefix(T t);

    void put(T t);

    int size();
}
