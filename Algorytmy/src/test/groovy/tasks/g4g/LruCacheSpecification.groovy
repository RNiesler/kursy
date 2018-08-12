package tasks.g4g

import spock.lang.Specification

class LruCacheSpecification extends Specification {
    void 'constructor'() {
        expect:
        new LruCache<String,String>(1) != null

        when:
        new LruCache<String, String>(0)
        then:
        thrown(IllegalArgumentException)
    }

    void 'size limit'() {
        setup:
        final cache = new LruCache<String, Integer>(1)

        when:
        cache.put('abc', 0);
        then:
        cache.size() == 1

        when:
        cache.put('def', 1)
        then:
        cache.size() == 1
        cache.get('def') == 1
        cache.get('abc') == null
    }

    void 'lru removed'() {
        setup:
        final cache = new LruCache<String, Integer>(2)
        cache.put('a', 1)
        cache.put('b', 2)

        when:
        cache.get('a')
        cache.put('c', 3)

        then:
        cache.size() == 2
        cache.get('a') == 1
        cache.get('c') == 3
        !cache.get('b')
    }

    void 'double put'() {
        setup:
        final cache = new LruCache<String, Integer>(2);
        cache.put('a',1)
        cache.put('b',2)

        when:
        cache.get('b')
        cache.put('a',3)
        then:
        cache.size() == 2
        cache.get('a') == 3
        cache.get('b') == 2
    }
}
