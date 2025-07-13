// 3362. Zero Array Transformation III

import java.util.*;

public class P3362 {
    public int maxRemoval(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(queries, (a, b) -> Integer.compare(a[0], b[0]));
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int[] diff = new int[n];

        int j = 0, cur = 0, need = 0;
        for (int i = 0; i < n; i++) {
            while (j < m && queries[j][0] <= i) {
                tm.merge(queries[j][1], 1, Integer::sum);
                j++;
            }
            // System.out.println(tm.size());
            // if (!tm.isEmpty()) System.out.println(tm.lastEntry().getValue().size());
            // System.out.println("---");
            while (!tm.isEmpty() && cur < nums[i]) {
                Map.Entry<Integer, Integer> lastEntry = tm.lastEntry();
                int end = lastEntry.getKey();
                diff[end]--;
                int starts = lastEntry.getValue();
                cur++;
                need++;

                starts--;
                if (starts == 0) tm.remove(end);
                else tm.put(end, starts);
            }
            // System.out.println(tm.size());
            // System.out.println(cur);
            // System.out.println("------");
            if (cur < nums[i]) return -1;

            if (tm.containsKey(i)) {
                tm.remove(i);
            }
            cur += diff[i];

            // System.out.println(tm.size());
            // System.out.println(cur);
            // System.out.println("---------");
        }

        return m - need;
    }
}
