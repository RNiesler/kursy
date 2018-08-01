package tasks.hackerrank;

import java.util.Optional;

public class Trie {
    private final static int ALPHABET_SIZE = 26;
    private static class Node {
        Node[] children = new Node[ALPHABET_SIZE];
        boolean isCompleteWord;
        int childCount;

        void add(String name, int start) {
            if(start == name.length()) {
                isCompleteWord = true;
                childCount = 1;
            } else {
                int charIndex = getCharIndex(name.charAt(start));
                if(children[charIndex] == null) {
                    children[charIndex] = new Node();
                }
                children[charIndex].add(name, start+1);
                childCount++;
            }
        }

        Optional<Node> getChild(String name, int start) {
            if(start == name.length()) {
                return Optional.of(this);
            } else {
                int charIndex = getCharIndex(name.charAt(start));
                if(children[charIndex] != null) {
                    return children[charIndex].getChild(name, start+1);
                } else {
                    return Optional.empty();
                }
            }
        }

        private int getCharIndex(char c) {
            return c % 'a';
        }
    }

    private Node root = new Node();

    public void add(String name) {
        root.add(name, 0);
    }

    public int countCompleteWordsStartingWith(String partial) {
        return root.getChild(partial, 0)
                .map(node -> node.childCount)
                .orElse(0);
    }
}
