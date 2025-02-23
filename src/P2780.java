import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2780. Minimum Index of a Valid Split
public class P2780 {
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.merge(num, 1, Integer::sum);
        int v = 0, cnt = 0;
        for (int key : map.keySet()) {
            int value = map.get(key);
            if (value > cnt) {
                cnt = value;
                v = key;
            }
        }
        int[] prefix = new int[n];
        prefix[0] = nums.get(0) == v ? 1 : 0;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1];
            if (nums.get(i) == v) prefix[i]++;
        }
        for (int i = 0; i < n - 1; i++) {
            if (2 * prefix[i] > i + 1 && 2 * (prefix[n - 1] - prefix[i]) > n - i - 1) return i;
        }
        return -1;
    }
}
