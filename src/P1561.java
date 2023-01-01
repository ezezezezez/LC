import java.io.*;
import java.lang.*;
import java.util.*;

// 1561. Maximum Number of Coins You Can Get

public class P1561 {
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int n = piles.length;
        int m = n / 3;
        int ret = 0;
        for (int i = m; i < n; i += 2) {
            ret += piles[i];
        }
        return ret;
    }
}
