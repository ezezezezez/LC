// 3361. Shift Distance Between Two Strings

public class P3361 {
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        int n = s.length();
        long[][] cost = new long[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j < 26; j++) {
                cost[i][(i + j) % 26] = cost[i][(i + j - 1) % 26] + nextCost[(i + j - 1) % 26];
            }
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j < 26; j++) {
                cost[i][(i - j + 26) % 26] = Math.min(cost[i][(i - j + 26) % 26], cost[i][(i - j + 1 + 26) % 26] + previousCost[(i - j + 1 + 26) % 26]);
            }
        }
        long ret = 0;
        for (int i = 0; i < n; i++) {
            ret += cost[s.charAt(i) - 'a'][t.charAt(i) - 'a'];
        }

        return ret;
    }
}
