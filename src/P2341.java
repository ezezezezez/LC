import java.io.*;
import java.lang.*;
import java.util.*;

// 2341. Maximum Number of Pairs in Array

public class P2341 {
    public int[] numberOfPairs(int[] nums) {
        int n = nums.length;
        int[] ret = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.merge(num, 1, Integer::sum);
        for (int key : map.keySet()) {
            int cnt = map.get(key);
            ret[0] += cnt / 2;
            if (cnt % 2 == 1) {
                ret[1]++;
            }
        }
        return ret;
    }
}
