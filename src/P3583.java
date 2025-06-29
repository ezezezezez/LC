import java.util.HashMap;
import java.util.Map;

// 3583. Count Special Triplets
public class P3583 {
    public int specialTriplets(int[] nums) {
        int n = nums.length;
        long ret = 0;
        long mod = 1000000007;
        Map<Integer, Integer> left = new HashMap<>(), right = new HashMap<>();
        left.put(nums[0], 1);
        for (int i = 2; i < n; i++) right.merge(nums[i], 1, Integer::sum);
        for (int i = 1; i < n - 1; i++) {
            int t = nums[i] * 2;
            long nxt = 1L * left.getOrDefault(t, 0) * right.getOrDefault(t, 0) % mod;
            ret = (ret + nxt) % mod;
            left.merge(nums[i], 1, Integer::sum);
            right.merge(nums[i + 1], -1, Integer::sum);
        }

        return (int) ret;
    }
}
