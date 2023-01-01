import java.io.*;
import java.lang.*;
import java.util.*;

// 2498. Frog Jump II

public class P2498 {
    public int maxJump(int[] stones) {
        int n = stones.length;
        int cost = 0;
        boolean[] used = new boolean[n];
        int pre = 0;
        for (int i = Math.min(2, n - 1); i < n; i = Math.max(i + 1, Math.min(i + 2, n - 1))) {
            cost = Math.max(cost, stones[i] - pre);
            pre = stones[i];
            used[i] = true;
        }
        pre = stones[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (used[i]) continue;
            cost = Math.max(cost, pre - stones[i]);
            pre = stones[i];
        }
        return cost;
    }

    public int maxJump2(int[] stones) {
        int n = stones.length;
        int ret = stones[1] -  stones[0];
        for (int i = 2; i < n; i++) {
            ret = Math.max(ret, stones[i] - stones[i - 2]);
        }
        return ret;
    }
}
