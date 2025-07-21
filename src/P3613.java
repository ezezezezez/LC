import java.util.*;

// 3613. Minimize Maximum Component Cost
public class P3613 {
    public int minCost(int n, int[][] edges, int k) {
        if (k == n) return 0;
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        UF uf = new UF(n);
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            uf.union(u, v);
            if (uf.setCount <= k) return edge[2];
        }

        return -1;
    }

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
}
