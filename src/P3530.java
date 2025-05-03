import java.math.BigInteger;
import java.util.*;

// 3530. Maximum Profit from Valid Topological Order in DAG
public class P3530 {
    int ret;
    int order = 1;
    int n;
    Map<Integer, List<Integer>> map;
    int[] score;
    int[] in;
    int[] memo;
    public int maxProfit(int n, int[][] edges, int[] score) {
        this.n = n;
        this.score = score;
        map = new HashMap<>();
        in = new int[n];
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            map.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            in[b]++;
        }
        memo = new int[1 << n];
        Arrays.fill(memo, -1);
        int mask = (1 << n) - 1;
        dfs(mask);
        return memo[mask];
    }

    int dfs(int mask) {
        if (mask == 0) {
            return 0;
        }
        if (memo[mask] != -1) return memo[mask];
        int t = 0;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) > 0 && in[i] == 0) {
                int newMask = mask;
                newMask -= (1 << i);
                if (map.containsKey(i)) {
                    for (int j : map.get(i)) {
                        in[j]--;
                    }
                }
                int currentScore = order * score[i];
                order++;
                t = Math.max(t, currentScore + dfs(newMask));
                order--;
                if (map.containsKey(i)) {
                    for (int j : map.get(i)) {
                        in[j]++;
                    }
                }
            }
        }
        memo[mask] = t;
        return t;
    }
}
