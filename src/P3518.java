import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

// 3518. Smallest Palindromic Rearrangement II
public class P3518 {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int m = n / 2;
        int[] cnt = new int[26];
        for (int i = 0; i < m; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        if (perm(m, cnt, k) < k) return "";
        StringBuilder sb = new StringBuilder(n);
        char[] carr = new char[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 26; j++) {
                if (cnt[j] == 0) continue;
                cnt[j]--;
                int t = perm(m - (i + 1), cnt, k);
                if (t >= k) {
                    carr[i] = (char) (j + 'a');
                    break;
                }
                k -= t;
                cnt[j]++;
            }
        }
        sb.append(carr);
        if (n % 2 == 1) sb.append(s.charAt(n / 2));
        for (int i = m - 1; i >= 0; i--) {
            sb.append(carr[i]);
        }
        return sb.toString();
    }

    int perm(int sz, int[] cnt, int k) {
        long ret = 1L;
        for (int c : cnt) {
            if (c == 0) continue;
            ret = ret * comb(sz, c, k);
            if (ret >= k) {
                return k;
            }
            sz -= c;
        }
        return (int) ret;
    }

    int comb(int n, int r, int k) {
        long ret = 1L;
        int t = Math.min(r, n - r);
        for (int i = 1; i <= t; i++) {
            ret = ret * (n + 1 - i) / i;
            if (ret >= k) {
                return k;
            }
        }
        return (int) ret;
    }
}
