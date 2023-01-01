import java.io.*;
import java.lang.*;
import java.util.*;

// 1733. Minimum Number of People to Teach

public class P1733 {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length, len = friendships.length;
        Set<Integer>[] sets = new Set[m];
        Arrays.setAll(sets, k -> new HashSet<>());
        for (int i = 0; i < m; i++) {
            for (int lang : languages[i]) {
                sets[i].add(lang - 1);
            }
        }
        boolean[][] canCommunicate = new boolean[m][m];
        for (int[] friendship : friendships) {
            int u = friendship[0] - 1, v = friendship[1] - 1;
            for (int lang : sets[u]) {
                if (sets[v].contains(lang)) {
                    canCommunicate[u][v] = canCommunicate[v][u] = true;
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            Set<Integer> cur = new HashSet<>();
            for (int[] friendship : friendships) {
                int u = friendship[0] - 1, v = friendship[1] - 1;
                if (canCommunicate[u][v]) continue;
                if (!sets[u].contains(i - 1)) {
                    cur.add(u);
                }
                if (!sets[v].contains(i - 1)) {
                    cur.add(v);
                }
            }
            ret = Math.min(ret, cur.size());
        }
        return ret;
    }
}
