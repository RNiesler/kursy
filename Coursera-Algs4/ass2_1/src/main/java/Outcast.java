import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Outcast {
    private final WordNet wordNet;

    /**
     * constructor takes a WordNet object
     *
     * @param wordnet
     */
    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;
    }

    /**
     * given an array of WordNet nouns, return an outcast
     *
     * @param nouns
     * @return
     */
    public String outcast(String[] nouns) {
        if (nouns.length < 2) {
            throw new IllegalArgumentException("At least two nouns are needed to calculate the outcast.");
        }
        int[] distances = new int[nouns.length];
        for (int i = 0; i < nouns.length; i++) {
            String iNoun = nouns[i];
            distances[i] = Arrays.stream(nouns)
                    .filter(noun -> !noun.equals(iNoun))
                    .mapToInt(noun -> wordNet.distance(iNoun, noun))
                    .sum();
        }
        int outcastIndex = 0;
        int maxDistance = 0;
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] > maxDistance) {
                outcastIndex = i;
                maxDistance = distances[i];
            }
        }
        return nouns[outcastIndex];
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}