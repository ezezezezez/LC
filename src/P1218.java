import java.util.*;
import java.io.*;
import java.lang.*;

// 1218. Longest Arithmetic Subsequence of Given Difference

public class P1218 {
    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        int ret = 1;
        map.put(arr[0], 1);

        for (int i = 1; i < n; i++) {
            final int val = arr[i];
            int pre = arr[i] - difference;
            ret = Math.max(ret, 1 + map.getOrDefault(pre, 0));
            map.put(arr[i], 1 + map.getOrDefault(pre, 0));
        }

        return ret;
    }
}
