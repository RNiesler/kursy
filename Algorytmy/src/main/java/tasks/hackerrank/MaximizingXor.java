package tasks.hackerrank;

public class MaximizingXor {
    static int maximizingXor(int l, int r) {
        int xor = l ^ r;
        //first differing position
        int firstDiffering = Integer.highestOneBit(xor);
        firstDiffering >>= 1;

        // set every other to one
        while (firstDiffering > 0) {
            xor |= firstDiffering;
            firstDiffering >>= 1;
        }
        return xor;
    }
}
