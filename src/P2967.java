import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 2967. Minimum Cost to Make Array Equalindromic
public class P2967 {
    private static final int[] pal = new int[109999];

    static {
        int palIdx = 0;
        for (int base = 1; base <= 10000; base *= 10) {
            for (int i = base; i < base * 10; i++) {
                int x = i;
                for (int t = i / 10; t > 0; t /= 10) {
                    x = x * 10 + t % 10;
                }
                pal[palIdx++] = x;
            }
            if (base <= 1000) {
                for (int i = base; i < base * 10; i++) {
                    int x = i;
                    for (int t = i; t > 0; t /= 10) {
                        x = x * 10 + t % 10;
                    }
                    pal[palIdx++] = x;
                }
            }
        }
        pal[palIdx++] = 1_000_000_001;
    }

    public long minimumCost(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int idx = lowerBound(pal, 0, pal.length, nums[(n - 1) / 2]);
        if (nums[n / 2] >= pal[idx] || idx == 0) {
            return cost(nums, pal[idx]);
        }
        return Math.min(cost(nums, pal[idx]), cost(nums, pal[idx - 1]));
    }

    long cost(int[] nums, int x) {
        long ret = 0;
        for (int num : nums) {
            ret += Math.abs(num - x);
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
