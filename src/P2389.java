import java.io.*;
import java.lang.*;
import java.util.*;

// 2389. Longest Subsequence With Limited Sum

public class P2389 {
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(nums);
        int[] ret = new int[m];
        for (int i = 0; i < m; i++) {
            int q = queries[i];
            int sum = 0;
            int j = 0;
            while (j < n && sum + nums[j] <= q) {
                sum += nums[j];
                j++;
            }
            ret[i] = j;
        }
        return ret;
    }
}
