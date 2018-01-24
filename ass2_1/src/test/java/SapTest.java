import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class SapTest {
    @Test
    void testDigraph1() throws URISyntaxException, MalformedURLException {
        In in = new In(ClassLoader.getSystemResource("digraph1.txt").toURI().toURL());
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        assertEquals(4, sap.length(3, 11));
        assertEquals(1, sap.ancestor(3, 11));
    }

    @Test
    void testDigraph2() throws URISyntaxException, MalformedURLException {
        In in = new In(ClassLoader.getSystemResource("digraph2.txt").toURI().toURL());
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        assertEquals(2, sap.length(1, 5));
        assertEquals(0, sap.ancestor(1, 5));
    }

    @Test
    void testDigraph3() throws URISyntaxException, MalformedURLException {
        In in = new In(ClassLoader.getSystemResource("digraph3.txt").toURI().toURL());
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        assertEquals(2, sap.length(1, 5));
        assertEquals(1, sap.ancestor(1, 5));
    }

    @Test
    void testDigraph4() throws URISyntaxException, MalformedURLException {
        In in = new In(ClassLoader.getSystemResource("digraph3.txt").toURI().toURL());
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        assertEquals(2, sap.length(1, 5));
        assertEquals(1, sap.ancestor(1, 5));
    }
}
