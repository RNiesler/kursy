import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;

public class SAP {

    private final Digraph digraph;
    private final ArrayList<BfsEntity> bfsEntities;

    private static class BfsEntity {
        int vertex;
        HashMap<Integer, Integer> bfsMap; // key - source vertex, value - distance

        BfsEntity(int vertex) {
            this.vertex = vertex;
            this.bfsMap = new HashMap<>();
            bfsMap.put(vertex, 0);
        }

        boolean containsPathToAny(Iterable<Integer> vertices) {
            Iterator<Integer> it = vertices.iterator();
            while (it.hasNext()) {
                if (bfsMap.containsKey(it.next())) {
                    return true;
                }
            }
            return false;
        }

        int minDistanceToAny(Iterable<Integer> vertices) {
            Iterator<Integer> it = vertices.iterator();
            int currentDistance = Integer.MAX_VALUE;
            while (it.hasNext()) {
                int currentVertex = it.next();
                if (bfsMap.containsKey(currentVertex) && bfsMap.get(currentVertex) < currentDistance) {
                    currentDistance = bfsMap.get(currentVertex);
                }
            }
            return currentDistance;
        }
    }

    private static class SapResult {
        int vertex;
        int distance;
    }

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph digraph) {
        if (digraph == null) {
            throw new IllegalArgumentException("Digraph cannot be null");
        }
        this.digraph = digraph;
        this.bfsEntities = new ArrayList<>(digraph.V());
        for (int i = 0; i < digraph.V(); i++) {
            this.bfsEntities.add(new BfsEntity(i));
        }
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return length(Arrays.asList(v), Arrays.asList(w));
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return ancestor(Arrays.asList(v), Arrays.asList(w));
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        SapResult result = calculateSap(v, w);
        if (result.vertex == -1) {
            return -1;
        } else {
            return result.distance;
        }
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return calculateSap(v, w).vertex;
    }

    private SapResult calculateSap(Iterable<Integer> v, Iterable<Integer> w) {
        Iterator<Integer> vIt = v.iterator();
        Iterator<Integer> wIt = w.iterator();
        while (vIt.hasNext()) {
            int vertex = vIt.next();
            doBfs(vertex);
        }
        while (wIt.hasNext()) {
            int vertex = wIt.next();
            doBfs(vertex);
        }
        SapResult result = new SapResult();
        result.vertex = -1;
        result.distance = Integer.MAX_VALUE;
        for (BfsEntity bfsEntity : bfsEntities) {
            if (bfsEntity.containsPathToAny(v) && bfsEntity.containsPathToAny(w)) {
                int distanceToV = bfsEntity.minDistanceToAny(v);
                int distanceToW = bfsEntity.minDistanceToAny(w);
                if (distanceToV + distanceToW < result.distance) {
                    result.vertex = bfsEntity.vertex;
                    result.distance = distanceToV + distanceToW;
                }
            }
        }
        return result;
    }

    private void doBfs(int vertex) {
        Queue<BfsEntity> bfsQueue = new LinkedList<>();
        bfsQueue.add(bfsEntities.get(vertex));
        while (!bfsQueue.isEmpty()) {
            BfsEntity currentVertex = bfsQueue.remove();
            int currentDistance = currentVertex.bfsMap.get(vertex);
            for (int adjVertex : digraph.adj(currentVertex.vertex)) {
                BfsEntity adjVertexEntity = bfsEntities.get(adjVertex);
                if (!adjVertexEntity.bfsMap.containsKey(vertex) ||
                        adjVertexEntity.bfsMap.get(vertex) > currentDistance + 1) {
                    adjVertexEntity.bfsMap.put(vertex, currentDistance + 1);
                    bfsQueue.add(adjVertexEntity);
                }
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
