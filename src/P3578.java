import java.util.TreeMap;

// 3578. Count Partitions With Max-Min Difference at Most K
public class P3578 {
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        int ret = 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int mod = 1000000007;
        int[] prefix = new int[n + 1];
        prefix[0] = 1;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int j = 0;
        for (int i = 0; i < n; i++) {
            map.merge(nums[i], 1, Integer::sum);
            while (map.lastKey() - map.firstKey() > k) {
                map.merge(nums[j], -1, Integer::sum);
                if (map.get(nums[j]) == 0) map.remove(nums[j]);
                j++;
            }

            // System.out.println(j);
            dp[i + 1] = (prefix[i] - (j - 1 >= 0 ? prefix[j - 1] : 0) + mod) % mod;
            prefix[i + 1] = (prefix[i] + dp[i + 1]) % mod;
        }

        return dp[n];
    }
}
