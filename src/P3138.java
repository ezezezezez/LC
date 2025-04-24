import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 3138. Minimum Length of Anagram Concatenation
public class P3138 {
    public int minAnagramLength(String s) {
        int n = s.length();
        List<Integer> factors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }
        Collections.sort(factors);
        int[][] cnt = new int[n + 1][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                cnt[i + 1][j] = cnt[i][j];
            }
            cnt[i + 1][s.charAt(i) - 'a']++;
        }
        for (int len : factors) {
            boolean valid = true;
            outer:
            for (int i = len - 1; i < n; i += len) {
                for (int j = 0; j < 26; j++) {
                    if (cnt[i + 1][j] - cnt[i + 1 - len][j] != cnt[len][j]) {
                        valid = false;
                        break outer;
                    }
                }
            }
            if (valid) return len;
        }
        return n;
    }
}
