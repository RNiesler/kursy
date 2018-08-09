package tasks.g4g;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

//Imagine you have a special keyboard with the following keys:
//Key 1:  Prints 'A' on screen
//Key 2: (Ctrl-A): Select screen
//Key 3: (Ctrl-C): Copy selection to buffer
//Key 4: (Ctrl-V): Print buffer on screen appending it
//                 after what has already been printed.
//
//If you can only press the keyboard for N times (with the above four
//keys), write a program to produce maximum numbers of A's. That is to
//say, the input parameter is N (No. of keys that you can press), the
//output is M (No. of As that you can produce).
public class MaxAs {

    @AllArgsConstructor
    @EqualsAndHashCode
    private static class MemoEntity {
        final int remainingKeystrokes;
        final int currentCount;
        final int currentBuffer;
    }

    public static int maxAs(int totalKeystrokes) {
        HashMap<MemoEntity, Integer> memo = new HashMap<>();
        return maxAsRecursive(totalKeystrokes, 0, 0, memo);
    }

    private static int maxAsRecursive(int totalKeystrokes, int currentAs, int copyBuffer, Map<MemoEntity, Integer> memo) {
        if (totalKeystrokes <= 0) {
            return 0;
        } else {
            MemoEntity memoEntity = new MemoEntity(totalKeystrokes, currentAs, copyBuffer);
            if (memo.containsKey(memoEntity)) {
                return memo.get(memoEntity);
            } else {
                int max = 1 + maxAsRecursive(totalKeystrokes - 1, currentAs + 1, copyBuffer, memo);
                if (copyBuffer > 0) {
                    int maxWithPaste = copyBuffer + maxAsRecursive(totalKeystrokes - 1, currentAs + copyBuffer, copyBuffer, memo);
                    if (maxWithPaste > max) {
                        max = maxWithPaste;
                    }
                }
                if (totalKeystrokes > 1) {
                    int maxWithSelectAndCopy = maxAsRecursive(totalKeystrokes - 2, currentAs, currentAs, memo);
                    if (maxWithSelectAndCopy > max) {
                        max = maxWithSelectAndCopy;
                    }
                }
                memo.put(memoEntity, max);
                return max;
            }
        }
    }

    //Below are few important points to note.
    //
    //a) For N < 7, the output is N itself.
    //
    //b) Ctrl V can be used multiple times to print current buffer (See last two examples above).
    // The idea is to compute the optimal string length for N keystrokes by using a simple insight.
    // The sequence of N keystrokes which produces an optimal string length will end with a suffix of Ctrl-A, a Ctrl-C, followed by only Ctrl-V’s . (For N > 6)
    //
    //The task is to find out the break=point after which we get the above suffix of keystrokes.
    // Definition of a breakpoint is that instance after which we need to only press Ctrl-A, Ctrl-C once and the only Ctrl-V’s afterwards to generate the optimal length.
    // If we loop from N-3 to 1 and choose each of these values for the break-point, and compute that optimal string they would produce.
    // Once the loop ends, we will have the maximum of the optimal lengths for various breakpoints, thereby giving us the optimal length for N keystrokes.
    public static int maxAsOptimal(int totalKeystrokes) {
        if (totalKeystrokes < 7) {
            return totalKeystrokes;
        } else {
            int max = 0;
            for (int i = 1; i <= totalKeystrokes - 3; i++) {
                int countOfPastes = totalKeystrokes - 2 - i;
                int val = (countOfPastes + 1) * maxAsOptimal(i);
                if (val > max) {
                    max = val;
                }
            }
            return max;
        }
    }
}
