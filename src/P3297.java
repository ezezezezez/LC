// 3297. Count Substrings That Can Be Rearranged to Contain a String I
public class P3297 {
    public long validSubstringCount(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] cnt1 = new int[26], cnt2 = new int[26];
        for (char c : word2.toCharArray()) {
            cnt2[c - 'a']++;
        }
        long ret = 0;
        for (int i = 0, j = 0; i < n; i++) {
            char c = word1.charAt(i);
            cnt1[c - 'a']++;
            while (valid(cnt1, cnt2)) {
                char t = word1.charAt(j);
                cnt1[t - 'a']--;
                j++;
            }
            ret += j;
        }
        return ret;
    }

    boolean valid(int[] cnt1, int[] cnt2) {
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] < cnt2[i]) return false;
        }
        return true;
    }
}
