import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 3585. Find Weighted Median Node in Tree
public class P3585 {
    // 1e5: K=17
    int K = 17;
    int N;
    int[] dep;
    long[] distFromRoot;
    List<int[]>[] adj;
    int[][] table;
    long[][] distTable;

    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        N = edges.length + 1;
        adj = new List[N];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }
        dep = new int[N];
        distFromRoot = new long[N];
        table = new int[N][K + 1];
        distTable = new long[N][K + 1];
        for (int[] row : table) Arrays.fill(row, -1);
        for (int i = 0; i <= K; i++) {
            table[0][i] = 0;
            distTable[0][i] = 0;
        }
        dfs(0);

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < N; j++) {
                table[j][i] = table[table[j][i - 1]][i - 1];
                distTable[j][i] = distTable[j][i - 1] + distTable[table[j][i - 1]][i - 1];
            }
        }

        int m = queries.length;
        int[] ret = new int[m];
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int u = query[0], v = query[1];
            if (u == v) {
                ret[i] = u;
                continue;
            }
            int lca = lca(u, v);
            long totalDist = getDist(u, v);
            long dist1 = getDist(u, lca);
            // System.out.println(totalDist + " " + dist1 + " " + lca);
            if (2 * dist1 >= totalDist) {
                int k = K;
                long d = 0;
                // System.out.println("U: " + u);
                for (int p = k; p >= 0; p--) {
                    if (d + 2 * distTable[u][p] < totalDist) {
                        d += 2 * distTable[u][p];
                        u = table[u][p];
                    }
                }
                // System.out.println("U: " + u);
                ret[i] = table[u][0];
            } else {
                int k = K;
                long d = 0;
                // System.out.println("V1: " + v);
                for (int p = k; p >= 0; p--) {
                    // System.out.println("Vtable: " + distTable[v][p]);
                    if (d + 2 * distTable[v][p] <= totalDist) {
                        d += 2 * distTable[v][p];
                        v = table[v][p];
                    }
                }
                // System.out.println("V2: " + v);
                ret[i] = v;
            }
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
            distTable[v][0] = w;
            dfs(v);
        }
    }
}
