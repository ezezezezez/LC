import java.util.*;

// 3048. Earliest Second to Mark Indices I
public class P3048 {
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = nums.length, m = changeIndices.length;
        int ret = Integer.MAX_VALUE;
        Map<Integer, Deque<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int idx = changeIndices[i];
            map.computeIfAbsent(idx, k -> new ArrayDeque<>()).offerLast(i + 1);
        }
        // System.out.println(map);
        int[] f = new int[m + 1];
        while (true) {
            for (int key : map.keySet()) {
                f[map.get(key).peekLast()] = key;
            }
            // System.out.println(Arrays.toString(f));
            int preCost = 0;
            boolean valid = true;
            int visited = 0;
            int lastVisited = -1, lastVisitedTime = 0;
            for (int i = 1; i <= m; i++) {
                int idx = f[i];
                if (idx == 0) continue;
                lastVisited = idx;
                lastVisitedTime = i;
                if (i - preCost - nums[idx - 1] - 1 >= 0) {
                    preCost += nums[idx - 1] + 1;
                    visited++;
                } else {
                    valid = false;
                    break;
                }
            }
            // System.out.println(lastVisited + " " + lastVisitedTime + " " + valid + " " + visited);
            if (visited < n) valid = false;
            if (!valid) break;
            ret = Math.min(ret, lastVisitedTime);
            map.get(lastVisited).pollLast();
            if (map.get(lastVisited).isEmpty()) break;
            Arrays.fill(f, 0);
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
