import java.util.HashMap;
import java.util.Map;

// 2799. Count Complete Subarrays in an Array
public class P2799 {
    public int countCompleteSubarrays(int[] nums) {
        int ret = 0;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int j = i; j >= 0; j--) {
                cnt.merge(nums[j], 1, Integer::sum);
                if (cnt.size() == map.size()) {
                    ret += j + 1;
                    break;
                }
            }
        }
        return ret;
    }
}
