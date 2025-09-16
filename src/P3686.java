// 3686. Number of Stable Subsequences

public class P3686 {
    int mod = 1000000007;

    public int countStableSubsequences(int[] nums) {
        int n = nums.length;
        int[][] dpOne = new int[n + 1][2], dpTwo = new int[n + 1][2];
        for (int i = 0; i < n; i++) {
            dpOne[i + 1][0] = dpOne[i][0];
            dpOne[i + 1][1] = dpOne[i][1];
            dpTwo[i + 1][0] = dpTwo[i][0];
            dpTwo[i + 1][1] = dpTwo[i][1];
            int num = nums[i];
            if (num % 2 == 0) {
                int more = (dpOne[i][1] + dpTwo[i][1]) % mod;
                dpOne[i + 1][0] = (dpOne[i + 1][0] + 1 + more) % mod;
                dpTwo[i + 1][0] = (dpTwo[i + 1][0] + dpOne[i][0]) % mod;
            } else {
                int more = (dpOne[i][0] + dpTwo[i][0]) % mod;
                dpOne[i + 1][1] = (dpOne[i + 1][1] + 1 + more) % mod;
                dpTwo[i + 1][1] = (dpTwo[i + 1][1] + dpOne[i][1]) % mod;
            }
        }
        int a = (dpOne[n][0] + dpOne[n][1]) % mod;
        int b = (dpTwo[n][0] + dpTwo[n][1]) % mod;
        return (a + b) % mod;
    }
}
