import java.util.*;

public class P2606 {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int n = s.length();
        int m = vals.length;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            char c = chars.charAt(i);
            int val = vals[i];
            map.put(c, val);
        }
        int[] dp = new int[n];
        dp[0] = map.containsKey(s.charAt(0)) ? map.get(s.charAt(0)) : s.charAt(0) - 'a' + 1;
        int cost = Math.max(0, dp[0]);
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            dp[i] = Math.max(0, dp[i - 1]) + (map.containsKey(c) ? map.get(c) : c - 'a' + 1);
            cost = Math.max(cost, dp[i]);
        }
        return cost;
    }
}
