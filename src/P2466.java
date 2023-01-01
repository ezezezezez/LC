import java.io.*;
import java.lang.*;
import java.util.*;

// 2466. Count Ways To Build Good Strings

public class P2466 {
    public int countGoodStrings(int low, int high, int zero, int one) {
        int ret = 0;
        int mod = (int)(1e9 + 7);
        int[] f = new int[high + 1];
        f[0] = 1;
        for (int i = 1; i <= high; i++) {
            if (i - zero >= 0) {
                f[i] = (f[i] + f[i - zero]) % mod;
            }
            if (i - one >= 0) {
                f[i] = (f[i] + f[i - one]) % mod;
            }
            if (i >= low) {
                ret = (ret + f[i]) % mod;
            }
        }
        return ret;
    }
}
