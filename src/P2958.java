import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 2958. Length of Longest Subarray With at Most K Frequency
public class P2958 {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int ret = 0;
        for (int i = 0, j = 0; i < n; i++) {
            map.merge(nums[i], 1, Integer::sum);
            if (map.get(nums[i]) > k) {
                while (map.get(nums[i]) > k) {
                    map.merge(nums[j], -1, Integer::sum);
                    j++;
                }
            }
            ret = Math.max(ret, i - j + 1);
        }
        return ret;
    }
}
