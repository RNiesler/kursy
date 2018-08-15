package tasks.hackerrank;

import java.util.Objects;

public class MinimumSwaps2 {
    static int minimumSwapsSelection(int[] arr) {
        int swaps = 0;
        for(int i=0; i<arr.length-1; i++) {
            int min = i;
            for(int j=i+1; j<arr.length; j++) {
                if(arr[j] < arr[min]) {
                    min = j;
                }
            }
            if(min != i) {
                swaps++;
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        return swaps;
    }

    static int minimumSwapsLinear(int[] arr) {
        Objects.requireNonNull(arr);
        int swaps=0;
        int i=0;
        while(i < arr.length) {
            if(arr[i] != i+1) {
                int temp=arr[arr[i]-1];
                arr[arr[i]-1] = arr[i];
                arr[i]= temp;
                swaps++;
            } else {
                i++;
            }
        }
        return swaps;
    }
}
