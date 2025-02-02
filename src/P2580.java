import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class P2580 {
    long M = (long)(1e9 + 7);
    public int countWays(int[][] ranges) {
        int n = ranges.length;
        UF uf = new UF(n);
        Arrays.sort(ranges, Comparator.comparing((int[] arr) -> arr[0]).thenComparing((int[] arr) -> arr[1]));
        int r = ranges[0][1];
        for (int i = 1; i < n; i++) {
            int l = ranges[i][0], rr = ranges[i][1];
            if (l <= r) {
                uf.union(i - 1, i);
                r = Math.max(r, rr);
            } else {
                r = rr;
            }
        }
        Set<Integer> groups = new HashSet<>();
        for (int i = 0; i < n; i++) groups.add(uf.find(i));
        return (int)pow(2, groups.size());
    }

    long pow(long x, long y) {
        long ret = 1;
        long cur = x;
        while (y != 0) {
            if ((y & 1) != 0) {
                ret = ret * cur % M;
            }
            cur = cur * cur % M;
            y >>= 1;
        }
        return ret;
    }

    public class UF {
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
