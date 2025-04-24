import java.util.Arrays;

// 3176. Find the Maximum Length of a Good Subsequence I
public class P3176_3 {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] f = new int[k + 1][n + 1];
        for (int i = 0; i < n; i++) {
            f[0][i + 1] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] == nums[i]) {
                    f[0][i + 1] = Math.max(f[0][i + 1], f[0][j + 1] + 1);
                }
            }
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                f[i][j + 1] = 1;
                for (int p = 0; p < j; p++) {
                    if (nums[p] == nums[j]) {
                        f[i][j + 1] = Math.max(f[i][j + 1], f[i][p + 1] + 1);
                    } else {
                        f[i][j + 1] = Math.max(f[i][j + 1], f[i - 1][p + 1] + 1);
                    }
                }
            }
        }
        // printGrid2D(f);
        int ret = 0;
        for (int i = 0; i < n; i++) ret = Math.max(ret, f[k][i + 1]);
        return ret;
    }

    void printGrid2D(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
