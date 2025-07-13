import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// 3347. Maximum Frequency of an Element After Performing Operations II

public class P3347_2 {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Map<Integer, Integer> cnt = new HashMap<>();
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int num : nums) {
            cnt.merge(num, 1, Integer::sum);
            tm.merge(num, 0, Integer::sum);
            tm.merge(num - k, 1, Integer::sum);
            tm.merge(num + k + 1, -1, Integer::sum);
        }
        int ret = 0;
        int prefix = 0;
        for (int key : tm.keySet()) {
            prefix += tm.get(key);
            ret = Math.max(ret, Math.min(prefix, cnt.getOrDefault(key, 0) + numOperations));
        }

        return ret;
    }
}
