import java.io.*;
import java.lang.*;
import java.util.*;

// 2501. Longest Square Streak in an Array

public class P2501 {
    public int longestSquareStreak(int[] nums) {
        int n = nums.length;
        int ret = -1;
        Set<Long> set = new HashSet<>();
        for (int num : nums) set.add((long)num);
        for (int num : nums) {
            long t = num;
            int cnt = 1;
            while (set.contains(t * t)) {
                t = t * t;
                cnt++;
            }
            if (cnt > 1) {
                ret = Math.max(ret, cnt);
            }
        }
        return ret;
    }
}
