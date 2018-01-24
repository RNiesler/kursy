import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class InputTest {
    @ParameterizedTest
    @CsvSource({"synsets3.txt, 3",
            "synsets6.txt, 6",
            "synsets8.txt, 8",
            "synsets11.txt, 11",
            "synsets15.txt, 15",
    })
    @Disabled
    void nounsSizeTest(String synset, int expectedSize) {
        String synsetsPath = ClassLoader.getSystemResource(synset).getFile().toString();
        String hypernymsPath = ClassLoader.getSystemResource("empty.txt").getFile().toString();
        WordNet wordNet = new WordNet(synsetsPath, hypernymsPath);
        long size = wordNet.nouns().spliterator().getExactSizeIfKnown();
        assertEquals(expectedSize, size);
    }

    @ParameterizedTest
    @CsvSource({"synsets100-subgraph.txt, hypernyms100-subgraph.txt",
            "synsets500-subgraph.txt, hypernyms500-subgraph.txt",
            "synsets1000-subgraph.txt, hypernyms1000-subgraph.txt",
            "synsets5000-subgraph.txt, hypernyms5000-subgraph.txt",
            "synsets10000-subgraph.txt, hypernyms10000-subgraph.txt",
            "synsets50000-subgraph.txt, hypernyms50000-subgraph.txt",
            "synsets.txt, hypernyms.txt"})
    void synsetInputTest(String synsetFile, String hypernymsFile) {
        String synsetsPath = ClassLoader.getSystemResource(synsetFile).getFile().toString();
        String hypernymsPath = ClassLoader.getSystemResource(hypernymsFile).getFile().toString();
        new WordNet(synsetsPath, hypernymsPath);
    }

    @Test
    @Disabled
    void nounsTest() {
        String synsetsPath = ClassLoader.getSystemResource("synsets3.txt").getFile().toString();
        String hypernymsPath = ClassLoader.getSystemResource("empty.txt").getFile().toString();
        WordNet wordNet = new WordNet(synsetsPath, hypernymsPath);
        assertAll(() -> assertTrue(wordNet.isNoun("a")),
                () -> assertTrue(wordNet.isNoun("b")),
                () -> assertTrue(wordNet.isNoun("c")));
    }

    @Test
    void correctSplitTest() {
        String synsetsPath = ClassLoader.getSystemResource("synsets100-subgraph.txt").getFile().toString();
        String hypernymsPath = ClassLoader.getSystemResource("hypernyms100-subgraph.txt").getFile().toString();
        WordNet wordNet = new WordNet(synsetsPath, hypernymsPath);
        assertAll(() -> assertTrue(wordNet.isNoun("keratin")),
                () -> wordNet.isNoun("ceratin"));

    }

    @Test
    void cycleTest() {
        String synsetsPath = ClassLoader.getSystemResource("synsets3.txt").getFile().toString();
        String hypernymsPath = ClassLoader.getSystemResource("hypernyms3InvalidCycle.txt").getFile().toString();
        assertThrows(IllegalArgumentException.class, () -> new WordNet(synsetsPath, hypernymsPath));
    }

    @Test
    void twoRootsTest() {
        String synsetsPath = ClassLoader.getSystemResource("synsets3.txt").getFile().toString();
        String hypernymsPath = ClassLoader.getSystemResource("hypernyms3InvalidTwoRoots.txt").getFile().toString();
        assertThrows(IllegalArgumentException.class, () -> new WordNet(synsetsPath, hypernymsPath));
    }

}
