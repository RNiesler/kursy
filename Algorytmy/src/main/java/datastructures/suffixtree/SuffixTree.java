package datastructures.suffixtree;

import datastructures.trie.CompressedTrie;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SuffixTree extends CompressedTrie<String, Character> {
    static class StringSplitter implements Splitter<String, Character> {

        @Override
        public Iterable<Character> split(String str) {
            return str.chars().mapToObj(charInt -> Character.valueOf((char) charInt)).collect(Collectors.toList());
        }

        @Override
        public String join(Iterable<Character> segments) {
            return StreamSupport.stream(segments.spliterator(), false)
                    .collect(StringBuilder::new, (stringBuilder, character) -> stringBuilder.append(character),
                            (builder, subBuilder) -> builder.append(subBuilder))
                    .toString();
        }
    }

    public SuffixTree() {
        super(new StringSplitter());
    }

    public SuffixTree(Iterable<String> values) {
        super(values, new StringSplitter());
    }

    @Override
    public void put(String str) {
        for (int i = 0; i < str.length(); i++) {
            super.put(str.substring(i));
        }
    }
}
