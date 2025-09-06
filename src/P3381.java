import java.util.Arrays;

// 3381. Maximum Subarray Sum With Length Divisible by K
public class P3381 {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] best = new long[k];
        long INF = (long) 9e15;
        Arrays.fill(best, INF);
        best[0] = 0;
        long sum = 0;
        long ret = (long) -9e15;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int idx = (int) Math.floorMod(i + 1, k);
            if (best[idx] != INF) {
                ret = Math.max(ret, sum - best[idx]);
            }
            best[idx] = Math.min(best[idx], sum);
        }
        return ret;
    }
}
