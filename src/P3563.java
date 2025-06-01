// 3563. Lexicographically Smallest String After Adjacent Removals
public class P3563 {
    // Helper to check if two characters are consecutive in the alphabet (with wraparound)
    private boolean areConsecutive(char a, char b) {
        int diff = Math.abs(a - b);
        return (diff == 1) || (diff == 25);  // 25 covers 'a' vs 'z'
    }

    public String lexicographicallySmallestString(String s) {
        int n = s.length();
        // dp[i][j] will hold the smallest string for substring s[i..j]
        String[][] dp = new String[n][n];

        // Build DP table for substrings of increasing length
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    // Single character (no removal possible)
                    dp[i][j] = Character.toString(s.charAt(i));
                } else {
                    // Start by keeping s[i] (Option A)
                    String best = Character.toString(s.charAt(i)) + dp[i + 1][j];

                    // Try to remove s[i] by pairing with some s[k]
                    for (int k = i + 1; k <= j; k++) {
                        if (areConsecutive(s.charAt(i), s.charAt(k))) {
                            // If k == i+1, there's nothing between i and k
                            // Otherwise check that s[i+1..k-1] is fully removable
                            if (k == i + 1 || dp[i + 1][k - 1].isEmpty()) {
                                // Candidate result is dp[k+1][j] (or empty if k==j)
                                String candidate = (k < j ? dp[k + 1][j] : "");
                                // Choose lexicographically smaller
                                if (candidate.compareTo(best) < 0) {
                                    best = candidate;
                                }
                            }
                        }
                    }
                    dp[i][j] = best;
                }
            }
        }
        return dp[0][n - 1];
    }
}
