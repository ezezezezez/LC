import java.io.*;
import java.lang.*;
import java.util.*;

// 2305. Fair Distribution of Cookies

public class P2305 {
    public int distributeCookies(int[] cookies, int k) {
        int n = cookies.length;
        int[][] f = new int[1 << n][k + 1];
        for (int s = 1; s < (1 << n); s++) {
            int tot = 0;
            for (int p = 0; p < n; p++) {
                if (((1 << p) & s) > 0) {
                    tot += cookies[p];
                }
            }
            f[s][1] = tot;
        }
        for (int i = 2; i <= k; i++) {
            for (int s = 0; s < (1 << n); s++) {
                f[s][i] = Integer.MAX_VALUE;
                int cnt = 0;
                for (int p = 0; p < n; p++) {
                    if (((1 << p) & s) > 0) {
                        cnt++;
                    }
                }
                if (cnt < i) continue;
                for (int j = s; j > 0; j = (j - 1) & s) {
                    int cnt2 = 0, tot = 0;
                    for (int p = 0; p < n; p++) {
                        if (((1 << p) & j) > 0) {
                            cnt2++;
                            tot += cookies[p];
                        }
                    }
                    if (cnt - cnt2 < i - 1) continue;
                    f[s][i] = Math.min(f[s][i], Math.max(f[s ^ j][i - 1], tot));
                    // System.out.println(s + " " + i + " " + f[s][i]);
                }
            }
        }
        return f[(1 << n) - 1][k];
    }
}
