import java.util.HashMap;
import java.util.Map;

// 3371. Identify the Largest Outlier in an Array

public class P3371 {
    public int getLargestOutlier(int[] nums) {
        int n = nums.length;
        int ret = Integer.MIN_VALUE;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            sum += num;
            map.merge(num, 1, Integer::sum);
        }
        for (int i = 0; i < n; i++) {
            sum -= nums[i];
            if (sum % 2 == 0) {
                map.merge(nums[i], -1, Integer::sum);
                if (map.getOrDefault(sum / 2, 0) > 0) {
                    ret = Math.max(ret, nums[i]);
                }
                map.merge(nums[i], 1, Integer::sum);
            }
            sum += nums[i];
        }
        return ret;
    }
}
