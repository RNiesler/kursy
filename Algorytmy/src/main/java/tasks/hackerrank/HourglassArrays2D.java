package tasks.hackerrank;

public class HourglassArrays2D {
    static int hourglassSum(int[][] arr) {
        int maxHourglass = Integer.MIN_VALUE;
        for(int row = 0; row < arr.length-2; row++) {
            for(int col=0; col < arr[row].length-2; col++) {
                int sum = arr[row][col] + arr[row][col+1] + arr[row][col+2]
                        + arr[row+1][col+1]
                        + arr[row+2][col] + arr[row+2][col+1] + arr[row+2][col+2];
                if(sum > maxHourglass) {
                    maxHourglass = sum;
                }
            }
        }
        return maxHourglass;
    }
}
