package tasks.g4g;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

class LruCache<K, V> {
    private HashMap<K, V> internalMap = new HashMap<>();

    private LinkedList<K> queue = new LinkedList<>();
    private int limit;

    public LruCache(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("The size must be above 0");
        }
        this.limit = size;
    }

    public void put(K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        if (internalMap.containsKey(key)) {
            queue.remove(key);
        } else if (internalMap.size() == limit) {
            internalMap.remove(queue.removeFirst());
        }
        queue.addLast(key);
        internalMap.put(key, value);

    }

    public V get(K key) {
        Objects.requireNonNull(key);
        V value = internalMap.get(key);
        if (value != null) {
            queue.remove(key);
            queue.addLast(key);
        }
        return value;
    }

    public int size() {
        return internalMap.size();
    }
}