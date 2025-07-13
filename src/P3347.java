import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 3347. Maximum Frequency of an Element After Performing Operations II

public class P3347 {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        Arrays.sort(nums);
        int ret = 0;

        for (int i = 0; i < n; i++) {
            int t = i + 1;
            int op = numOperations;
            while (t < n && nums[t] == nums[i]) t++;
            int cnt = t - i;
            int loIdx = lowerBound(nums, 0, n, nums[i] - k);
            int rem = Math.min(i - loIdx, op);
            cnt += rem;
            op -= rem;
            if (op > 0) {
                int hiIdx = lowerBound(nums, 0, n, nums[i] + k + 1) - 1;
                rem = Math.min(hiIdx - t + 1, op);
                cnt += rem;
            }
            ret = Math.max(ret, cnt);
            i = t - 1;
        }

        int j = 0;
        for (int i = 0; i < n; i++) {
            root.update(root, L, R, nums[i], nums[i], 1);
            while (nums[i] - nums[j] > 2 * k) {
                root.update(root, L, R, nums[j], nums[j], -1);
                j++;
            }
            // System.out.println(j + " " + i);
            int lo2 = nums[i] - k, hi2 = nums[j] + k;
            int mxCnt = root.query(root, L, R, lo2, hi2);
            int rem = i - j + 1 - mxCnt;
            ret = Math.max(ret, Math.min(rem, numOperations) + mxCnt);
        }

        return ret;
    }

    int lowerBound(int[] nums, int lo, int hi, int val) {
        lo--;
        while (hi - lo > 1) {
            int mid = (lo + hi) >> 1;
            if (nums[mid] >= val) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }

    int L = 1;
    int R = (int)1e9 + 5;
    RangeMaxIntDelta root = new RangeMaxIntDelta();

    static class RangeMaxIntDelta {
        RangeMaxIntDelta ls, rs;
        int val;
        int lazy;

        public static int DEFAULT_VAL = 0;

        public RangeMaxIntDelta() {
            this.val = DEFAULT_VAL;
            this.lazy = 0;
        }

        private void create(RangeMaxIntDelta x) {
            if (x.ls == null) x.ls = new RangeMaxIntDelta();
            if (x.rs == null) x.rs = new RangeMaxIntDelta();
        }

        private void pushDown(RangeMaxIntDelta x) {
            create(x);
            if (x.lazy != 0) {
                int delta = x.lazy;

                x.ls.val = (x.ls.val == DEFAULT_VAL ? delta : x.ls.val + delta);
                x.ls.lazy += delta;

                x.rs.val = (x.rs.val == DEFAULT_VAL ? delta : x.rs.val + delta);
                x.rs.lazy += delta;

                x.lazy = 0;
            }
        }

        private void pushUp(RangeMaxIntDelta x) {
            int leftVal = (x.ls == null ? DEFAULT_VAL : x.ls.val);
            int rightVal = (x.rs == null ? DEFAULT_VAL : x.rs.val);
            x.val = Math.max(leftVal, rightVal);
        }

        public void update(RangeMaxIntDelta x, int l, int r, int s, int t, int v) {
            if (s > r || t < l) {
                return;
            }
            if (s <= l && r <= t) {
                x.val = (x.val == DEFAULT_VAL ? v : x.val + v);
                x.lazy += v;
                return;
            }
            pushDown(x);

            int mid = (l + r) >>> 1;
            if (s <= mid) update(x.ls, l, mid, s, t, v);
            if (t > mid) update(x.rs, mid + 1, r, s, t, v);
            pushUp(x);
        }

        public int query(RangeMaxIntDelta x, int l, int r, int s, int t) {
            if (s > r || t < l) {
                return DEFAULT_VAL;
            }
            if (s <= l && r <= t) {
                return x.val;
            }
            pushDown(x);

            int mid = (l + r) >>> 1;
            int leftMax = query(x.ls, l, mid, s, t);
            int rightMax = query(x.rs, mid + 1, r, s, t);
            return Math.max(leftMax, rightMax);
        }
    }
}
