import java.util.HashMap;
import java.util.Map;

// 2910. Minimum Number of Groups to Create a Valid Assignment
public class P2910 {
    public int minGroupsForValidAssignment(int[] balls) {
        int n = balls.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : balls) map.merge(num, 1, Integer::sum);
        int k = Integer.MAX_VALUE;
        for (int cnt : map.values()) k = Math.min(k, cnt);
        for (int i = k; i >= 1; i--) {
            int t = i + 1;
            boolean valid = true;
            for (int cnt : map.values()) {
                if (cnt % t > 0 && cnt % t + cnt / t < i) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                int ret = 0;
                for (int cnt : map.values()) {
                    ret += (cnt + i) / (i + 1);
                }
                return ret;
            }
        }
        return -1;
    }
}
