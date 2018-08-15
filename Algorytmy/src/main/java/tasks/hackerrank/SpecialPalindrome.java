package tasks.hackerrank;

import java.util.Objects;

public class SpecialPalindrome {
    // Complete the substrCount function below.
    static long substrCountV1(int n, String s) {
        Objects.requireNonNull(s);
        char[] strArr = s.toCharArray();
        long count = 0;
        for(int i=0; i<strArr.length; i++) {
            int j=i;
            while(j<strArr.length && canBePalindromePrefix(strArr,i,j)) {
                if(isPalindrome(strArr, i, j)) {
                    count++;
                }
                j++;
            }
        }
        return count;
    }
    private static boolean canBePalindromePrefix(final char[] c, int first, int last) {
        char firstChar = c[first];
        int leftCount = 1;
        int rightCount = 0;
        boolean hadOther = false;
        int i = first+1;
        while(i<=last) {
            if(c[i] == firstChar) {
                if(hadOther) {
                    if(rightCount < leftCount) {
                        rightCount++;
                    } else {
                        return false;
                    }
                } else {
                    leftCount++;
                }
            } else {
                if(hadOther) {
                    return false;
                } else {
                    hadOther = true;
                }
            }
            i++;
        }
        return true;
    }

    private static boolean isPalindrome(final char[] c, int first, int last) {
        while(first < last) {
            if(c[first] != c[last]) {
                return false;
            }
            first++;
            last--;
        }
        return true;
    }
}
