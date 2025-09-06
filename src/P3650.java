// 3650. Minimum Cost Path with Edge Reversals

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class P3650 {
    List<int[]>[] adj;
    public int minCost(int n, int[][] edges) {
        int m = edges.length;
        adj = new List[n];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            adj[u].add(new int[] {v, w});
            adj[v].add(new int[] {u, 2 * w});
        }
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE / 2);
        d[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[] {0, 0});

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int node = data[0], dist = data[1];
            if (dist > d[node]) continue;
            if (node == n - 1) return dist;

            for (int[] nxt : adj[node]) {
                int v = nxt[0], w = nxt[1];
                if (dist + w < d[v]) {
                    d[v] = dist + w;
                    pq.offer(new int[] {v, d[v]});
                }
            }
        }

        return -1;
    }
}
