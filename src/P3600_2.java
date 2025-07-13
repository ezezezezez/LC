import java.util.*;

// 3600. Maximize Spanning Tree Stability with Upgrades

public class P3600_2 {
    public int maxStability(int n, int[][] edges, int k) {
        UF uf = new UF(n);
        int minS = Integer.MAX_VALUE;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], s = edge[2], must = edge[3];
            if (must == 1) {
                if (!uf.union(u, v)) return -1;
                minS = Math.min(minS, s);
            }
        }
        if (uf.setCount == 1) return minS;

        Arrays.sort(edges, (a, b) -> Integer.compare(b[2], a[2]));
        List<Integer> list = new ArrayList<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], s = edge[2], must = edge[3];
            if (must == 1) continue;
            if (uf.union(u, v)) {
                list.add(s);
            }
        }
        if (uf.setCount != 1) return -1;
        if (k > 0) {
            minS = Math.min(minS, 2 * list.get(list.size() - 1));
        }
        if (k < list.size()) {
            minS = Math.min(minS, list.get(list.size() - 1 - k));
        }

        return minS;
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
