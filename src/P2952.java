import java.util.*;

// 2952. Minimum Number of Coins to be Added
public class P2952 {
    public int minimumAddedCoins(int[] coins, int target) {
        int n = coins.length;
        Arrays.sort(coins);
        int max = 0;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int x = coins[i];
            while (x - max > 1) {
                ret++;
                max += max + 1;
            }
            max += x;
            if (max >= target) break;
        }
        while (max < target) {
            ret++;
            max += max + 1;
        }
        return ret;
    }
}
