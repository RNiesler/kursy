package tasks.task1

class Node<T> {
    Node(T value) {
        this.value = value
    }
    T value
    Node left
    Node right
}

class Solution {
    List<Node> globalMax = []
    int globalMaxValue = Integer.MIN_VALUE

    List<Node> maxPath(Node node) {
        maxPathInternal(node)
        return globalMax
    }

    protected List<Node> maxPathInternal(Node node) {
        if (!node) {
            return []
        } else {
            def leftPath = maxPathInternal(node.left)
            def leftPathValue = leftPath.collect { it.value }.sum(0)
            def rightPath = maxPathInternal(node.right)
            def rightPathValue = rightPath.collect { it.value }.sum(0)
            if (leftPathValue + rightPathValue + node.value > globalMaxValue) {
                globalMaxValue = leftPathValue + rightPathValue + node.value
                globalMax = leftPath.collect().reverse() + [node] + rightPath
            }
            return [node] + (rightPathValue > leftPathValue ? rightPath : leftPath)
        }
    }
}

def root1 = new Node(1)
root1.left = new Node(2)
root1.right = new Node(3)
root1.left.left = new Node(4)
root1.left.right = new Node(5)
root1.right.left = new Node(6)
root1.right.right = new Node(7)

def longestPath1 = new Solution().maxPath(root1)
assert longestPath1.collect { it.value }.sum() == 18


def root2 = new Node(-100)
root2.left = new Node(2)
root2.right = new Node(3)
root2.left.left = new Node(4)
root2.left.right = new Node(5)
root2.right.left = new Node(6)
root2.right.right = new Node(7)

def longestPath2 = new Solution().maxPath(root2)
assert longestPath2.collect { it.value }.sum() == 16