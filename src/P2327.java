import java.io.*;
import java.lang.*;
import java.util.*;

// 2327. Number of People Aware of a Secret

public class P2327 {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int cnt = 1;
        int mod = (int)(1e9 + 7);
        int[] d = new int[n + 1];
        int[] f = new int[n + 1];
        d[1 + delay]++;
        int tell = 0;
        if (1 + forget <= n) {
            d[1 + forget]--;
            d[1 + forget] = Math.floorMod(d[1 + forget], mod);
            f[1 + forget]++;
        }
        for (int i = 2; i <= n; i++) {
            tell = Math.floorMod(tell + d[i], mod);
            cnt = Math.floorMod(cnt - f[i], mod);
            cnt = Math.floorMod(cnt + tell, mod);
            if (i + delay <= n) d[i + delay] = Math.floorMod(d[i + delay] + tell, mod);
            if (i + forget <= n) {
                d[i + forget] = Math.floorMod(d[i + forget] - tell, mod);
                f[i + forget] = Math.floorMod(f[i + forget] + tell, mod);
            }
        }
        return cnt;
    }
}
