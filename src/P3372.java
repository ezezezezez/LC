// 3372. Maximize the Number of Target Nodes After Connecting Trees I

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P3372 {
    List<Integer>[] adj, adj2;

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1, m = edges2.length + 1;
        int[] ret = new int[n];
        if (k == 0) {
            Arrays.fill(ret, 1);
            return ret;
        }
        adj = new List[n];
        adj2 = new List[m];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        Arrays.setAll(adj2, kk -> new ArrayList<>());
        for (int[] edge : edges1) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        for (int[] edge : edges2) {
            int u = edge[0], v = edge[1];
            adj2[u].add(v);
            adj2[v].add(u);
        }
        int mx = 0;
        for (int i = 0; i < m; i++) {
            cnt = 0;
            dfs2(i, -1, k - 1);
            mx = Math.max(mx, cnt);
        }
        for (int i = 0; i < n; i++) {
            cnt = 0;
            dfs(i, -1, k);
            ret[i] = cnt + mx;
        }
        return ret;
    }

    int cnt = 0;

    void dfs(int u, int pre, int k) {
        cnt++;
        if (k == 0) return;
        for (int v : adj[u]) {
            if (v == pre) continue;
            dfs(v, u, k - 1);
        }
    }

    void dfs2(int u, int pre, int k) {
        cnt++;
        if (k == 0) return;
        for (int v : adj2[u]) {
            if (v == pre) continue;
            dfs2(v, u, k - 1);
        }
    }
}
