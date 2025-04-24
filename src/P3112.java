import java.util.*;

// 3111. Minimum Rectangles to Cover Points
public class P3112 {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        long[] ret = new long[n];
        long INF = Long.MAX_VALUE / 2;
        Arrays.fill(ret, INF);
        List<Map<Integer, Long>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new HashMap<>());
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            long v = edge[2];
            g.get(a).merge(b, v, Math::min);
            g.get(b).merge(a, v, Math::min);
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[] {0, 0});
        ret[0] = 0;
        boolean[] vis = new boolean[n];
        while (!pq.isEmpty()) {
            long[] pair = pq.poll();
            int cur = (int) pair[0];
            long len = pair[1];
            if (vis[cur]) continue;
            vis[cur] = true;
            for (Map.Entry<Integer, Long> entry : g.get(cur).entrySet()) {
                int nxt = entry.getKey();
                long d = entry.getValue();
                if (ret[cur] + d < ret[nxt] && ret[cur] + d < disappear[nxt]) {
                    ret[nxt] = ret[cur] + d;
                    pq.offer(new long[] {nxt, ret[nxt]});
                }
            }
        }
        // System.out.println(Arrays.toString(ret));
        int[] realRet = new int[n];
        for (int i = 0; i < n; i++) {
            realRet[i] = ret[i] == INF ? -1 : (int) ret[i];
        }
        return realRet;
    }
}
