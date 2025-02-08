import java.util.Arrays;

// 2735. Collecting Chocolates
public class P2735 {
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        long ret = Long.MAX_VALUE;
        long[] mn = new long[n];
        Arrays.fill(mn, Long.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int v = nums[(j - i + n) % n];
                if (v < mn[j]) mn[j] = v;
            }
            long sum = 0;
            for (long v : mn) sum += v;
            ret = Math.min(ret, sum + (long) i * x);
        }
        return ret;
    }
}
