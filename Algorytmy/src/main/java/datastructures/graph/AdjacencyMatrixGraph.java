package datastructures.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdjacencyMatrixGraph<T> implements Graph<T> {
    public class Node implements Graph.Node<T> {
        private int nodeIndex;
        private T value;

        Node(int nodeIndex, T value) {
            this.nodeIndex = nodeIndex;
            this.value = value;
        }

        @Override
        public List<Edge<T>> getAdjacent() {
            return IntStream.range(0, nodesCount)
                    .filter(i -> adjacencyMatrix[nodeIndex][i] != 0)
                    .mapToObj(i -> {
                        Node node = (Node) nodes[i];
                        return new Edge<T>(this, node, adjacencyMatrix[nodeIndex][i]);
                    })
                    .collect(Collectors.toList());
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public boolean hasEdgeTo(Graph.Node<T> target) {
            return adjacencyMatrix[nodeIndex][getNodeIndex(target.getValue())] != 0;
        }

        public void setIndex(int i) {
            this.nodeIndex = i;
        }
    }

    private static final int DEFAULT_INITIAL_MATRIX_CAPACITY = 10;

    private int[][] adjacencyMatrix;
    private Object[] nodes; // since Node is a generic type, we cannot instantiate Node[]
    private int nodesCount;
    private int capacity;

    public AdjacencyMatrixGraph() {
        this(DEFAULT_INITIAL_MATRIX_CAPACITY);
    }

    public AdjacencyMatrixGraph(int initialCapacity) {
        adjacencyMatrix = new int[initialCapacity][initialCapacity];
        nodes = new Object[initialCapacity];
        capacity = initialCapacity;
    }

    @Override
    public void insert(T value) {
        if (nodesCount == capacity) {
            grow();
        }

        Node newNode = new Node(nodesCount, value);
        nodes[nodesCount++] = newNode;
    }

    private void grow() {
        resize(2 * capacity);
    }

    private void shrink() {
        resize(capacity / 2);
    }

    private void resize(int newCapacity) {
        Object[] newNodes = new Object[newCapacity];
        int[][] newAdjacencyMatrix = new int[newCapacity][newCapacity];
        System.arraycopy(nodes, 0, newNodes, 0, capacity);
        for (int i = 0; i < capacity; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, newAdjacencyMatrix[i], 0, capacity);
        }
        nodes = newNodes;
        adjacencyMatrix = newAdjacencyMatrix;
        capacity = newCapacity;

    }

    protected int getNodeIndex(T value) {
        for (int i = 0; i < nodesCount; i++) {
            if (((Node) nodes[i]).getValue().equals(value)) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void addEdge(T v1, T v2, int weight) {
        int i1 = getNodeIndex(v1);
        int i2 = getNodeIndex(v2);
        adjacencyMatrix[i1][i2] = weight;
    }

    @Override
    public void addEdge(Edge<T> edge) {
        addEdge(edge.getSource().getValue(), edge.getTarget().getValue(), edge.getWeight());
    }

    @Override
    public void remove(T value) {
        int removedInd = getNodeIndex(value);
        for (int i = removedInd; i < nodesCount - 1; i++) {
            adjacencyMatrix[i] = adjacencyMatrix[i + 1];
            Node node = (Node) nodes[i + 1];
            node.setIndex(i);
            nodes[i] = node;
            for (int j = removedInd; j < nodesCount - 1; j++) {
                adjacencyMatrix[i][j] = adjacencyMatrix[i][j + 1];
            }
        }
        nodesCount--;

        //TODO avoid double arraycopy
        if (nodesCount < capacity / 4) {
            shrink();
        }
    }

    @Override
    public boolean contains(T value) {
        for (Object node : nodes) {
            if (((Node) node).getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<T> getAll() {
        return Arrays.stream(nodes).limit(nodesCount).map(node -> ((Node) node).getValue()).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return nodesCount;
    }

    @Override
    public Collection<Node> getNodes() {
        ArrayList<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodesCount; i++) {
            nodeList.add((Node) nodes[i]);
        }
        return nodeList;
    }
}
