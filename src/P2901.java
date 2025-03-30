import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 2901. Longest Unequal Adjacent Groups Subsequence II
public class P2901 {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        List<String> ret = new ArrayList<>();
        int[][] dp = new int[n + 1][2];
        for (int i = 0; i <= n; i++) dp[i] = new int[] {-1, 0};
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int group = groups[i];
            dp[i + 1][0] = -1;
            dp[i + 1][1] = 1;
            for (int j = i - 1; j >= 0; j--) {
                int diff = 0;
                String word2 = words[j];
                int group2 = groups[j];
                if (group2 == group) continue;
                if (word.length() != word2.length()) continue;
                for (int k = 0; k < word.length(); k++) {
                    if (word.charAt(k) != word2.charAt(k)) diff++;
                }
                if (diff != 1) continue;
                if (dp[j + 1][1] + 1 > dp[i + 1][1]) {
                    dp[i + 1][0] = j;
                    dp[i + 1][1] = dp[j + 1][1] + 1;
                }
            }
        }
        int mx = 0, idx = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i + 1][1] > mx) {
                mx = dp[i + 1][1];
                idx = i;
            }
        }
        while (idx != -1) {
            ret.add(words[idx]);
            idx = dp[idx + 1][0];
        }
        Collections.reverse(ret);
        return ret;
    }
}
