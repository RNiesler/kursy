
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    private final HashMap<String, Set<Integer>> nouns = new HashMap<>();
    private final ArrayList<String[]> synsets = new ArrayList<>();
    private final Digraph digraph;
    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsetsFile, String hypernymsFile) {
        checkNonNullArgument(synsetsFile);
        checkNonNullArgument(hypernymsFile);
        In synsetsIn = new In(synsetsFile);
        if (!synsetsIn.exists()) {
            throw new IllegalArgumentException("Cannot read " + synsetsFile);
        }
        In hypernymsIn = new In(hypernymsFile);
        if (!hypernymsIn.exists()) {
            throw new IllegalArgumentException("Cannot read " + hypernymsFile);
        }

        try {
            while (synsetsIn.hasNextLine()) {
                String line = synsetsIn.readLine();
                String[] lineElems = line.split(",");
                int synsetId = Integer.valueOf(lineElems[0]);
                if (lineElems.length < 3) {
                    throw new IllegalArgumentException("Illegal format of the line in " + synsetsFile + ": " + line);
                }
                String[] synonyms = lineElems[1].split(" ");
                synsets.add(synonyms);
                for (String synonym : synonyms) {
                    addNounMapping(synonym, synsetId);
                }
            }
        } finally {
            synsetsIn.close();
        }

        digraph = new Digraph(synsets.size());

        try {
            while (hypernymsIn.hasNextLine()) {
                String line = hypernymsIn.readLine();
                String[] lineElems = line.split(",");
                if (lineElems.length > 1) { // ignore empty lines
                    for (int i = 1; i < lineElems.length; i++) {
                        digraph.addEdge(Integer.valueOf(lineElems[0]), Integer.valueOf(lineElems[i]));
                    }
                }
            }
        } finally {
            hypernymsIn.close();
        }

        Digraph reverse = digraph.reverse();
        Topological topologicalOrder = new Topological(reverse);
        if (!topologicalOrder.hasOrder()) {
            throw new IllegalArgumentException("The graph is not a DAG");
        } else {
            // check if the node is a root
            int potentialRoot = topologicalOrder.order().iterator().next();
            BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(reverse, potentialRoot);
            for (int i = 0; i < reverse.V(); i++) {
                if (!bfs.hasPathTo(i)) {
                    throw new IllegalArgumentException("The graph has no single root");
                }
            }
        }
        sap = new SAP(digraph);
    }

    private void addNounMapping(final String noun, final int synsetId) {
        if (!nouns.containsKey(noun)) {
            nouns.put(noun, new HashSet<>());
        }
        nouns.get(noun).add(synsetId);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        checkNonNullArgument(word);
        return nouns.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        checkNonNullArgument(nounA);
        checkNonNullArgument(nounB);
        if (!isNoun(nounA)) {
            throw new IllegalArgumentException("No such noun in the word net: " + nounA);
        }
        if (!isNoun(nounB)) {
            throw new IllegalArgumentException("No such noun in the word net: " + nounB);
        }
        return sap.length(nouns.get(nounA), nouns.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        checkNonNullArgument(nounA);
        checkNonNullArgument(nounB);
        if (!isNoun(nounA)) {
            throw new IllegalArgumentException("No such noun in the word net: " + nounA);
        }
        if (!isNoun(nounB)) {
            throw new IllegalArgumentException("No such noun in the word net: " + nounB);
        }
        return synsets.get(sap.ancestor(nouns.get(nounA), nouns.get(nounB)))[0];    // TODO what if -1?
    }

    private static void checkNonNullArgument(String arg) {
        if (arg == null) {
            throw new IllegalArgumentException("The argument cannot be null");
        }
    }
}