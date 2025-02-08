import java.util.Arrays;

// 2746. Decremental String Concatenation
public class P2746 {
    public int minimizeConcatenatedLength(String[] words) {
        int n = words.length;
        String cur = words[0];
        int[][][] dp = new int[n][26][26];
        for (int[][] arr : dp) {
            for (int[] arr2 : arr) {
                Arrays.fill(arr2, Integer.MAX_VALUE / 2);
            }
        }
        dp[0][words[0].charAt(0) - 'a'][words[0].charAt(words[0].length() - 1) - 'a'] = words[0].length();
        for (int i = 1; i < n; i++) {
            String word = words[i];
            int s = word.charAt(0) - 'a', e = word.charAt(word.length() - 1) - 'a';
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    if (e == j) {
                        dp[i][s][k] = Math.min(dp[i][s][k], word.length() + dp[i - 1][j][k] - 1);
                    }
                    if (k == s) {
                        dp[i][j][e] = Math.min(dp[i][j][e], word.length() + dp[i - 1][j][k] - 1);
                    }
                    dp[i][s][k] = Math.min(dp[i][s][k], dp[i - 1][j][k] + word.length());
                    dp[i][j][e] = Math.min(dp[i][j][e], dp[i - 1][j][k] + word.length());
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                // System.out.println(i + " " + j + " " + dp[Math.max(0, n - 2)][i][j]);
                if (dp[n - 1][i][j] == 0) continue;
                ret = Math.min(ret, dp[n - 1][i][j]);
            }
        }
        return ret;
    }
}
