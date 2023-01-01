import java.io.*;
import java.lang.*;
import java.util.*;

// 2320. Count Number of Ways to Place Houses

public class P2320 {
    public int countHousePlacements(int n) {
        if (n == 1) return 4;
        if (n == 2) return 9;
        int mod = (int)(1e9 + 7);
        int ret = 9;
        int prepre = 4, pre = 9, preHalf = 6;
        for (int i = 3; i <= n; i++) {
            ret = (ret + prepre) % mod;
            ret = (ret + preHalf) % mod;
            ret = (ret + preHalf) % mod;
            preHalf = (preHalf + pre) % mod;
            prepre = pre;
            pre = ret;
        }
        return ret;
    }
}
