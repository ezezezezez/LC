import java.util.*;

// 2747. Count Zero Request Servers
public class P2747 {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length, queriesLen = queries.length;
        int[] ret = new int[queriesLen];
        int[][] queriesWithIdx = new int[queriesLen][2];
        for (int i = 0; i < queriesLen; i++) {
            queriesWithIdx[i][0] = queries[i];
            queriesWithIdx[i][1] = i;
        }
        Arrays.sort(queriesWithIdx, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        Map<Integer, Integer> map = new HashMap<>();
        Queue<int[]> queue = new ArrayDeque<>();
        int j = 0;
        for (int i = 0; i < queriesLen; i++) {
            int[] qq = queriesWithIdx[i];
            int t = qq[0], idx = qq[1];
            while (j < m && logs[j][1] <= t) {
                if (logs[j][1] >= t - x) {
                    queue.offer(logs[j]);
                    map.merge(logs[j][0], 1, Integer::sum);
                }
                j++;
            }
            // System.out.println(queue);
            // System.out.println(map);
            while (!queue.isEmpty() && queue.peek()[1] < t - x) {
                int[] log = queue.poll();
                map.merge(log[0], -1, Integer::sum);
                if (map.get(log[0]) == 0) map.remove(log[0]);
            }
            ret[idx] = n - map.size();
        }
        return ret;
    }
}
