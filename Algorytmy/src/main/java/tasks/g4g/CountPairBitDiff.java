package tasks.g4g;

public class CountPairBitDiff {

    public static int countBitDiff(int[] input) {
        int count = 0;
        for (int pos = 0; pos < 32; pos++) {
            //treat each 32 bit positions separately
            int oneCount = 0;
            for (int i = 0; i < input.length; i++) {
                if (input[i] % 2 != 0) {
                    oneCount++;
                }
                input[i] >>= 1; // divide by 2
            }
            count += oneCount * (input.length - oneCount);
        }
        return count;
    }

}
