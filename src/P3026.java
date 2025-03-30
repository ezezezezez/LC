import java.util.HashMap;
import java.util.Map;

// 3026. Maximum Good Subarray Sum
public class P3026 {
    long INF = (long) 1e15;
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long ret = -INF;
        Map<Integer, Long> map = new HashMap<>();
        long sum = 0;
        for (int num : nums) {
            int a = num - k, b = num + k;
            if (map.containsKey(a)) {
                ret = Math.max(ret, sum + num - map.get(a));
            }
            if (map.containsKey(b)) {
                ret = Math.max(ret, sum + num - map.get(b));
            }
            if (sum < map.getOrDefault(num, INF)) {
                map.put(num, sum);
            }
            sum += num;
        }
        return ret == -INF ? 0 : ret;
    }
}
