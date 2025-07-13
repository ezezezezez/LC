import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 3117. Minimum Sum of Values by Dividing Array

public class P3117 {
    int INF = Integer.MAX_VALUE / 2;

    public int minimumValueSum(int[] nums, int[] andValues) {
        int n = nums.length, m = andValues.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0;

        for (int i = 0; i < m; i++) {
            Deque<Integer> dq = new ArrayDeque<>();
            int an = 0;
            int[] and = new int[18];
            int[] left = new int[18];
            int qi = 0;
            for (int j = 0; j < n; j++) {
                int cur = nums[j];
                for (int p = 0; p < an; p++) {
                    and[p] &= cur;
                }
                and[an] = cur;
                left[an] = j;
                an++;
                // System.out.println("2. " + i + " " + j + " " + an);

                int q = 0;
                int last = -1;
                for (int p = 0; p < an; p++) {
                    if (and[p] >= andValues[i] && and[p] != last) {
                        and[q] = and[p];
                        last = and[p];
                        left[q] = left[p];
                        q++;
                    }
                }

                an = q;
                // System.out.println("3. " + i + " " + j + " " + an + " " + left[0] + " " + and[0]);
                if (an > 0 && and[0] == andValues[i]) {
                    int r = an > 1 ? left[1] - 1 : j;
                    // System.out.println("1. " + i + " " + j + " " + an + " " + r + " " + left[0]);
                    for (; qi <= r; qi++) {
                        while (!dq.isEmpty() && dp[i][dq.peekLast()] >= dp[i][qi]) {
                            dq.pollLast();
                        }
                        dq.offerLast(qi);
                    }
                    // System.out.println(dq);
                    while (!dq.isEmpty() && dq.peekFirst() < left[0]) dq.pollFirst();
                    if (!dq.isEmpty() && dp[i][dq.peekFirst()] != INF)
                        dp[i + 1][j + 1] = dp[i][dq.peekFirst()] + nums[j];
                }
            }
        }

        // printGrid2D(dp);

        return dp[m][n] == INF ? -1 : dp[m][n];
    }

    void printGrid2D(int[][] grid) {
        for (int[] ints : grid) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
