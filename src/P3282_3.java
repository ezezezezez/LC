import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// 3282. Reach End of Array With Max Score
public class P3282_3 {
    public long findMaximumScore(List<Integer> nums) {
        int n = nums.size();
        if (n == 1) return 0L;
        long ret = 0L;
        int mx = 0;
        for (int i = 1; i < n - 1; i++) {
            if (nums.get(i) > nums.get(mx)) {
                ret += (long) (i - mx) * nums.get(mx);
                mx = i;
            }
        }
        ret += (long) (n - 1 - mx) * nums.get(mx);
        return ret;
    }
}
