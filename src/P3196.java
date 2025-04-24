import java.util.Arrays;

// 3196. Maximize Total Cost of Alternating Subarrays
public class P3196 {
    public long maximumTotalCost(int[] nums) {
        int n = nums.length;
        long ret = 0;
        long[] f = new long[n + 1];
        Arrays.fill(f, Long.MIN_VALUE);
        f[0] = 0;
        f[1] = nums[0];
        for (int i = 1; i < n; i++) {
            f[i + 1] = Math.max(f[i] + nums[i], f[i - 1] + nums[i - 1] - nums[i]);
        }
        // System.out.println(Arrays.toString(f));
        return f[n];
    }
}
