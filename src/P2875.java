import java.util.HashMap;
import java.util.Map;

// 2875. Minimum Size Subarray in Infinite Array
public class P2875 {
    public int minSizeSubarray(int[] nums, int target) {
        int n = nums.length;
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 0);
        long total = 0;
        for (int num : nums) total += num;
        // System.out.println(total);
        long sum = 0;
        int retPre = (int) (target / total * n);
        target %= total;
        if (target == 0) return retPre;
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (map.containsKey(sum - target)) {
                ret = Math.min(ret, i + 1 - map.get(sum - target));
            }
            map.put(sum, i + 1);
        }
        // System.out.println(ret);
        if (n <= 2) return ret == Integer.MAX_VALUE ? -1 : retPre + ret;
        map.clear();
        sum = 0;
        for (int i = n - 1; i >= 2; i--) {
            sum += nums[i];
            map.putIfAbsent(sum, n - i);
        }
        // System.out.println(map);
        sum = 0;
        for (int i = 0; i < n - 2; i++) {
            sum += nums[i];
            if (map.containsKey(target - sum) && n - map.get(target - sum) > i + 1) {
                ret = Math.min(ret, i + 1 + map.get(target - sum));
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : retPre + ret;
    }
}
