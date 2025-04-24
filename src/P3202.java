import java.util.Arrays;

// 3202. Find the Maximum Length of Valid Subsequence II
public class P3202 {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] f = new int[n + 1][k];
        for (int i = 1; i <= n; i++) Arrays.fill(f[i], 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int sum = (nums[i] + nums[j]) % k;
                f[i + 1][sum] = Math.max(f[i + 1][sum], 1 + f[j + 1][sum]);
            }
        }
        // printGrid2D(f);
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                ret = Math.max(ret, f[i + 1][j]);
            }
        }
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
