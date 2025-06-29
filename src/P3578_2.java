import java.util.ArrayDeque;
import java.util.Deque;

// 3578. Count Partitions With Max-Min Difference at Most K
public class P3578_2 {
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> minQ = new ArrayDeque<>(), maxQ = new ArrayDeque<>();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int mod = 1000000007;
        int prefix = 1;
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (!minQ.isEmpty() && nums[minQ.peekLast()] >= nums[i]) {
                minQ.pollLast();
            }
            minQ.offerLast(i);
            while (!maxQ.isEmpty() && nums[maxQ.peekLast()] <= nums[i]) {
                maxQ.pollLast();
            }
            maxQ.offerLast(i);
            while (nums[maxQ.peekFirst()] - nums[minQ.peekFirst()] > k) {
                prefix = (prefix - dp[j] + mod) % mod;
                if (minQ.peekFirst() == j) minQ.pollFirst();
                if (maxQ.peekFirst() == j) maxQ.pollFirst();
                j++;
            }
            dp[i + 1] = prefix;
            prefix = (prefix + dp[i + 1]) % mod;
        }

        return dp[n];
    }
}
