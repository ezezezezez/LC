import java.util.*;

// 3066. Minimum Operations to Exceed Threshold Value II
public class P3066 {
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int num : nums) pq.offer((long) num);
        int ret = 0;
        while (pq.size() >= 2 && pq.peek() < k) {
            long a = pq.poll(), b = pq.poll();
            pq.offer(Math.min(a, b) * 2 + Math.max(a, b));
            ret++;
        }
        return ret;
    }
}
