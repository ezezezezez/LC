// 3607. Power Grid Maintenance

import java.util.*;

public class P3607 {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        int n = connections.length, m = queries.length;
        List<Integer> ret = new ArrayList<>();
        UF uf = new UF(c);
        for (int[] conn : connections) {
            int u = conn[0] - 1, v = conn[1] - 1;
            uf.union(u, v);
        }
        Map<Integer, TreeSet<Integer>> sets = uf.getSets();
        for (int[] query : queries) {
            int type = query[0], x = query[1] - 1;
            if (type == 1) {
                TreeSet<Integer> set = sets.get(uf.find(x));
                if (set.contains(x)) ret.add(x + 1);
                else if (set.isEmpty()) ret.add(-1);
                else ret.add(set.first() + 1);
            } else {
                sets.get(uf.find(x)).remove(x);
            }
        }

        int[] res = new int[ret.size()];
        for (int i = 0; i < ret.size(); i++) res[i] = ret.get(i);

        return res;
    }

    static class UF {
        int[] parent;
        int[] size;

        int[] rank;
        int n;
        int setCount;

        Map<Integer, TreeSet<Integer>> getSets() {
            Map<Integer, TreeSet<Integer>> ret = new HashMap<>();
            for (int i = 0; i < n; i++) {
                ret.computeIfAbsent(find(i), kk -> new TreeSet<>()).add(i);
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
