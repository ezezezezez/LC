// 3563. Lexicographically Smallest String After Adjacent Removals
public class P3563_2 {
    public String lexicographicallySmallestString(String s) {
        int n = s.length();

        String[][] dp = new String[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = s.substring(i, j + 1);
                } else {
                    String best = s.charAt(i) + dp[i + 1][j];

                    for (int k = i + 1; k <= j; k++) {
                        if (canRemove(s.charAt(i), s.charAt(k)) && (i + 1 == k || dp[i + 1][k - 1].isEmpty())) {
                            String candidate = k < j ? dp[k + 1][j] : "";
                            if (candidate.compareTo(best) < 0) {
                                best = candidate;
                            }
                        }
                    }
                    dp[i][j] = best;
                }
            }
        }

        return dp[0][n - 1];
    }

    boolean canRemove(char a, char b) {
        int diff = Math.abs(a - b);
        return diff == 1 || diff == 25;
    }
}
