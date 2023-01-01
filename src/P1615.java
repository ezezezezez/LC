import java.io.*;
import java.lang.*;
import java.util.*;

// 1615. Maximal Network Rank

public class P1615 {
    public int maximalNetworkRank(int n, int[][] roads) {
        int m = roads.length;
        int[] cnt = new int[n];
        boolean[][] connected = new boolean[n][n];
        for (int[] road : roads) {
            int a = road[0], b = road[1];
            connected[a][b] = connected[b][a] = true;
            cnt[a]++;
            cnt[b]++;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ret = Math.max(ret, cnt[i] + cnt[j] + (connected[i][j] ? -1 : 0));
            }
        }
        return ret;
    }
}
