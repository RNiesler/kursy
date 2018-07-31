package tasks.hackerrank;

import java.util.List;
import java.util.Objects;

public class LonelyInteger {
    static int findLonely(List<Integer> arr) {
        Objects.requireNonNull(arr);
        int product = 0;
        for (Integer element : arr) {
            product = product ^ element;
        }

        return product;
    }
}
