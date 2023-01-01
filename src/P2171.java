import java.io.*;
import java.lang.*;
import java.util.*;

// 2171. Removing Minimum Number of Magic Beans

public class P2171 {
    public long minimumRemoval(int[] beans) {
        int n = beans.length;
        Arrays.sort(beans);
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + beans[i];
        long ret = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            long cur = prefix[n] - (0L + n - i) * beans[i];
            ret = Math.min(ret, cur);
        }
        return ret;
    }
}
