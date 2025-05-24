import java.util.*;

// 3310. Remove Methods From Project
public class P3310_2 {
    Set<Integer> vis = new HashSet<>();
    List<Integer>[] adj;
    public List<Integer> remainingMethods(int n, int k, int[][] edges) {
        adj = new List[n];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
        }
        dfs(k);

        List<Integer> ret = new ArrayList<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (vis.contains(v) && !vis.contains(u)) {
                for (int i = 0; i < n; i++) ret.add(i);
                return ret;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!vis.contains(i)) ret.add(i);
        }
        return ret;
    }

    void dfs(int u) {
        vis.add(u);
        for (int v : adj[u]) {
            if (vis.contains(v)) continue;
            dfs(v);
        }
    }
}
