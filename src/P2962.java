import java.util.ArrayList;
import java.util.List;

// 2962. Count Subarrays Where Max Element Appears at Least K Times
public class P2962 {
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int mx = 0;
        for (int num : nums) mx = Math.max(mx, num);
        long ret = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = mx == nums[i] ? 1 : 0;
        }
        int count = 0;
        for (int i = 0, j = 0; i < n; i++) {
            count += nums[i];
            while (count == k) {
                count -= nums[j];
                j++;
            }
            ret += j;
        }
        return ret;
    }
}
