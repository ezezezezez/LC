import java.util.*;

// 3559. Number of Ways to Assign Edge Weights II
public class P3559 {
    int K = 17;
    int N;
    int[] dep;
    List<Integer>[] adj;
    int[][] table;

    // List<Integer>[] adj;
    // int[] dep;
    int mxDep;
    int mod = 1000000007;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        N = edges.length + 1;
        adj = new List[N];
        Arrays.setAll(adj, kk -> new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0] - 1, v = edge[1] - 1;
            adj[u].add(v);
            adj[v].add(u);
        }

        dep = new int[N];
        table = new int[N][K + 1];
        for (int[] row : table) Arrays.fill(row, -1);
        for (int i = 0; i <= K; i++) table[0][i] = 0;
        dfs(0);

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < N; j++) {
                table[j][i] = table[table[j][i - 1]][i - 1];
            }
        }

        for (int i = 0; i < N; i++) mxDep = Math.max(mxDep, dep[i]);
        int[][] dp = new int[mxDep + 1][2];
        dp[0][0] = 1;

        for (int i = 0; i < mxDep; i++) {
            dp[i + 1][0] = (dp[i][0] + dp[i][1]) % mod;
            dp[i + 1][1] = (dp[i][0] + dp[i][1]) % mod;
        }

        int m = queries.length;
        int[] ret = new int[m];
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int u = query[0] - 1, v = query[1] - 1;
            int lca = lca(u, v);
            int du = dep[u] - dep[lca], dv = dep[v] - dep[lca];
            long t1 = 1L * dp[du][0] * dp[dv][1] % mod;
            long t2 = 1L * dp[du][1] * dp[dv][0] % mod;
            ret[i] = (int) ((t1 + t2) % mod);
        }

        return ret;
    }

    int lca(int u, int v) {
        if (dep[u] < dep[v]) {
            int t = u;
            u = v;
            v = t;
        }

        for (int i = K; i >= 0; i--) {
            if (dep[u] - (1 << i) >= dep[v]) {
                u = table[u][i];
            }
        }

        if (u == v) return u;

        for (int i = K; i >= 0; i--) {
            if (table[u][i] != table[v][i]) {
                u = table[u][i];
                v = table[v][i];
            }
        }

        return table[u][0];
    }

    void dfs(int u) {
        for (int v : adj[u]) {
            if (v == table[u][0]) continue;
            table[v][0] = u;
            dep[v] = dep[u] + 1;
            dfs(v);
        }
    }
}
