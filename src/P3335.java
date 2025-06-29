// 3335. Total Characters in String After Transformations I

public class P3335 {
    public int lengthAfterTransformations(String s, int t) {
        int n = s.length();
        int mod = 1000000007;
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t; i++) {
            int[] cnt2 = new int[26];
            for (int j = 0; j < 26; j++) {
                if (cnt[j] == 0) continue;
                if (j == 25) {
                    cnt2[0] = cnt[j];
                    cnt2[1] = (cnt2[1] + cnt[j]) % mod;
                } else {
                    cnt2[j + 1] = cnt[j];
                }
            }
            cnt = cnt2;
        }
        int ret = 0;
        for (int i = 0; i < 26; i++) ret = (ret + cnt[i]) % mod;
        return ret;
    }
}
