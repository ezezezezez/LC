import java.util.Arrays;

// 3201. Find the Maximum Length of Valid Subsequence I
public class P3201 {
    public int maximumLength(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n + 1][4];
        for (int i = 0; i < n; i++) {
            int cur = nums[i] % 2;
            f[i + 1][0] = f[i][0];
            f[i + 1][1] = f[i][1];
            f[i + 1][2] = f[i][2];
            f[i + 1][3] = f[i][3];
            f[i + 1][cur] = f[i][cur ^ 1] + 1;
            f[i + 1][cur + 2] = f[i][cur + 2] + 1;
        }
        return Math.max(Math.max(f[n][0], f[n][1]), Math.max(f[n][2], f[n][3]));
    }
}
