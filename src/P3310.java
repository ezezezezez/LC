import java.util.*;

// 3310. Remove Methods From Project
public class P3310 {
    static class UF {
        int[] parent;
        int[] size;

        int[] rank;
        int n;
        int setCount;

        Map<Integer, Set<Integer>> getSets() {
            Map<Integer, Set<Integer>> ret = new HashMap<>();
            for (int i = 0; i < n; i++) {
                ret.computeIfAbsent(find(i), kk -> new HashSet<>()).add(i);
            }
            return ret;
        }

        public UF(int _n) {
            n = _n;
            setCount = _n;
            parent = new int[_n];
            size = new int[_n];
            rank = new int[_n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            }
            //        if (rank[x] < rank[y])
            if (size[x] < size[y]) {
                int t = x;
                x = y;
                y = t;
            }
            parent[y] = x;
            //        if (rank[x] == rank[y])
            //            rank[x]++;
            size[x] += size[y];
            --setCount;
            return true;
        }

        boolean connected(int x, int y) {
            x = find(x);
            y = find(y);
            return x == y;
        }
    }

    Set<Integer> vis = new HashSet<>();
    List<Integer>[] adj;
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        UF uf = new UF(n);
        adj = new List[n];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int[] edge : invocations) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            uf.union(u, v);
        }
        dfs(k);
        Map<Integer, Set<Integer>> sets = uf.getSets();
        Set<Integer> sus = sets.get(uf.find(k));
        List<Integer> ret = new ArrayList<>();
        if (sus.size() != vis.size()) {
            for (int i = 0; i < n; i++) ret.add(i);
        } else {
            for (int i = 0; i < n; i++) {
                if (!sus.contains(i)) ret.add(i);
            }
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
