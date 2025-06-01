// 3325. Count Substrings With K-Frequency Characters I

public class P3325_2 {
    public int numberOfSubstrings(String s, int k) {
        int n = s.length();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                if (++cnt[c - 'a'] >= k) {
                    ret += n - j;
                    break;
                }
            }
        }

        return ret;
    }
}
