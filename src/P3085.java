import java.util.Arrays;

// 3085. Minimum Deletions to Make String K-Special
public class P3085 {
    public int minimumDeletions(String word, int k) {
        int n = word.length();
        int ret = Integer.MAX_VALUE;
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[word.charAt(i) - 'a']++;
        }
        Arrays.sort(cnt);
        int r = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) continue;
            int t = 0;
            for (int j = 25; cnt[j] - cnt[i] > k; j--) {
                t += cnt[j] - cnt[i] - k;
            }
            ret = Math.min(ret, t + r);
            r += cnt[i];
        }
        return ret;
    }
}
