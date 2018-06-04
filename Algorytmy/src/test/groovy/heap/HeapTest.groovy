package heap

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.rules.ExpectedException

class HeapTest {

    @Test
    void heapify() {
        def array = [6, 5, 4, 3, 2, 1] as List<Integer>
        def heap = new Heap<Integer>(array)
        assert 1 == heap.extract()
        assert 2 == heap.extract()
        assert 3 == heap.extract()
        assert 4 == heap.extract()
        assert 5 == heap.extract()
        assert 6 == heap.extract()
        Assertions.assertThrows(NoSuchElementException.class, { heap.extract() })
    }
}
