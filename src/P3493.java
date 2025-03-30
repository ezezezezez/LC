import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 3493. Properties Graph
public class P3493 {
    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length, m = properties[0].length;
        UF uf = new UF(n);
        List<Set<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int num : properties[i]) set.add(num);
            list.add(set);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int count = 0;
                for (int num : list.get(i)) {
                    if (list.get(j).contains(num)) count++;
                }
                if (count >= k) uf.union(i, j);
            }
        }
        return uf.setCount;
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
