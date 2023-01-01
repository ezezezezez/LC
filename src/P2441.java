import java.io.*;
import java.lang.*;
import java.util.*;

// 2441. Largest Positive Integer That Exists With Its Negative

public class P2441 {
    public int findMaxK(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int ret = -1;
        for (int num : nums) {
            if (num > 0 && set.contains(-num)) ret = Math.max(ret, num);
        }
        return ret;
    }
}
