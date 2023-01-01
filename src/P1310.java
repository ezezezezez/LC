import java.util.*;
import java.io.*;
import java.lang.*;

// 1310. XOR Queries of a Subarray

public class P1310 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length, m = queries.length;
        int[] ret = new int[m];
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] ^ arr[i];

        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int left = query[0], right = query[1];
            ret[i] = prefix[right + 1] ^ prefix[left];
        }

        return ret;
    }
}
