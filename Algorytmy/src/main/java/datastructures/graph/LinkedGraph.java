package datastructures.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LinkedGraph<T> implements Graph<T> {
    public class Node<T> implements Graph.Node<T> {
        private T value;
        private List<Node<T>> adjacentList;

        Node(T value) {
            this.value = value;
            adjacentList = new LinkedList<>();
        }

        @Override
        public List<Node<T>> getAdjacent() {
            return adjacentList;
        }

        @Override
        public T getValue() {
            return value;
        }
    }

    public LinkedGraph() {
        this.nodes = new LinkedList<>();
    }

    public LinkedGraph(Set<? extends T> set) {
        this.nodes = set.stream()
                .map(value -> new Node<T>(value))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private LinkedList<Node<T>> nodes;

    @Override
    public void insert(T value) {
        if (!contains(value)) {
            nodes.add(new Node(value));
        }
    }

    @Override
    public void addEdge(T v1, T v2) {
        Node<T> n1 = getNode(v1).orElseThrow(() ->
                new IllegalArgumentException("Cannot add edge for a value that is not in the graph: " + v1));
        Node<T> n2 = getNode(v2).orElseThrow(() ->
                new IllegalArgumentException("Cannot add edge for a value that is not in the graph: " + v1));

        if (n1.getAdjacent().contains(n2)) {
            throw new IllegalArgumentException("The edge is already present in the graph");
        } else {
            n1.getAdjacent().add(n2);
        }
    }

    protected Optional<Node<T>> getNode(T value) {
        return nodes.stream().filter(node -> value.equals(node.getValue())).findFirst();
    }

    @Override
    public void remove(T value) {
        getNode(value).ifPresent(node -> {
            nodes.stream().filter(innerNode -> innerNode.getAdjacent().contains(node))
                    .forEach(innerNode -> innerNode.getAdjacent().remove(node));
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
    public List<Node<T>> getNodes() {
        return nodes;
    }
}
