import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 3316. Find Maximum Removals From Source String
public class P3316 {
    int INF = Integer.MAX_VALUE / 2;
    int ret = -INF;
    Set<Integer> indices = new HashSet<>();
    int[][] memo;
    String s, p;
    int[] pref;
    public int maxRemovals(String source, String pattern, int[] targetIndices) {
        this.s = source;
        this.p = pattern;
        int n = source.length(), m = pattern.length();
        for (int idx : targetIndices) indices.add(idx);

        pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + (indices.contains(i) ? 1 : 0);
        }

        memo = new int[n][m];
        for (int[] row : memo) Arrays.fill(row, -1);

        return dfs(n - 1, m - 1);
    }

    int dfs(int i, int j) {
        if (i < j) return -INF;
        if (j == -1) return pref[i + 1];

        if (memo[i][j] != -1) return memo[i][j];

        int t = dfs(i - 1, j) + (indices.contains(i) ? 1 : 0);
        if (s.charAt(i) == p.charAt(j)) {
            t = Math.max(t, dfs(i - 1, j - 1));
        }

        memo[i][j] = t;

        return memo[i][j];
    }
}
