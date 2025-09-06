// 3620. Network Recovery Pathways

import java.util.*;

public class P3620_3 {
    int n;
    long k;
    List<int[]>[] adj;
    boolean[] online;

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        n = online.length;
        this.online = online;
        this.k = k;
        adj = new List[n];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        int[] deg = new int[n];
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], cost = edge[2];
            if (online[u] && online[v]) {
                adj[u].add(new int[]{v, cost});
                deg[v]++;
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < n; i++) {
            if (deg[i] == 0) q.offer(i);
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int[] nxt : adj[x]) {
                int y = nxt[0];
                deg[y]--;
                if (deg[y] == 0 && y != 0) {
                    q.offer(y);
                }
            }
        }

        int lo = 0, hi = (int) 1e9 + 5, t = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (check(deg.clone(), mid)) {
                t = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return t;
    }

    boolean check(int[] deg, int limit) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        long[] d = new long[n];
        Arrays.fill(d, Long.MAX_VALUE / 2);
        d[0] = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            if (x == n - 1) return d[x] <= k;

            for (int[] nxt : adj[x]) {
                int y = nxt[0], cost = nxt[1];
                deg[y]--;
                if (cost >= limit) d[y] = Math.min(d[y], d[x] + cost);
                if (deg[y] == 0) {
                    q.offer(y);
                }
            }
        }

        return false;
    }
}
