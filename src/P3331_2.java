// 3331. Find Subtree Sizes After Changes

import java.util.*;

public class P3331_2 {
    Map<Character, Integer> map = new HashMap<>();
    List<Integer>[] adj;
    String s;
    int[] parent;
    int[] size;

    int[] top = new int[26];
    public int[] findSubtreeSizes(int[] parent, String s) {
        this.parent = parent;
        this.s = s;
        int n = parent.length;
        size = new int[n];
        adj = new List[n];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            int p = parent[i];
            adj[i].add(p);
            adj[p].add(i);
        }

        Arrays.fill(top, -1);
        dfs(0, -1);

        return size;
    }

    void dfs(int u, int pre) {
        size[u] = 1;

        int ou = top[s.charAt(u) - 'a'];
        top[s.charAt(u) - 'a'] = u;
        for (int v : adj[u]) {
            if (v == pre) continue;
            char t = s.charAt(v);
            if (top[t - 'a'] != -1) {
                dfs(v, u);
                int p = top[t - 'a'];
                size[p] += size[v];
            } else {
                dfs(v, u);
                size[u] += size[v];
            }
        }
        top[s.charAt(u) - 'a'] = ou;
    }
}
