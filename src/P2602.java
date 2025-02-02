import java.util.*;

public class P2602 {
    public List<Long> minOperations(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(nums);
        List<Long> ret = new ArrayList<>();
        long[] prefix = new long[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) prefix[i] = prefix[i - 1] + nums[i];
        for (int i = 0; i < m; i++) {
            int v = queries[i];
            int lo = 0, hi = n - 1, t = -1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] <= v) {
                    t = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            if (t == -1) {
                ret.add(prefix[n - 1] - 1L * v * n);
            } else {
                long add = 1L * v * (t + 1) - prefix[t];
                if (t < n - 1) {
                    add += prefix[n - 1] - prefix[t] - 1L * (n - t - 1) * v;
                }
                ret.add(add);
            }
        }
        return ret;
    }
}
