import java.util.HashMap;
import java.util.Map;

// 2871. Split Array Into Maximum Number of Subarrays
public class P2871 {
    public int maxSubarrays(int[] nums) {
        int n = nums.length;
        int min = nums[0];
        for (int i = 1; i < n; i++) min &= nums[i];
        if (min > 0) return 1;
        int ret = 0, pre = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            pre &= nums[i];
            if (pre == 0) {
                ret++;
                pre = Integer.MAX_VALUE;
            }
        }
        return ret;
    }
}
