import java.util.*;

// 3660. Jump Game IX
public class P3660 {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        int maxV = 0;
        for (int i = 0; i < n; i++) {
            maxV = Math.max(maxV, nums[i]);
            if (nums[i] > nums[prefix[i]]) {
                prefix[i + 1] = i;
            } else {
                prefix[i + 1] = prefix[i];
            }
        }
        int[] ret = new int[n];
        TreeMap<Integer, Integer> tMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            tMap.put(nums[i], i);
        }
        int lastKey = -1;
        Map<Integer, Integer> helper = new HashMap<>();
        for (int key : tMap.keySet()) {
            helper.put(key, lastKey);
            int idx = tMap.get(key);
            lastKey = Math.max(lastKey, idx);
        }
        UF uf = new UF(n);
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (nums[prefix[i]] > num) uf.union(i, prefix[i]);
            int idx = helper.get(num);
            if (idx != -1 && idx > i) uf.union(i, idx);
        }
        // Map<Integer, Set<Integer>> sets = uf.getSets();
        Map<Integer, Integer> map = new HashMap<>();
        // for (int key : sets.keySet()) {
        //     int mx = 0;
        //     for (int v : sets.get(key)) {
        //         mx = Math.max(mx, nums[v]);
        //     }
        //     map.put(key, mx);
        // }
        for (int i = 0; i < n; i++) {
            int p = uf.find(i);
            map.merge(p, nums[i], Math::max);
        }
        for (int i = 0; i < n; i++) {
            int p = uf.find(i);
            ret[i] = map.get(p);
        }

        return ret;
    }

    static public class UF {
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
