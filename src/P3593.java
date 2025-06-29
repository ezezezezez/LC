import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 3593. Minimum Increments to Equalize Leaf Paths

public class P3593 {
    int n;
    int[] cost;
    List<Integer>[] adj;
    long[] maxCost;
    int ret = 0;

    public int minIncrease(int n, int[][] edges, int[] cost) {
        this.n = n;
        this.cost = cost;
        adj = new List[n];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        maxCost = new long[n];
        dfs(0, -1);
        // System.out.println(Arrays.toString(maxCost));

        dfs2(0, -1, maxCost[0]);

        return ret;
    }

    void dfs2(int u, int pre, long x) {
        long y = x - maxCost[u];
        if (y > 0) {
            ret++;
            x -= y;
        }
        for (int v : adj[u]) {
            if (v == pre) continue;
            dfs2(v, u, x - cost[u]);
        }
    }

    void dfs(int u, int pre) {
        maxCost[u] = cost[u];
        long maxChild = 0;
        for (int v : adj[u]) {
            if (v == pre) continue;
            dfs(v, u);
            maxChild = Math.max(maxChild, maxCost[v]);
        }
        maxCost[u] += maxChild;
    }
}
