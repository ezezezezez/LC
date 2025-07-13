// 3608. Minimum Time for K Connected Components

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P3608 {
    public int minTime(int n, int[][] edges, int k) {
        int lo = 0, hi = (int) 1e9 + 5, t = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            UF uf = new UF(n);
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], time = edge[2];
                if (time > mid) uf.union(u, v);
            }
            if (uf.setCount >= k) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return t;
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
