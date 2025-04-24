import java.util.Arrays;

// 3180. Maximum Total Reward Using Operations I
public class P3180 {
    int[][] memo;
    int n;
    int[] nums;
    int mx;
    public int maxTotalReward(int[] rewardValues) {
        nums = rewardValues;
        Arrays.sort(nums);
        n = rewardValues.length;
        for (int num : nums) mx = Math.max(mx, num);
        memo = new int[n][mx + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        int ret = dfs(0, 0);
        // printGrid2D(memo);
        return ret;
    }

    int dfs(int idx, int sum) {
        if (idx == n) return sum;
        if (sum >= mx) return sum;
        if (memo[idx][sum] != -1) return memo[idx][sum];
        int ret = dfs(idx + 1, sum);
        if (nums[idx] > sum) ret = Math.max(ret, dfs(idx + 1, sum + nums[idx]));
        memo[idx][sum] = ret;
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
