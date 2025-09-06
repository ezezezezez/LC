// 3620. Network Recovery Pathways

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P3620_2 {
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
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], cost = edge[2];
            adj[u].add(new int[]{v, cost});
        }

        int lo = 0, hi = (int) 1e9 + 5, t = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long[] memo = new long[n];
            Arrays.fill(memo, -1);
            if (dfs(0, mid, memo) <= k) {
                t = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return t;
    }

    long dfs(int u, int limit, long[] memo) {
        if (u == n - 1) return 0;
        if (memo[u] != -1) return memo[u];

        long res = Long.MAX_VALUE / 2;
        for (int[] nxt : adj[u]) {
            int v = nxt[0], cost = nxt[1];
            if (cost < limit) continue;
            if (!online[v]) continue;
            res = Math.min(res, dfs(v, limit, memo) + cost);
        }

        return memo[u] = res;
    }
}
