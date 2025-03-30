import java.util.Arrays;

// 3504. Longest Palindrome After Substring Concatenation II
public class P3504 {
    public int[] manacher2(String s) {
        int n = s.length();
        int[] bestLength = new int[n];
        Arrays.fill(bestLength, 1);

        int[] d1 = new int[n];
        int l = 0, r = -1;
        for (int i = 0; i < n; i++) {
            int k = (i > r) ? 1 : Math.min(d1[l + r - i], r - i + 1);
            while (i - k >= 0 && i + k < n && s.charAt(i - k) == s.charAt(i + k)) {
                k++;
            }
            d1[i] = k;
            k--;
            if (i + k > r) {
                l = i - k;
                r = i + k;
            }
            int start = i - d1[i] + 1;
            int len = 2 * d1[i] - 1;
            if (start >= 0 && start < n && len > bestLength[start]) {
                bestLength[start] = len;
            }
        }

        int[] d2 = new int[n];
        l = 0;
        r = -1;
        for (int i = 0; i < n; i++) {
            int k = (i > r) ? 0 : Math.min(d2[l + r - i + 1], r - i + 1);
            while (i - k - 1 >= 0 && i + k < n && s.charAt(i - k - 1) == s.charAt(i + k)) {
                k++;
            }
            d2[i] = k;
            if (i + k - 1 > r) {
                l = i - k;
                r = i + k - 1;
            }
            int start = i - d2[i];
            int len = 2 * d2[i];
            if (start >= 0 && start < n && len > bestLength[start]) {
                bestLength[start] = len;
            }
        }

        return bestLength;
    }

    public int longestPalindrome(String s, String t) {
        int n1 = s.length(), n2 = t.length();
        int ret = 1;
        t = new StringBuilder(t).reverse().toString();
        int[] f = manacher2(s), g = manacher2(t);
        int[][] dp = new int[n1 + 1][n2 + 1];
        char[] cs = s.toCharArray(), ct = t.toCharArray();
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (cs[i] == ct[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
            }
        }
        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                ret = Math.max(ret, 2 * dp[i][j] + (i < n1 ? f[i] : 0));
                ret = Math.max(ret, 2 * dp[i][j] + (j < n2 ? g[j] : 0));
            }
        }
        return ret;
    }
}
