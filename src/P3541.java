import java.util.*;

// 3541. Find Most Frequent Vowel and Consonant
public class P3541 {
    public int maxFreqSum(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[cs[i] - 'a']++;
        }
        int mx1 = 0, mx2 = 0;
        for (int i = 0; i < 26; i++) {
            if ("aeiou".indexOf((char) (i + 'a')) != -1) {
                mx1 = Math.max(mx1, cnt[i]);
            } else {
                mx2 = Math.max(mx2, cnt[i]);
            }
        }
        return mx1 + mx2;
    }
}
