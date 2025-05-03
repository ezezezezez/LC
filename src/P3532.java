import java.util.Arrays;

// 3532. Path Existence Queries in a Graph I
public class P3532 {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int m = queries.length;
        boolean[] ret = new boolean[m];
        UF uf = new UF(n);
        for (int i = 0; i < n - 1; i++) {
            if (Math.abs(nums[i] - nums[i + 1]) <= maxDiff) {
                uf.union(i, i + 1);
            }
        }
        for (int i = 0; i < m; i++) {
            int u = queries[i][0], v = queries[i][1];
            if (uf.connected(u, v)) ret[i] = true;
        }
        return ret;
    }

    class UF {
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
