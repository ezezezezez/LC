import java.util.HashSet;
import java.util.Set;

// 2718. Sum of Matrix After Queries
public class P2718 {
    public long matrixSumQueries(int n, int[][] queries) {
        int m = queries.length;
        long ret = 0;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for (int i = m - 1; i >= 0; i--) {
            int[] query = queries[i];
            int type = query[0], index = query[1], val = query[2];
            if (type == 0) {
                if (rows.contains(index)) continue;
                ret += 1L * val * (n - cols.size());
                rows.add(index);
            } else {
                if (cols.contains(index)) continue;
                ret += 1L * val * (n - rows.size());
                cols.add(index);
            }
        }
        return ret;
    }
}
