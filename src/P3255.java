import java.util.ArrayDeque;
import java.util.Deque;

// 3255. Find the Power of K-Size Subarrays II
public class P3255 {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] ret = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(nums[0]);
        for (int i = 1; i < k; i++) {
            if (dq.peekLast() != nums[i] - 1) {
                dq.clear();
            }
            dq.offerLast(nums[i]);
        }
        ret[0] = dq.size() == k ? dq.peekLast() : -1;
        for (int i = 1; i < ret.length; i++) {
            int num = nums[i + k - 1];
            if (dq.size() == k) dq.pollFirst();
            if (!dq.isEmpty() && dq.peekLast() != num - 1) {
                dq.clear();
            }
            dq.offerLast(num);
            if (dq.size() == k) {
                ret[i] = dq.peekLast();
            } else {
                ret[i] = -1;
            }
        }
        return ret;
    }
}
