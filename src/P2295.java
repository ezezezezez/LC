import java.io.*;
import java.lang.*;
import java.util.*;

// 2295. Replace Elements in an Array

public class P2295 {
    public int[] arrayChange(int[] nums, int[][] operations) {
        int n = nums.length, m = operations.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }
        for (int[] operation : operations) {
            int a = operation[0], b = operation[1];
            int idx = map.get(a);
            map.remove(a);
            nums[idx] = b;
            map.put(b, idx);
        }
        return nums;
    }
}
