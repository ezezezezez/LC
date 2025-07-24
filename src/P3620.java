// 3620. Network Recovery Pathways

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class P3620 {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] adj = new List[n];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], cost = edge[2];
            adj[u].add(new int[] {v, cost});
        }

        int lo = 0, hi = (int) 1e9 + 5, t = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            boolean valid = false;
            PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
            pq.offer(new long[] {0, 0});
            long[] dist = new long[n];
            Arrays.fill(dist, Long.MAX_VALUE / 2);
            dist[0] = 0;
            boolean[] vis = new boolean[n];

            outer:
            while (!pq.isEmpty()) {
                long[] data = pq.poll();
                int u = (int) data[0];
                if (vis[u]) continue;
                vis[u] = true;

                for (int[] nxt : adj[u]) {
                    int v = nxt[0], cost = nxt[1];
                    if (!online[v]) continue;
                    if (cost < mid) continue;
                    if (dist[u] + cost > k) continue;
                    if (dist[u] + cost < dist[v]) {
                        dist[v] = dist[u] + cost;
                        if (v == n - 1) {
                            valid = true;
                            break outer;
                        }
                        pq.offer(new long[] {v, dist[v]});
                    }
                }
            }

            if (valid) {
                t = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return t;
    }
}
