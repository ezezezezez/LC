import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// 3282. Reach End of Array With Max Score
public class P3282_2 {
    public long findMaximumScore(List<Integer> nums) {
        int n = nums.size();
        if (n == 1) return 0L;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(n - 2);
        for (int i = n - 3; i >= 0; i--) {
            int num = nums.get(i);
            while (!dq.isEmpty() && num >= nums.get(dq.peekLast())) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        int idx = n - 1;
        long ret = 0L;
        while (!dq.isEmpty()) {
            int p = dq.pollFirst();
            ret += (long) (idx - p) * nums.get(p);
            idx = p;
        }
        return ret;
    }
}
