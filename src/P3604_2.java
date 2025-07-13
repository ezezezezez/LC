// 3604. Minimum Time to Reach Destination in Directed Graph

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class P3604_2 {
    List<int[]>[] adj;

    public int minTime(int n, int[][] edges) {
        if (n == 1) return 0;
        adj = new List[n];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], start = edge[2], end = edge[3];
            adj[u].add(new int[] {v, start, end});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[] {0, 0});
        int[] vis = new int[n];
        Arrays.fill(vis, Integer.MAX_VALUE);

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int time = data[0], node = data[1];
            if (node == n - 1) return time;
            if (time > vis[node]) continue;
            vis[node] = time;

            for (int[] nxt : adj[node]) {
                int nxtNode = nxt[0], nxtStart = nxt[1], nxtEnd = nxt[2];
                if (time > nxtEnd) continue;
                int nxtTime = Math.max(time, nxtStart) + 1;
                if (nxtTime < vis[nxtNode]) {
                    vis[nxtNode] = nxtTime;
                    pq.offer(new int[] {nxtTime, nxtNode});
                }
            }
        }

        return -1;
    }
}
