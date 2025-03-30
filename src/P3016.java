import java.util.Arrays;

// 3016. Minimum Number of Pushes to Type Word II
public class P3016 {
    public int minimumPushes(String word) {
        int n = word.length();
        int ret = 0;
        int[] f = new int[26];
        for (int i = 0; i < n; i++) {
            f[word.charAt(i) - 'a']++;
        }
        Arrays.sort(f);
        for (int i = 0; i < f.length / 2; i++) {
            int t = f[i];
            f[i] = f[f.length - 1 - i];
            f[f.length - 1 - i] = t;
        }
        for (int i = 0; i < 8; i++) {
            ret += f[i];
        }
        for (int i = 8; i < 16; i++) {
            ret += 2 * f[i];
        }
        for (int i = 16; i < 24; i++) {
            ret += 3 * f[i];
        }
        for (int i = 24; i < 26; i++) {
            ret += 4 * f[i];
        }
        return ret;
    }
}
