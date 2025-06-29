// 3337. Total Characters in String After Transformations II

import java.util.List;

public class P3337 {
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        int mod = 1000000007;
        int n = s.length();
        int K = 31;
        long[][][] table = new long[26][K][26];
        for (int i = 0; i < 26; i++) {
            int v = nums.get(i);
            for (int j = 0; j < v; j++) {
                table[i][0][(i + 1 + j) % 26]++;
            }
        }
        for (int p = 0; p < K - 1; p++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    for (int k = 0; k < 26; k++) {
                        long cnt = table[i][p][j] * table[j][p][k] % mod;
                        table[i][p + 1][k] = (table[i][p + 1][k] + cnt) % mod;
                    }
                }
            }
        }
        long[] cnt = new long[26];
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;

        for (int k = K - 1; k >= 0; k--) {
            if (t > 0 && (1 << k) <= t) {
                t -= 1 << k;
                long[] temp = new long[26];
                for (int i = 0; i < 26; i++) {
                    if (cnt[i] == 0) continue;
                    for (int j = 0; j < 26; j++) {
                        temp[j] = (temp[j] + cnt[i] * table[i][k][j] % mod) % mod;
                    }
                }
                cnt = temp;
            }
        }

        long ret = 0;
        for (long v : cnt) ret = (ret + v) % mod;
        return (int) ret;
    }
}
