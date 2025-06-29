// 3337. Total Characters in String After Transformations II

import java.util.List;

public class P3337_2 {
    long mod = 1000000007;
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        int n = s.length();
        long[][] A = new long[26][26];
        for (int i = 0; i < 26; i++) {
            int v = nums.get(i);
            for (int j = 0; j < v; j++) {
                A[i][(i + 1 + j) % 26] = 1;
            }
        }

        long[][] f0 = new long[26][1];
        for (int i = 0; i < 26; i++) f0[i][0] = 1;

        long[][] res = pow(A, t, f0);
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;
        long ret = 0;
        for (int i = 0; i < 26; i++) {
            int v = cnt[i];
            ret = (ret + v * res[i][0] % mod) % mod;
        }

        return (int) ret;
    }

    // a^n * f0
    public long[][] pow(long[][] a, int n, long[][] f0) {
        long[][] res = f0;
        while (n > 0) {
            if ((n & 1) > 0) {
                res = mul(a, res);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }

    // a * b
    public long[][] mul(long[][] a, long[][] b) {
        long[][] c = new long[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < a[i].length; k++) {
                if (a[i][k] == 0) {
                    continue;
                }
                for (int j = 0; j < b[k].length; j++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return c;
    }
}
