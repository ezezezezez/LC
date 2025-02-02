import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 2707. Extra Characters in a String
public class P2707 {
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length(), m = dictionary.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        Set<String> set =  new HashSet<>();
        for (String word : dictionary) set.add(word);
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i] + 1;
            for (int j = i; j >= 0; j--) {
                String sub = s.substring(j, i + 1);
                if (set.contains(sub)) {
                    dp[i + 1] = Math.min(dp[i + 1], dp[j]);
                }
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}
