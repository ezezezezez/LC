// 3355. Zero Array Transformation I

public class P3356 {
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int lo = 0, hi = m, t = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int[] diff = new int[n + 1];
            for (int i = 0; i < mid; i++) {
                int l = queries[i][0], r = queries[i][1], val = queries[i][2];
                diff[l] += val;
                diff[r + 1] -= val;
            }
            int prefix = 0;
            boolean valid = true;
            for (int i = 0; i < n; i++) {
                prefix += diff[i];
                if (prefix < nums[i]) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return t;
    }
}
