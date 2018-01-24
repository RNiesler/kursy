import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DistanceTest {
    @Test
    void digraph1() {
        String synsetsPath = ClassLoader.getSystemResource("synsets12.txt").getFile().toString();
        String hypernymsPath = ClassLoader.getSystemResource("digraph1cp.txt").getFile().toString();
        WordNet wordNet = new WordNet(synsetsPath, hypernymsPath);
        assertEquals(4, wordNet.distance("d", "l"));
        assertEquals("b", wordNet.sap("d", "l"));
    }


    @Test
    void digraph2() {
        String synsetsPath = ClassLoader.getSystemResource("synsets6.txt").getFile().toString();
        String hypernymsPath = ClassLoader.getSystemResource("digraph2cp.txt").getFile().toString();
        WordNet wordNet = new WordNet(synsetsPath, hypernymsPath);
        assertEquals(2, wordNet.distance("b", "f"));
        assertEquals("a", wordNet.sap("b", "f"));
    }
}
