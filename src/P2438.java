import java.io.*;
import java.lang.*;
import java.util.*;

// 2438. Range Product Queries of Powers

public class P2438 {
    public int[] productQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ret = new int[m];
        int idx = 0;
        int[] arr = new int[31];
        for (int i = 0; i < 31; i++) {
            if (((1 << i) & n) > 0) {
                arr[idx++] = 1 << i;
            }
        }
        int mod = (int)(1e9 + 7);
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int left = query[0], right = query[1];
            long prod = 1;
            for (int j = left; j <= right; j++) {
                prod = prod * arr[j] % mod;
            }
            ret[i] = (int)prod;
        }
        return ret;
    }
}
