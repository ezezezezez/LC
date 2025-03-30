import java.util.Arrays;

// 2957. Remove Adjacent Almost-Equal Characters
public class P2957 {
    public int removeAlmostEqualCharacters(String word) {
        int n = word.length();
        char[] cs = word.toCharArray();
        int[][] dp = new int[n + 1][26];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);
        for (int i = 0; i < 26; i++) dp[0][i] = 0;
        for (int i = 0; i < n; i++) {
            char t = cs[i];
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    if (Math.abs(j - k) > 1) {
                        boolean same = t - 'a' == j;
                        dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][k] + (same ? 0 : 1));
                    }
                }
            }
        }
        // for (int[] row : dp) System.out.println(Arrays.toString(row));
        int ret = Integer.MAX_VALUE;
        for (int v : dp[n]) ret = Math.min(ret, v);
        return ret;
    }

    public int removeAlmostEqualCharacters2(String word) {
        int n = word.length();
        char[] cs = word.toCharArray();
        int ret = 0;
        for (int i = 1; i < n; i++) {
            if (Math.abs(cs[i] - cs[i - 1]) <= 1) {
                ret++;
                i++;
            }
        }
        return ret;
    }
}
