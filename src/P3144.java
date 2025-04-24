import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 3144. Minimum Substring Partition of Equal Character Frequency
public class P3144 {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            for (int j = i; j >= 0; j--) {
                cnt[s.charAt(j) - 'a']++;
                if (valid(cnt)) {
                    f[i + 1] = Math.min(f[i + 1], f[j] + 1);
                }
            }
        }
        return f[n];
    }

    boolean valid(int[] cnt) {
        int t = -1;
        for (int c : cnt) {
            if (c == 0) continue;
            if (t == -1) {
                t = c;
            } else if (c != t) {
                return false;
            }
        }
        return true;
    }
}
