import java.util.*;

// 2870. Minimum Number of Operations to Make Array Empty
public class P2870 {
    public int minOperations(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.merge(num, 1, Integer::sum);
        int ret = 0;
        for (int cnt : map.values()) {
            if (cnt == 1) return -1;
            if (cnt % 3 == 0) {
                ret += cnt / 3;
            } else if (cnt % 3 == 2) {
                ret += cnt / 3 + 1;
            } else {
                ret += 2 + (cnt - 2) / 3;
            }
        }
        return ret;
    }
}
