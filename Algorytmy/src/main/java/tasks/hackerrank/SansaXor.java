package tasks.hackerrank;

public class SansaXor {
    static int sansaXor(int[] arr) {
        int n = arr.length;
        int xorSum = 0;
        for (int i = 0; i < arr.length; i++) {
            //long x = (i+1) * (n - i);
            //if(x % 2 == 1) {
            if ((i + 1) % 2 == 1 && (n - i) % 2 == 1) {
                xorSum ^= arr[i];
            }
        }
        return xorSum;
    }
}
