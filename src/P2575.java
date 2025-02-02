import java.util.ArrayList;
import java.util.List;

public class P2575 {
    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] ret = new int[n];
        long r = 0;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            int t = c - '0';
            r = 10 * r + t;
            if (r % m == 0) {
                ret[i] = 1;
                r = 0;
            } else {
                ret[i] = 0;
                r = r % m;
            }
        }
        return ret;
    }
}
