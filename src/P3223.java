import java.util.Arrays;

// 3223. Minimum Length of String After Operations
public class P3223 {
    public int minimumLength(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        int ret = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] >= 3) {
                ret += cnt[i] % 2 == 1 ? 1 : 2;
            } else if (cnt[i] > 0) {
                ret += cnt[i];
            }
        }
        return ret;
    }
}
