package heap

class Heap<T extends Comparable<T>> {
    protected List<T> heapStorage

    Heap() {
        heapStorage = new ArrayList<>()
    }

    Heap(Collection<T> array) {
        heapStorage = new ArrayList<>(array)
        (array.size() / 2..0).each { i ->
            heapify(i as int)
        }
    }

    void insert(T elem) {
        heapStorage.add(elem)
        siftUp(lastIndex())
    }

    T extract() {
        if (heapStorage.size() == 0) {
            throw new NoSuchElementException("The heap is empty")
        }
        T ret = heapStorage[0]
        heapStorage[0] = heapStorage[lastIndex()]
        heapStorage.remove(lastIndex())
        heapify(0)
        return ret
    }

    protected void siftUp(int i) {
        T elem = heapStorage[i]
        while (i > 0 && heapStorage[parent(i)].compareTo(elem) > 0) {
            heapStorage[i] = heapStorage[parent(i)]
            i = parent(i)
        }
        heapStorage[i] = elem
    }

    protected void heapify(int i) {
        T min = heapStorage[i]
        int ind = i
        if (leftChild(i) <= lastIndex() && heapStorage.get(leftChild(i)).compareTo(min) < 0) {
            ind = leftChild(i)
            min = heapStorage.get(ind)
        }
        if (rightChild(i) <= lastIndex() && heapStorage.get(rightChild(i)).compareTo(min) < 0) {
            ind = rightChild(i)
            min = heapStorage.get(ind)
        }
        if (ind != i) {
            heapStorage[ind] = heapStorage[i]
            heapStorage[i] = min
            heapify(ind)
        }
    }

    protected int lastIndex() {
        return heapStorage.size() - 1;
    }

    private static int leftChild(int i) {
        return i * 2 + 1
    }

    private static int rightChild(int i) {
        return i * 2 + 2
    }

    private static int parent(int i) {
        return (i - 1) / 2
    }
}
