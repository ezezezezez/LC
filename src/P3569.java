// 3569. Maximize Count of Distinct Primes After Split

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class P3569 {
    static int MAXV = 100000;
    static boolean[] isp = new boolean[MAXV + 1];

    static {
        Arrays.fill(isp, true);
        isp[0] = false;
        isp[1] = false;
        for (int i = 2; i * i <= MAXV; i++) {
            if (isp[i]) {
                for (int j = i * i; j <= MAXV; j += i) {
                    isp[j] = false;
                }
            }
        }
    }

    public int[] maximumCount(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        L = 1;
        R = n - 1;
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (isp[num]) map.computeIfAbsent(num, kk -> new TreeSet<>()).add(i);
        }

        int[] ret = new int[m];

        int staticCount = map.size();
        for (int v : map.keySet()) {
            int mn = map.get(v).first();
            int mx = map.get(v).last();
            if (mn < mx) {
                root.update(root, L, R, mn + 1, mx, 1);
            }
        }

        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int idx = query[0], val = query[1];
            if (nums[idx] == val) {
                ret[i] = staticCount + root.query(root, L, R, L, R);
                continue;
            }

            int ov = nums[idx];
            if (isp[ov]) {
                TreeSet<Integer> set = map.get(ov);
                int mn = set.first();
                int mx = set.last();
                if (mn < mx) {
                    root.update(root, L, R, mn + 1, mx, -1);
                }
                set.remove(idx);
                if (set.isEmpty()) {
                    staticCount--;
                    map.remove(ov);
                } else {
                    mn = set.first();
                    mx = set.last();
                    if (mn < mx) {
                        root.update(root, L, R, mn + 1, mx, 1);
                    }
                }
            }

            if (isp[val]) {
                if (!map.containsKey(val)) {
                    TreeSet<Integer> set = new TreeSet<>();
                    set.add(idx);
                    staticCount++;
                    map.put(val, set);
                } else {
                    TreeSet<Integer> set = map.get(val);
                    int mn = set.first();
                    int mx = set.last();
                    if (mn < mx) {
                        root.update(root, L, R, mn + 1, mx, -1);
                    }

                    set.add(idx);
                    mn = set.first();
                    mx = set.last();
                    if (mn < mx) {
                        root.update(root, L, R, mn + 1, mx, 1);
                    }
                }
            }

            nums[idx] = val;
            ret[i] = staticCount + root.query(root, L, R, L, R);
        }

        return ret;
    }

    int L;
    int R;
    RangeMaxIntDelta root = new RangeMaxIntDelta();

    static class RangeMaxIntDelta {
        RangeMaxIntDelta ls;
        RangeMaxIntDelta rs;
        int val;
        int lazy;

        void create(RangeMaxIntDelta x) {
            if (x.ls == null) x.ls = new RangeMaxIntDelta();
            if (x.rs == null) x.rs = new RangeMaxIntDelta();
        }

        void pushDown(RangeMaxIntDelta x) {
            create(x);
            if (x.lazy != 0) {
                int delta = x.lazy;
                x.ls.val += delta;
                x.ls.lazy += delta;
                x.rs.val += delta;
                x.rs.lazy += delta;
                x.lazy = 0;
            }
        }

        void pushUp(RangeMaxIntDelta x) {
            x.val = Math.max(x.ls.val, x.rs.val);
        }

        void update(RangeMaxIntDelta x, int l, int r, int s, int t, int v) {
            if (s > r || t < l) return;
            if (s <= l && r <= t) {
                x.val += v;
                x.lazy += v;
                return;
            }
            pushDown(x);

            int mid = (l + r) >> 1;
            if (s <= mid) update(x.ls, l, mid, s, t, v);
            if (t > mid) update(x.rs, mid + 1, r, s, t, v);
            pushUp(x);
        }

        int query(RangeMaxIntDelta x, int l, int r, int s, int t) {
            if (s > r || t < l) return Integer.MIN_VALUE;
            if (s <= l && r <= t) {
                return x.val;
            }
            pushDown(x);

            int mid = (l + r) >> 1;
            int leftMax = query(x.ls, l, mid, s, t);
            int rightMax = query(x.rs, mid + 1, r, s, t);
            return Math.max(leftMax, rightMax);
        }
    }
}
