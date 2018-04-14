import edu.princeton.cs.algs4.Picture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnergyTest {
    @Test
    void testEnergy() {
        Picture picture = new Picture(ClassLoader.getSystemResource("3x4.png").getFile());
        SeamCarver seamCarver = new SeamCarver(picture);
        assertEquals(Math.sqrt(52024), seamCarver.energy(1, 2));
        assertEquals(Math.sqrt(52225), seamCarver.energy(1, 1));
    }
}
