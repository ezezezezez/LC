import java.io.*;
import java.lang.*;
import java.util.*;

// 2400. Number of Ways to Reach a Position After Exactly k Steps

public class P2400 {
    int mod = (int)(1e9 + 7);
    Map<Integer, int[]> memo = new HashMap<>();
    int k;
    public int numberOfWays(int startPos, int endPos, int k) {
        this.k = k;
        int ret = dfs(startPos, endPos, k);
        return ret == -1 ? 0 : ret;
    }

    int dfs(int pos, int end, int step) {
        if (!memo.containsKey(end)) {
            memo.put(end, new int[k + 1]);
            Arrays.fill(memo.get(end), -1);
        }
        if (step == 0) {
            return pos == end ? 1 : -1;
        }
        if (memo.containsKey(end) && memo.get(end)[step] != -1) {
            return memo.get(end)[step];
        }
        int ret = 0;
        int a = dfs(pos, end - 1, step - 1);
        if (a != -1) ret = (ret + a) % mod;
        int b = dfs(pos, end + 1, step - 1);
        if (b != -1) ret = (ret + b) % mod;

        memo.get(end)[step] = ret;
        return ret > 0 ? ret : -1;
    }
}
