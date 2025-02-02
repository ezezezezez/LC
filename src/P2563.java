import java.util.Arrays;

public class P2563 {
    public long countFairPairs(int[] nums, int lower, int upper) {
        int n = nums.length;
        if (n == 1) return 0;
        long ret = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int a = lower - nums[i], b = upper - nums[i];
            int lo = i + 1, hi = n - 1;
            int ta = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] >= a) {
                    ta = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1 ;
                }
            }
            lo = i + 1;
            hi = n - 1;
            int tb = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] <= b) {
                    tb = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            if (ta == -1 || tb == -1) continue;
            ret += tb - ta + 1;
        }
        return ret;
    }
}
