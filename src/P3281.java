import java.util.Arrays;
import java.util.PriorityQueue;

// 3281. Maximize Score of Numbers in Ranges
public class P3281 {
    public int maxPossibleScore(int[] start, int d) {
        int n = start.length;
        Arrays.sort(start);
        int lo = 0, hi = Integer.MAX_VALUE, t = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            boolean valid = true;
            int last = start[0];
            for (int i = 1; i < n; i++) {
                int nxt = last + mid;
                if (nxt > start[i] + d) {
                    valid = false;
                    break;
                }
                last = Math.max(nxt, start[i]);
            }
            if (valid) {
                t = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return t;
    }
}
