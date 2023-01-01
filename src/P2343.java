import java.io.*;
import java.lang.*;
import java.util.*;

// 2343. Query Kth Smallest Trimmed Number

public class P2343 {
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int len = nums[0].length();
        int[] ret = new int[m];
        Integer[] ids = new Integer[n];
        for (int i = 0; i < m; i++) {
            int k = queries[i][0], trim = queries[i][1];
            String[] arr = new String[n];
            for (int j = 0; j < n; j++) {
                arr[j] = nums[j].substring(len - trim);
            }
            Arrays.setAll(ids, u -> u);
            Arrays.sort(ids, (a, b) -> {
                if (!arr[a].equals(arr[b])) {
                    return arr[a].compareTo(arr[b]);
                }
                return Integer.compare(a, b);
            });
            ret[i] = ids[k - 1];
        }
        return ret;
    }
}
