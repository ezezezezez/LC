import java.util.*;

// 3243. Shortest Distance After Road Addition Queries I
public class P3243 {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ret = new int[m];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            map.computeIfAbsent(i, k -> new HashSet<>()).add(i + 1);
        }
        int INF = Integer.MAX_VALUE / 2;
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int a = query[0], b = query[1];
            map.get(a).add(b);
            // System.out.println(map);
            PriorityQueue<int[]> pq = new PriorityQueue<>((p, q) -> Integer.compare(p[1], q[1]));
            pq.offer(new int[] {0, 0});
            int[] d = new int[n];
            Arrays.fill(d, INF);
            d[0] = 0;
            boolean[] vis = new boolean[n];
            while (true) {
                int[] pair = pq.poll();
                int x = pair[0], dist = pair[1];
                if (x == n - 1) {
                    ret[i] = dist;
                    break;
                }
                if (vis[x]) continue;
                vis[x] = true;
                for (int y : map.get(x)) {
                    if (d[x] + 1 < d[y]) {
                        d[y] = d[x] + 1;
                        pq.offer(new int[] {y, d[y]});
                    }
                }
            }
        }
        return ret;
    }
}
