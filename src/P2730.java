import java.util.HashSet;
import java.util.Set;

// 2730. Find the Longest Semi-Repetitive Substring
public class P2730 {
    public int longestSemiRepetitiveSubstring(String s) {
        int ret = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                String sub = s.substring(j, i + 1);
                int cnt = 0;
                for (int k = 0; k < sub.length() - 1; k++) {
                    if (sub.charAt(k) == sub.charAt(k + 1)) cnt++;
                }
                if (cnt <= 1) {
                    ret = Math.max(ret, sub.length());
                }
            }
        }
        return ret;
    }
}
