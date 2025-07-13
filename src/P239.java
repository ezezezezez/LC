import java.util.ArrayDeque;
import java.util.Deque;

// 239. Sliding Window Maximum

public class P239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ret = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) dq.pollFirst();
            if (i >= k - 1) ret[i - (k - 1)] = nums[dq.peekFirst()];
        }

        return ret;
    }
}
