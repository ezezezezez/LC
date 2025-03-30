import java.util.*;

// 2948. Make Lexicographically Smallest Array by Swapping Elements
public class P2948 {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;
        Arrays.sort(indices, (a, b) -> Integer.compare(nums[a], nums[b]));
        UF uf = new UF(n);
        for (int i = 1; i < n; i++) {
            if (nums[indices[i]] - nums[indices[i - 1]] <= limit) {
                uf.union(indices[i], indices[i - 1]);
            }
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(uf.find(i), k -> new ArrayList<>()).add(i);
        }
        // System.out.println(map);
        int[] ret = new int[n];
        for (List<Integer> list : map.values()) {
            List<Integer> idx = new ArrayList<>(list);
            idx.sort((a, b) -> Integer.compare(a, b));
            list.sort((a, b) -> Integer.compare(nums[a], nums[b]));
            for (int i = 0; i < idx.size(); i++) {
                ret[idx.get(i)] = nums[list.get(i)];
            }
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
