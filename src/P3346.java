import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 3346. Maximum Frequency of an Element After Performing Operations I

public class P3346 {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        Arrays.sort(nums);
        int mx = 0;
        for (int num : nums) mx = Math.max(mx, num);
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) cnt.merge(num, 1, Integer::sum);

        int ret = 0;
        for (int i = 1; i <= mx; i++) {
            int i1 = lowerBound(nums, 0, n, i - k);
            int i2 = lowerBound(nums, 0, n, i + k + 1);
            int c = cnt.getOrDefault(i, 0);
            ret = Math.max(ret, Math.min(numOperations, i2 - i1 - c) + c);
        }

        return ret;
    }

    int lowerBound(int[] nums, int lo, int hi, int val) {
        lo--;
        while (hi - lo > 1) {
            int mid = (lo + hi) >> 1;
            if (nums[mid] >= val) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }
}
