package tasks.hackerrank;

import java.util.PriorityQueue;

public class RunningMedian {
    public RunningMedian(int capacity) {
        this.leftMaxHeap = new PriorityQueue<>((capacity / 2) + 1, (a, b) -> b - a);
        this.rightMinHeap = new PriorityQueue<>();
    }

    private PriorityQueue<Integer> leftMaxHeap;
    private PriorityQueue<Integer> rightMinHeap;

    public double addItemAndGetMedian(int item) {
        if (leftMaxHeap.isEmpty() || item < leftMaxHeap.peek()) {
            leftMaxHeap.add(item);
        } else {
            rightMinHeap.add(item);
        }
        equalize();
        return getMedian();
    }

    private void equalize() {
        if (leftMaxHeap.size() > rightMinHeap.size() + 1) {
            rightMinHeap.add(leftMaxHeap.poll());
        } else if (rightMinHeap.size() > leftMaxHeap.size()) {
            leftMaxHeap.add(rightMinHeap.poll());
        }
    }

    private double getMedian() {
        if (leftMaxHeap.size() == rightMinHeap.size()) {
            return ((double) leftMaxHeap.peek() + (double) rightMinHeap.peek()) / 2;
        } else {
            return leftMaxHeap.peek();
        }
    }
}
