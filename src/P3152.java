import java.util.List;

// 3152. Special Array II
public class P3152 {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        boolean[] ret = new boolean[m];
        int[] prefix = new int[n];
        for (int i = 0; i < n - 1; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] % 2 == nums[i + 1] % 2 ? 1 : 0);
        }
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1];
            if (prefix[l] == prefix[r]) {
                ret[i] = true;
            } else {
                ret[i] = false;
            }
        }
        return ret;
    }
}
