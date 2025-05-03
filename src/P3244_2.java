// 3244. Shortest Distance After Road Addition Queries II
public class P3244_2 {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ret = new int[m];
        int[] nxt = new int[n];
        boolean[] lost = new boolean[n];
        for (int i = 0; i < n; i++) {
            nxt[i] = i + 1;
        }
        int last = n - 1;
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int l = query[0], r = query[1];
            if (lost[l] || lost[r]) {
                ret[i] = ret[i - 1];
                continue;
            }
            int p = l, step = 0;
            while (p != r) {
                p = nxt[p];
                step++;
                if (p != r) lost[p] = true;
            }
            last = last - step + 1;
            nxt[l] = r;
            ret[i] = last;
        }
        return ret;
    }
}
