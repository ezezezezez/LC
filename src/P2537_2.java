import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P2537_2 {
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        long ret = 0, pairs = 0;
        int l = 0;
        for (int x : nums) {
            pairs += map.getOrDefault(x, 0);
            map.merge(x, 1, Integer::sum);
            while (pairs >= k) {
                int pre = nums[l];
                map.merge(pre, -1, Integer::sum);
                l++;
                pairs -= map.get(pre);
            }
            ret += l;
        }
        return ret;
    }
}
