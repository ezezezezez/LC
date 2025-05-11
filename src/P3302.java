import java.util.Arrays;

// 3302. Find the Lexicographically Smallest Valid Sequence
public class P3302 {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            if (m - 1 - f[i + 1] >= 0 && word1.charAt(i) == word2.charAt(m - 1 - f[i + 1])) {
                f[i] = f[i + 1] + 1;
            } else {
                f[i] = f[i + 1];
            }
        }
        boolean changed = false;
        int[] ret = new int[m];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                ret[j++] = i;
            } else if (!changed && f[i + 1] >= m - 1 - j) {
                ret[j++] = i;
                changed = true;
            }
            if (j == m) return ret;
        }
        return new int[0];
    }
}
