import java.util.*;

public class P2615 {
    public long[] distance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            map.computeIfAbsent(num, k -> new ArrayList<>()).add(i);
        }
        long[] ret = new long[n];
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            long dist1 = 0, dist2 = 0;
            for (int j = 1; j < list.size(); j++) dist1 += list.get(j) - list.get(0);
            for (int j = 0; j < list.size(); j++) {
                int idx = list.get(j);
                ret[idx] = dist1;
                if (j > 0) {
                    dist2 += j * (list.get(j) - list.get(j - 1));
                    ret[idx] += dist2;
                }
                if (j + 1 < list.size()) dist1 -= (list.size() - 1 - j) * (list.get(j + 1) - list.get(j));
            }
        }
        return ret;
    }
}
