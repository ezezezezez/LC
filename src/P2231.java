import java.io.*;
import java.lang.*;
import java.util.*;

// 2231. Largest Number After Digit Swaps by Parity

public class P2231 {
    public int largestInteger(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        List<Integer> odds = new ArrayList<>(), evens = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d % 2 == 0) {
                evens.add(d);
            } else {
                odds.add(d);
            }
        }
        Collections.sort(evens, (a, b) -> Integer.compare(b, a));
        Collections.sort(odds, (a, b) -> Integer.compare(b, a));
        int ret = 0, idx1 = 0, idx2 = 0;
        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d % 2 == 0) {
                ret = 10 * ret + evens.get(idx1++);
            } else {
                ret = 10 * ret + odds.get(idx2++);
            }
        }
        return ret;
    }
}
