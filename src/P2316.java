import java.io.*;
import java.lang.*;
import java.util.*;

// 2316. Count Unreachable Pairs of Nodes in an Undirected Graph

public class P2316 {
    public long countPairs(int n, int[][] edges) {
        long ret = 1L * n * (n - 1) / 2;
        UF uf = new UF(n);
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            uf.union(a, b);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.merge(uf.find(i), 1, Integer::sum);
        }
        List<Integer> list = new ArrayList<>(map.values());
        int m = list.size();
        for (int i = 0; i < m; i++) {
            if (list.get(i) > 1) {
                int sz = list.get(i);
                ret -= 1L * sz * (sz - 1) / 2;
            }
        }
        return ret;
    }

    static class UF {
        int[] parent;
        int[] size;

        int[] rank;
        int n;
        int setCount;

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
