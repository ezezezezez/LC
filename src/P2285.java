import java.io.*;
import java.lang.*;
import java.util.*;

// 2285. Maximum Total Importance of Roads

public class P2285 {
    public long maximumImportance(int n, int[][] roads) {
        long[] cnt = new long[n];
        for (int[] road : roads) {
            int a = road[0], b = road[1];
            cnt[a]++;
            cnt[b]++;
        }
        Arrays.sort(cnt);
        long ret = 0;
        long k = n;
        for (int i = n - 1; i >= 0; i--) {
            ret += k * cnt[i];
            k--;
        }
        return ret;
    }
}
