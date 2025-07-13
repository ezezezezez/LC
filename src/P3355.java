// 3355. Zero Array Transformation I

public class P3355 {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] diff = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1];
            diff[l]++;
            diff[r + 1]--;
        }
        int prefix = 0;
        for (int i = 0; i < n; i++) {
            prefix += diff[i];
            if (prefix < nums[i]) return false;
        }
        return true;
    }
}
