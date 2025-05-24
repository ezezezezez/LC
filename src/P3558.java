import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 3558. Number of Ways to Assign Edge Weights I
public class P3558 {
    List<Integer>[] adj;
    int dep;
    int mod = 1000000007;
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        adj = new List[n + 1];
        Arrays.setAll(adj, kk -> new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        dfs(1, -1, 0);

        int[][] dp = new int[dep + 1][2];
        dp[0][0] = 1;

        for (int i = 0; i < dep; i++) {
            dp[i + 1][0] = (dp[i][0] + dp[i][1]) % mod;
            dp[i + 1][1] = (dp[i][0] + dp[i][1]) % mod;
        }

        return dp[dep][1];
    }

    void dfs(int u, int pre, int d) {
        dep = Math.max(dep, d);
        for (int v : adj[u]) {
            if (v == pre) continue;
            dfs(v, u, d + 1);
        }
    }
}
