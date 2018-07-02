package datastructures.graph;

import java.util.*;
import java.util.stream.Collectors;

public class LinkedGraph<T> implements Graph<T> {
    public class Node implements Graph.Node<T> {
        private T value;
        private List<Edge<T>> adjacentList;

        Node(T value) {
            this.value = value;
            adjacentList = new LinkedList<>();
        }

        @Override
        public List<Edge<T>> getAdjacent() {
            return adjacentList;
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public boolean hasEdgeTo(Graph.Node<T> target) {
            return adjacentList.stream().anyMatch(edge -> edge.getTarget().equals(target));
        }
    }

    public LinkedGraph() {
        this.nodes = new LinkedList<>();
    }

    public LinkedGraph(Set<? extends T> set) {
        this.nodes = set.stream()
                .map(value -> new Node(value))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private LinkedList<Node> nodes;

    @Override
    public void insert(T value) {
        if (!contains(value)) {
            nodes.add(new Node(value));
        }
    }

    @Override
    public void addEdge(T v1, T v2, int weight) {
        Node n1 = getNode(v1).orElseThrow(() ->
                new IllegalArgumentException("Cannot add edge for a value that is not in the graph: " + v1));
        Node n2 = getNode(v2).orElseThrow(() ->
                new IllegalArgumentException("Cannot add edge for a value that is not in the graph: " + v1));
        Edge<T> edge = new Edge<T>(n1, n2, weight);
        addEdge(edge);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (edge.getSource().hasEdgeTo(edge.getTarget())) {
            throw new IllegalArgumentException("The edge is already present in the graph");
        } else {
            edge.getSource().getAdjacent().add(edge);
        }

    }

    protected Optional<Node> getNode(T value) {
        return nodes.stream().filter(node -> value.equals(node.getValue())).findFirst();
    }

    @Override
    public void remove(T value) {
        getNode(value).ifPresent(node -> {
            nodes.stream().filter(innerNode -> innerNode.hasEdgeTo(node))
                    .forEach(innerNode -> innerNode.getAdjacent().removeIf(edge -> edge.getTarget().equals(node)));
            nodes.remove(node);
        });
    }

    @Override
    public boolean contains(T value) {
        return getNode(value).isPresent();
    }

    @Override
    public Iterable<T> getAll() {
        return nodes.stream().map(Node::getValue).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public List<Node> getNodes() {
        return nodes;
    }
}
