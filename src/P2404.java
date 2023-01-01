import java.io.*;
import java.lang.*;
import java.util.*;

// 2409. Count Days Spent Together

public class P2404 {
    public int mostFrequentEven(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (num % 2 == 0) map.merge(num, 1, Integer::sum);
        }
        if (map.isEmpty()) return -1;
        int mxCnt = 0, x = Integer.MAX_VALUE;
        for (int key : map.keySet()) {
            int cnt = map.get(key);
            if (cnt > mxCnt) {
                x = key;
                mxCnt = cnt;
            } else if (cnt == mxCnt) {
                x = Math.min(x, key);
            }
        }
        return x;
    }
}
