import edu.princeton.cs.algs4.Picture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SeamTest {
    @Test
    void verticalSeamTest() {
        Picture picture = new Picture(ClassLoader.getSystemResource("6x5.png").getFile());
        assertEquals(new int[]{3, 4, 3, 2, 2}, new SeamCarver(picture).findVerticalSeam());
    }

    @Test
    void HorizontalSeamTest() {
        Picture picture = new Picture(ClassLoader.getSystemResource("6x5.png").getFile());
        assertEquals(new int[]{2, 2, 1, 2, 1, 2}, new SeamCarver(picture).findVerticalSeam());
    }
}
