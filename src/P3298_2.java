// 3298. Count Substrings That Can Be Rearranged to Contain a String II
public class P3298_2 {
    public long validSubstringCount(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        long ret = 0;
        int[] b = new int[26];
        for (char c : word2.toCharArray()) {
            b[c - 'a']++;
        }
        int more = 0;
        for (int cnt : b) {
            if (cnt > 0) more++;
        }
        int j = 0;
        for (int i = 0; i < n; i++) {
            char c = word1.charAt(i);
            b[c - 'a']--;
            if (b[c - 'a'] == 0) more--;
            while (more == 0) {
                char x = word1.charAt(j);
                b[x - 'a']++;
                if (b[x - 'a'] > 0) {
                    more++;
                }
                j++;
            }
            ret += j;
        }
        return ret;
    }
}
