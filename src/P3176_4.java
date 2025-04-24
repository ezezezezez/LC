import java.util.HashMap;
import java.util.Map;

// 3176. Find the Maximum Length of a Good Subsequence I
public class P3176_4 {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, int[]> f = new HashMap<>();
        int[][] helper = new int[k + 1][3];
        for (int num : nums) {
            int[] g = f.computeIfAbsent(num, kk -> new int[k + 1]);
            for (int i = k; i >= 0; i--) {
                if (i > 0) {
                    int mx1 = helper[i - 1][0], mx2 = helper[i - 1][1], mxNum = helper[i - 1][2];
                    if (mxNum != num) {
                        g[i] = Math.max(g[i] + 1, mx1 + 1);
                    } else {
                        g[i] = Math.max(g[i] + 1, mx2 + 1);
                    }
                } else {
                    g[i]++;
                }
                if (g[i] > helper[i][0]) {
                    if (num == helper[i][2]) {
                        helper[i][0] = g[i];
                    } else {
                        helper[i][1] = helper[i][0];
                        helper[i][0] = g[i];
                        helper[i][2] = num;
                    }
                } else if (g[i] > helper[i][1]) {
                    helper[i][1] = g[i];
                }
            }
        }
        return helper[k][0];
    }
}
