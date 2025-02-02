import java.util.Arrays;

public class P2640 {
    public long[] findPrefixScore(int[] nums) {
        int n = nums.length;
        long[] ret = new long[n];
        long sum = 0;
        int mx = 0;
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            sum += nums[i] + 0L + mx;
            ret[i] = sum;
        }
        return ret;
    }
}
