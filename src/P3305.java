// 3305. Count of Substrings Containing Every Vowel and K Consonants I
public class P3305 {
    public int countOfSubstrings(String word, int k) {
        int ret = 0;
        char[] cs = word.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            for (int j = i; j < n; j++) {
                cnt[cs[j] - 'a']++;
                int a = cnt['a' - 'a'];
                int e = cnt['e' - 'a'];
                int ii = cnt['i' - 'a'];
                int o = cnt['o' - 'a'];
                int u = cnt['u' - 'a'];
                if (a > 0 && e > 0 && ii > 0 && o > 0 && u > 0 && j - i + 1 - a - e - ii - o - u == k) ret++;
            }
        }
        return ret;
    }
}
