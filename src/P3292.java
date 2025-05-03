import java.util.Arrays;

// 3292. Minimum Number of Valid Strings to Form Target II
public class P3292 {
    public int minValidStrings(String[] words, String target) {
        int n = target.length();
        int[] maxMatch = new int[n];
        for (String word : words) {
            int m = word.length();
            String combined = word + "$" + target;
            int[] z = Z(combined);
            for (int i = 0; i < n; i++) {
                int index = m + 1 + i;
                maxMatch[i] = Math.max(maxMatch[i], z[index]);
            }
        }
        int jumps = 0, curEnd = 0, farthest = 0;
        for (int i = 0; i < n; i++) {
            farthest = Math.max(farthest, maxMatch[i] + i);
            if (farthest >= n) break;
            if (i == curEnd) {
                if (curEnd == farthest) return -1;
                curEnd = farthest;
                jumps++;
            }
        }
        return jumps + 1;
    }

    int[] Z(String s) {
        int n = s.length();
        int[] z = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; ++i) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                ++z[i];
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }
}
