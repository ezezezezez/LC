import java.util.Arrays;

// 3545. Minimum Deletions for At Most K Distinct Characters
public class P3545 {
    int ret = Integer.MAX_VALUE;
    int n;
    int tot;
    public int minDeletion(String s, int k) {
        n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) tot++;
        }
        if (tot <= k) return 0;
        Arrays.sort(cnt);
        int ret = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                ret += cnt[i];
                tot--;
                if (tot <= k) break;
            }
        }
        return ret;
    }
}
