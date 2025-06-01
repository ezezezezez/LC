// 3331. Find Subtree Sizes After Changes

import java.util.*;

public class P3331 {
    Map<Character, List<Integer>> map = new HashMap<>();
    List<Integer>[] adj;
    String s;
    int[] parent;
    int[] ret;
    public int[] findSubtreeSizes(int[] parent, String s) {
        this.parent = parent;
        this.s = s;
        int n = parent.length;
        ret = new int[n];
        adj = new List[n];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            int p = parent[i];
            adj[i].add(p);
            adj[p].add(i);
        }

        dfs(0, -1);

        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            int p = parent[i];
            adj[i].add(p);
            adj[p].add(i);
        }

        dfs2(0, -1);

        return ret;
    }

    void dfs2(int u, int pre) {
        ret[u] = 1;
        for (int v : adj[u]) {
            if (v == pre) continue;
            dfs2(v, u);
            ret[u] += ret[v];
        }
    }

    void dfs(int u, int pre) {
        map.computeIfAbsent(s.charAt(u), kk -> new ArrayList<>()).add(u);
        for (int v : adj[u]) {
            if (v == pre) continue;
            char t = s.charAt(v);
            if (map.containsKey(t)) {
                int p = map.get(t).get(map.get(t).size() - 1);
                parent[v] = p;
            }
            dfs(v, u);
        }
        List<Integer> list = map.get(s.charAt(u));
        list.remove(list.size() - 1);
        if (list.isEmpty()) map.remove(s.charAt(u));
    }
}
