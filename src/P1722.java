import java.io.*;
import java.lang.*;
import java.util.*;

// 1722. Minimize Hamming Distance After Swap Operations

public class P1722 {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UF uf = new UF(n);
        for (int[] swap : allowedSwaps) {
            int a = swap[0], b = swap[1];
            uf.union(a, b);
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(uf.find(i), k -> new HashSet<>()).add(i);
        }
        int ret = 0;
        Set<Integer> vis = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int parent = uf.find(i);
            if (vis.contains(parent)) continue;
            vis.add(parent);
            Set<Integer> set = map.get(parent);
            Map<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
            for (int idx : set) {
                map1.merge(source[idx], 1, Integer::sum);
                map2.merge(target[idx], 1, Integer::sum);
            }
            int same = 0;
            for (int key1 : map1.keySet()) {
                int cnt1 = map1.get(key1), cnt2 = map2.getOrDefault(key1, 0);
                same += Math.min(cnt1, cnt2);
            }
            ret += uf.size[parent] - same;
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
