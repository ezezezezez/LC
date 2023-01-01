import java.io.*;
import java.lang.*;
import java.util.*;

// 2270. Number of Ways to Split Array

public class P2270 {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + nums[i];
        int ret = 0;
        for (int i = 0; i < n - 1; i++) {
            if (prefix[i + 1] >= prefix[n] - prefix[i + 1]) ret++;
        }
        return ret;
    }
}
