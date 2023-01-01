import java.util.*;
import java.io.*;
import java.lang.*;

// 1319. Number of Operations to Make Network Connected

public class P1319 {
    public int makeConnected(int n, int[][] connections) {
        int m = connections.length;
        int used = 0;

        UF uf = new UF(n);
        for (int[] connection : connections) {
            int a = connection[0], b = connection[1];
            if (uf.union(a, b)) {
                used++;
            }
        }

        int remConnection = m - used;
        int needConnection = uf.setCount - 1;
        if (remConnection < needConnection) return -1;
        return needConnection;
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
