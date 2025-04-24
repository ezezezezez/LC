import java.util.Arrays;

// 3121. Count the Number of Special Characters II
public class P3121 {
    public int numberOfSpecialChars(String word) {
        int n = word.length();
        int ret = 0;
        int[] idx1 = new int[26], idx2 = new int[26];
        Arrays.fill(idx1, -1);
        Arrays.fill(idx2, -1);
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (c < 'a' || c > 'z') {
                if (idx2[c - 'A'] == -1) idx2[c - 'A'] = i;
            } else {
                idx1[c - 'a'] = i;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (idx1[i] != -1 && idx2[i] != -1 && idx1[i] < idx2[i]) ret++;
        }
        return ret;
    }
}
