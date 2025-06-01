// 3325. Count Substrings With K-Frequency Characters I

public class P3325_3 {
    public int numberOfSubstrings(String s, int k) {
        int n = s.length();
        int[] cnt = new int[26];

        int j = 0;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            cnt[c - 'a']++;
            while (cnt[c - 'a'] >= k) {
                cnt[s.charAt(j) - 'a']--;
                j++;
            }

            ret += j;
        }

        return ret;
    }
}
