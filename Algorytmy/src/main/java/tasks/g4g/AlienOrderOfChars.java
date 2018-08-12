package tasks.g4g;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

//Given a sorted dictionary (array of words) of an alien language, find order of characters in the language.
public class AlienOrderOfChars {
    private static class Graph {
        private enum State {
            UNMARKED, PROCESSING, FINISHED;
        }

        private static class Vertex {
            Vertex(char c) {
                this.c = c;
            }

            char c;
            List<Vertex> successors = new LinkedList<>();
            State state = State.UNMARKED;

        }

        private Function<Character, Vertex> vertexMapper = key -> new Vertex(key);

        HashMap<Character, Vertex> vertices = new HashMap<>();

        void addVertexIfAbsent(char c) {
            vertices.computeIfAbsent(c, vertexMapper);
        }

        void addEdge(char predecessor, char successor) {
            Vertex predNode = vertices.computeIfAbsent(predecessor, vertexMapper);
            Vertex succNode = vertices.computeIfAbsent(successor, vertexMapper);
            predNode.successors.add(succNode);
        }

        List<Character> topologicalSort() {
            LinkedList<Vertex> stack = new LinkedList<>();
            for (Vertex vertex : vertices.values()) {
                if (vertex.state == State.UNMARKED) {
                    topologicalDfs(vertex, stack);
                }
            }
            Collections.reverse(stack);
            return stack.stream().map(v -> v.c).filter(c -> c != 0).collect(Collectors.toList());
        }

        private void topologicalDfs(Vertex vertex, LinkedList<Vertex> stack) {
            vertex.state = State.PROCESSING;
            for (Vertex successor : vertex.successors) {
                if (successor.state == State.UNMARKED) {
                    topologicalDfs(successor, stack);
                } else if (successor.state == State.PROCESSING) {
                    throw new IllegalStateException("Cannot sort - the graph has cycles");
                }
            }
            stack.add(vertex);
            vertex.state = State.FINISHED;
        }
    }

    public static Character[] charOrder(final String[] dictionary) {
        Objects.requireNonNull(dictionary);
        Graph graph = new Graph();
        buildGraph(dictionary, graph, 0, 0, dictionary.length);
        return graph.topologicalSort().toArray(new Character[0]);
    }

    private static void buildGraph(final String[] dictionary, final Graph graph, final int positionInWord, final int bucketStart, final int bucketEnd) {
        char previous = 0;
        int subBucketStart = bucketStart;
        for (int i = bucketStart; i < bucketEnd; i++) {
            char charAtPos = 0;
            if (positionInWord < dictionary[i].length()) {
                charAtPos = dictionary[i].charAt(positionInWord);
                if (charAtPos != previous) {
                    buildGraph(dictionary, graph, positionInWord + 1, subBucketStart, i);
                    subBucketStart = i;
                    graph.addEdge(previous, charAtPos);
                    previous = charAtPos;
                }
            }
        }
        if(previous != 0) {
            buildGraph(dictionary, graph, positionInWord + 1, subBucketStart, bucketEnd);
        }
    }

}
