package algorithms.graph;

import datastructures.graph.Graph;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Calculate shortest path in the graph with the Dijkstra's algorithm.
 * @param <T> Node type held by the graph
 */
public class Dijkstra<T> {
    private Map<T, Map<T, CalculationTuple<T>>> solutionCache = new HashMap<>();
    private Graph<T> graph;
    private static final int INFINITY = -1;

    private class CalculationTuple<T> implements Comparable<CalculationTuple<T>> {
        CalculationTuple(T value, int pathCost) {
            this.pathCost = pathCost;
            this.value = value;
        }

        T value;
        CalculationTuple<T> previous;
        int pathCost; // sum of edge weights
        boolean visited;

        @Override
        public int compareTo(CalculationTuple<T> o) {
            return pathCost - o.pathCost;
        }
    }

    private class ResultTuple<T> {
        List<T> path;
        int distance;
    }

    public Dijkstra(Graph<T> graph) {
        this.graph = graph;

    }

    public List<T> getShortestPath(T source, T target) throws NodeNotReachableException {
        return getShortestPathWithCache(source, target).path;
    }

    public int getShortestDistance(T source, T target) throws NodeNotReachableException {
        return getShortestPathWithCache(source, target).distance;
    }

    protected ResultTuple<T> getShortestPathWithCache(T source, T target) throws NodeNotReachableException {
        if (!solutionCache.containsKey(source)) {
            // cannot use computeIfAbsent because calculateShortestPath throws an exception
            solutionCache.put(source, calculateShortestPath(source, target));
        }
        Map<T, CalculationTuple<T>> solution = solutionCache.get(source);
        if (!solution.get(target).visited) {
            //continue calculation in a situation when the source is the same but the target differs
            calculateShortestPath(source, target, solution);
        }
        ResultTuple result = new ResultTuple();
        result.path = extractPathFromSolution(target, solution);
        result.distance = solution.get(target).pathCost;
        return result;
    }

    /**
     * Trace back calculations to return the path in order from the source to the target.
     */
    private List<T> extractPathFromSolution(T target, Map<T, CalculationTuple<T>> solution) {
        LinkedList<T> stack = new LinkedList<>();
        CalculationTuple<T> currentNode = solution.get(target);
        stack.push(currentNode.value);
        while (currentNode.pathCost > 0) {
            currentNode = currentNode.previous;
            stack.push(currentNode.value);
        }
        LinkedList<T> path = new LinkedList<>();
        while (!stack.isEmpty()) {
            path.add(stack.pop());
        }
        return path;
    }

    /**
     * Init empty solution and delegate to method filling it if needed.
     */
    protected Map<T, CalculationTuple<T>> calculateShortestPath(T source, T target) throws NodeNotReachableException {
        HashMap<T, CalculationTuple<T>> solution = new HashMap<>(graph.size());
        graph.getNodes().forEach(node -> solution.put(node.getValue(), new CalculationTuple<>(node.getValue(), INFINITY)));
        CalculationTuple<T> sourceCalculationTuple = solution.get(source);
        sourceCalculationTuple.pathCost = 0;
        return calculateShortestPath(source, target, solution);
    }

    /**
     * Fill existing solution (which might be empty) with calculation data. The actual algorithm.
     */
    protected Map<T, CalculationTuple<T>> calculateShortestPath(T source, T target, Map<T, CalculationTuple<T>> solution) throws NodeNotReachableException {
        PriorityQueue<CalculationTuple<T>> queue = new PriorityQueue<>();
        // the solution parameter contains partial solution based on which we recreate the state of the algorithm
        // at the end of previous calculation
        solution.entrySet().stream()
                .filter(entry -> entry.getValue().pathCost != INFINITY && !entry.getValue().visited)
                .map(Map.Entry::getValue)
                .forEach(queue::add);

        Map<T, Graph.Node<T>> nodeMap = graph.getNodes().stream()
                .collect(Collectors.toMap(Graph.Node<T>::getValue, Function.identity()));

        CalculationTuple<T> currentCalculation = queue.poll();
        while (currentCalculation != null && !target.equals(currentCalculation.value)) {
            currentCalculation.visited = true;
            updateAdjacentDistances(queue, nodeMap, solution, currentCalculation);
            currentCalculation = queue.poll();
        }
        if (currentCalculation != null) {
            currentCalculation.visited = true;
            // we still need to update distances to adjacent nodes for the future calculations
            updateAdjacentDistances(queue, nodeMap, solution, currentCalculation);
        } else { //TODO proper exception
            throw new NodeNotReachableException();
        }

        return solution;
    }

    private void updateAdjacentDistances(PriorityQueue<CalculationTuple<T>> queue, Map<T, Graph.Node<T>> nodeMap,
                                         Map<T, CalculationTuple<T>> solution, CalculationTuple<T> currentCalculation) {
        Graph.Node<T> graphNode = nodeMap.get(currentCalculation.value);

        for (Graph.Edge<T> edge : graphNode.getAdjacent()) {
            CalculationTuple<T> calculationTuple = solution.get(edge.getTarget().getValue());
            if (!calculationTuple.visited) {
                if (edge.getWeight() < 0) {
                    throw new IllegalArgumentException("The graph cannot have negative weighted edges.");
                }
                int newCost = currentCalculation.pathCost + edge.getWeight();
                if (calculationTuple.pathCost == INFINITY || calculationTuple.pathCost > newCost) {
                    calculationTuple.pathCost = newCost;
                    calculationTuple.previous = currentCalculation;
                    // here we want to update the priority which is not supported by PriorityQueue implementation
                    queue.remove(calculationTuple);
                    queue.add(calculationTuple);
                }
            }
        }
    }

    public static class NodeNotReachableException extends Exception {
    }
}
