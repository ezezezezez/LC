import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P2588 {
    public long beautifulSubarrays(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int cur = 0;
        long ret = 0;
        for (int num : nums) {
            int t = cur ^ num;
            ret += map.getOrDefault(t, 0);
            cur ^= num;
            map.merge(cur, 1, Integer::sum);
        }
        return ret;
    }
}
