import java.util.*;

public class P2616 {
    public int minimizeMax(int[] nums, int p) {
        int n = nums.length;
        if (n == 1) return 0;
        Arrays.sort(nums);
        int[] diff = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = nums[i + 1] - nums[i];
        }
        int ret = -1, lo = 0, hi = Integer.MAX_VALUE;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = 0;
            for (int i = 0; i < diff.length; i++) {
                if (diff[i] <= mid) {
                    cnt++;
                    i++;
                }
            }
            if (cnt >= p) {
                ret = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ret;
    }
}
