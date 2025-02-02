import java.util.HashMap;
import java.util.Map;

public class P2559 {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length, m = queries.length;
        int[] ret = new int[m];
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            dp[i + 1] = dp[i];
            if (isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1))) dp[i + 1]++;
        }
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            ret[i] = dp[b + 1] - dp[a];
        }
        Map<Integer, Integer> map = new HashMap<>();
        return ret;
    }

    boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
