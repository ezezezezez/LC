// 3362. Zero Array Transformation III

import java.util.Arrays;
import java.util.PriorityQueue;

public class P3362_2 {
    public int maxRemoval(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(queries, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        int[] diff = new int[n];

        int j = 0, cur = 0, need = 0;
        for (int i = 0; i < n; i++) {
            while (j < m && queries[j][0] <= i) {
                pq.offer(queries[j]);
                j++;
            }
            // System.out.println(tm.size());
            // if (!tm.isEmpty()) System.out.println(tm.lastEntry().getValue().size());
            // System.out.println("---");
            while (!pq.isEmpty() && cur < nums[i]) {
                int[] interval = pq.poll();
                int end = interval[1];
                if (end >= i) {
                    diff[end]--;
                    cur++;
                    need++;
                }
            }
            // System.out.println(tm.size());
            // System.out.println(cur);
            // System.out.println("------");
            if (cur < nums[i]) return -1;

            cur += diff[i];
        }

        return m - need;
    }
}
