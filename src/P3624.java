import java.util.ArrayList;
import java.util.List;

// 3624. Number of Integers With Popcount-Depth Equal to K II
public class P3624 {
    public int[] popcountDepth(long[] nums, long[][] queries) {
        int n = nums.length, m = queries.length;
        BIT1 bit = new BIT1(n);

        for (int i = 0; i < n; i++) {
            // int depth = getPopCountDepth(nums[i]);
            // System.out.println(depth);
            bit.update(i + 1, getPopCountDepth(nums[i]), 1);
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            long[] query = queries[i];
            int type = (int) query[0];
            if (type == 1) {
                int l = (int) query[1], r = (int) query[2], k = (int) query[3];
                res.add((int) bit.query(l + 1, r + 1, k));
            } else {
                int idx = (int) query[1];
                long newV = query[2];
                long oldV = nums[idx];
                bit.update(idx + 1, getPopCountDepth(oldV), -1);
                bit.update(idx + 1, getPopCountDepth(newV), 1);
                nums[idx] = newV;
            }
        }

        int[] ret = new int[res.size()];
        for (int i = 0; i < ret.length; i++) ret[i] = res.get(i);

        return ret;
    }

    int getPopCountDepth(long x) {
        int idx = 0;
        while (x != 1) {
            x = Long.bitCount(x);
            idx++;
        }
        return idx;
    }

    static class BIT1 {
        long[][] tree;
        int n;
        int MX_IDX;

        BIT1(int n) {
            this.n = n;
            tree = new long[6][n + 1];
            while ((1 << MX_IDX) <= n) MX_IDX++;
        }

        // x: 1-based
        void update(int index, int popCount, long delta) {
            if (index <= 0 || index > n)
                throw new IllegalArgumentException(String.format("index(%s) should be in [1, n(%s)]", index, n));
            for (int i = index; i <= n; i += lowbit(i)) {
                tree[popCount][i] += delta;
            }
        }

        // sum of val[1...r]
        private long query(int r, int popCount) {
            if (r < 0 || r > n)
                throw new IllegalArgumentException(String.format("r(%s) should be in [0, n(%s)]", r, n));
            long ret = 0;
            for (int i = r; i > 0; i -= lowbit(i)) {
                ret += tree[popCount][i];
            }
            return ret;
        }

        // l, r: 1-based
        long query(int l, int r, int popCount) {
            if (r > n || l <= 0 || l > r) {
                throw new IllegalArgumentException(String.format("l(%s), r(%s) should be in [1, n(%s)] and l should <= r", l, r, n));
            }
            return query(r, popCount) - query(l - 1, popCount);
        }

        int lowbit(int x) {
            return x & -x;
        }
    }
}
