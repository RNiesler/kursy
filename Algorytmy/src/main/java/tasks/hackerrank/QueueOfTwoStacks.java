package tasks.hackerrank;

import java.util.Stack;

public class QueueOfTwoStacks<T> {
    Stack<T> stackNewestOnTop = new Stack<T>();
    Stack<T> stackOldestOnTop = new Stack<T>();

    public void enqueue(T value) { // Push onto newest stack
        if (stackNewestOnTop.isEmpty() && stackOldestOnTop.isEmpty()) {
            stackOldestOnTop.push(value);
        } else {
            stackNewestOnTop.push(value);
        }
    }

    public T peek() {
        if (stackOldestOnTop.isEmpty()) {
            moveToOldestOnTop();
        }
        return stackOldestOnTop.peek();
    }

    private void moveToOldestOnTop() {
        while (!stackNewestOnTop.isEmpty()) {
            stackOldestOnTop.push(stackNewestOnTop.pop());
        }
    }

    public T dequeue() {
        T data = peek();
        stackOldestOnTop.pop();
        return data;
    }
}
