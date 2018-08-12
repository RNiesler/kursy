package tasks.g4g;

import java.util.PriorityQueue;

public class IntegerStreamMedian {
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public void add(Integer next) {
        if (minHeap.isEmpty() || next < minHeap.peek()) {
            minHeap.add(next);
        } else {
            maxHeap.add(next);
        }
        equalize();
    }

    private void equalize() {
        if (maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            return minHeap.peek();
        }
    }
}
