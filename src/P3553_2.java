import java.util.*;

// 3553. Minimum Weighted Subgraph With the Required Paths II
public class P3553_2 {
    int K = 17;
    int N;
    int[] dep, distFromRoot;
    List<int[]>[] adj;
    int[][] table;
    public int[] minimumWeight(int[][] edges, int[][] queries) {
        N = edges.length + 1;
        adj = new List[N];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            adj[u].add(new int[] {v, w});
            adj[v].add(new int[] {u, w});
        }
        dep = new int[N];
        distFromRoot = new int[N];
        table = new int[N][K + 1];
        for (int[] row : table) Arrays.fill(row, -1);
        for (int i = 0; i <= K; i++) table[0][i] = 0;
        dfs(0);

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < N; j++) {
                table[j][i] = table[table[j][i - 1]][i - 1];
            }
        }

        int m = queries.length;
        int[] ret = new int[m];
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int x = query[0], y = query[1], z = query[2];
            long res = (getDist(x, y) + getDist(x, z) + getDist(y, z)) / 2;
            ret[i] = (int) res;
        }

        return ret;
    }

    long getDist(int u, int v) {
        int lca = lca(u, v);

        return distFromRoot[u] + distFromRoot[v] - 2 * distFromRoot[lca];
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
        for (int[] pair : adj[u]) {
            int v = pair[0], w = pair[1];
            if (v == table[u][0]) continue;
            table[v][0] = u;
            dep[v] = dep[u] + 1;
            distFromRoot[v] = distFromRoot[u] + w;
            dfs(v);
        }
    }
}
