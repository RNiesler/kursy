package tasks.hackerrank;

public class LinkedListCycle {
    public static class Node {
        int data;
        Node next;
    }

    public static boolean hasCycle(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        Node slowNode = head.next;
        Node fastNode = head.next.next;
        while (slowNode != fastNode) {
            slowNode = slowNode.next;
            if (fastNode.next == null || fastNode.next.next == null) {
                break;
            } else {
                fastNode = fastNode.next.next;
            }
        }
        return slowNode == fastNode;
    }
}
