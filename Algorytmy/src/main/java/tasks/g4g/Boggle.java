package tasks.g4g;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;


public class Boggle {
    /**
     * not thread safe
     **/
    public static class Dfs {
        public interface Dictionary {
            boolean isWord(String word);
        }

        private final Dictionary dictionary;
        private final char[][] board;
        private boolean[][] visited;
        private Set<String> result;

        public Dfs(char[][] board, Dictionary dictionary) {
            Objects.requireNonNull(board);
            Objects.requireNonNull(dictionary);
            this.dictionary = dictionary;
            this.board = board;
            this.visited = new boolean[board.length][board.length];
        }

        public Set<String> solve() {
            if (result == null) {
                result = new HashSet<>();
                char[] prefixBuffer = new char[board.length * board.length];
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        boggleVisit(i, j, prefixBuffer, 0);
                    }
                }
            }
            return new HashSet<>(result); //return copy
        }


        private void boggleVisit(int row, int column, char[] prefix, int prefixLength) {
            if (row >= 0 && row < board.length && column >= 0 && column < board.length && !visited[row][column]) {
                visited[row][column] = true;
                prefix[prefixLength] = board[row][column];
                String word = new String(prefix, 0, prefixLength + 1);
                if (dictionary.isWord(word)) {
                    result.add(word);
                }
                boggleVisit(row - 1, column - 1, prefix, prefixLength + 1);
                boggleVisit(row - 1, column, prefix, prefixLength + 1);
                boggleVisit(row - 1, column + 1, prefix, prefixLength + 1);
                boggleVisit(row, column - 1, prefix, prefixLength + 1);
                boggleVisit(row, column + 1, prefix, prefixLength + 1);
                boggleVisit(row + 1, column - 1, prefix, prefixLength + 1);
                boggleVisit(row + 1, column, prefix, prefixLength + 1);
                boggleVisit(row + 1, column + 1, prefix, prefixLength + 1);
                visited[row][column] = false;
            }
        }
    }

    public static class BoggleTrie {
        interface Trie {
            interface Node {
                boolean isWord();

                Optional<? extends Node> getChildNode(char c);
            }

            Node getRoot();
        }

        static class TrieImpl implements Trie {
            static class NodeImpl implements Trie.Node {
                boolean leaf;
                Map<Character, NodeImpl> children = new HashMap<>();

                public boolean isWord() {
                    return leaf;
                }

                void add(char[] charBuffer, int start) {
                    if (start == charBuffer.length) {
                        leaf = true;
                    } else {
                        char c = charBuffer[start];
                        if (!children.containsKey(c)) {
                            children.put(c, new NodeImpl());
                        }
                        children.get(c).add(charBuffer, start + 1);
                    }
                }

                public Optional<NodeImpl> getChildNode(char c) {
                    return Optional.ofNullable(children.get(c));
                }
            }

            private NodeImpl root = new NodeImpl();

            public TrieImpl(String[] dictionary) {
                Objects.requireNonNull(dictionary);
                for (String word : dictionary) {
                    root.add(word.toCharArray(), 0);
                }
            }

            public NodeImpl getRoot() {
                return root;
            }
        }

        private final Trie trie;
        private final char[][] board;
        private boolean[][] visited;
        private Set<String> result;

        public BoggleTrie(char[][] board, String[] dictionary) {
            this.visited = new boolean[board.length][board.length];
            this.trie = new TrieImpl(dictionary);
            this.board = board;
        }

        public Set<String> solve() {
            if (result == null) {
                result = new HashSet<>();
                char[] prefixBuffer = new char[board.length * board.length];
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        boggleVisit(i, j, prefixBuffer, 0, trie.getRoot());
                    }
                }
            }
            return new HashSet<>(result);
        }

        private void boggleVisit(int row, int column, char[] prefix, int prefixLength, Trie.Node prefixNode) {
            if (row >= 0 && row < board.length && column >= 0 && column < board.length && !visited[row][column]) {
                visited[row][column] = true;
                prefix[prefixLength] = board[row][column];
                Optional<? extends Trie.Node> nodeOptional = prefixNode.getChildNode(prefix[prefixLength]);
                if (nodeOptional.isPresent()) {
                    Trie.Node node = nodeOptional.get();
                    if (node.isWord()) {
                        result.add(new String(prefix, 0, prefixLength + 1));
                    }
                    boggleVisit(row - 1, column - 1, prefix, prefixLength + 1, node);
                    boggleVisit(row - 1, column, prefix, prefixLength + 1, node);
                    boggleVisit(row - 1, column + 1, prefix, prefixLength + 1, node);
                    boggleVisit(row, column - 1, prefix, prefixLength + 1, node);
                    boggleVisit(row, column + 1, prefix, prefixLength + 1, node);
                    boggleVisit(row + 1, column - 1, prefix, prefixLength + 1, node);
                    boggleVisit(row + 1, column, prefix, prefixLength + 1, node);
                    boggleVisit(row + 1, column + 1, prefix, prefixLength + 1, node);

                }
                visited[row][column] = false;
            }
        }
    }
}