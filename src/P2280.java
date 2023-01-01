import java.io.*;
import java.lang.*;
import java.util.*;

// 2280. Minimum Lines to Represent a Line Chart

public class P2280 {
    public int minimumLines(int[][] sp) {
        int n = sp.length;
        if (n == 1) return 0;
        Arrays.sort(sp, (a, b) -> Integer.compare(a[0], b[0]));
        int u = sp[1][1] - sp[0][1], v = sp[1][0] - sp[0][0];
        int[] pre = new int[]{u / gcd(u, v), v / gcd(u, v)};
        int ret = 1;
        for (int i = 2; i < n; i++) {
            u = sp[i][1] - sp[i - 1][1];
            v = sp[i][0] - sp[i - 1][0];
            int[] cur = new int[]{u / gcd(u, v), v / gcd(u, v)};
            if (cur[0] != pre[0] || cur[1] != pre[1]) {
                ret++;
            }
            pre = cur;
        }
        return ret;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
