import java.io.*;
import java.lang.*;
import java.util.*;

// 2368. Reachable Nodes With Restrictions

public class P2368 {
    Set<Integer> set = new HashSet<>();
    Set<Integer>[] g;
    int ret;
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (int node : restricted) set.add(node);
        dfs(0, -1);
        return ret;
    }

    void dfs(int cur, int pre) {
        if (set.contains(cur)) return;
        ret++;
        for (int nxt : g[cur]) {
            if (nxt != pre) {
                dfs(nxt, cur);
            }
        }
    }
}
