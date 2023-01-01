import java.io.*;
import java.lang.*;
import java.util.*;

// 1764. Form Array by Concatenating Subarrays of Another Array

public class P1764 {
    public boolean canChoose(int[][] groups, int[] nums) {
        int n = groups.length, m = nums.length;
        int ns = 0;
        for (int p = 0; p < n; p++) {
            int[] group = groups[p];
            int sz = group.length;
            boolean match = false;
            for (int i = ns; i <= m - sz; i++) {
                boolean flag = true;
                for (int j = i; j < i + sz; j++) {
                    if (nums[j] != group[j - i]) {
                        // System.out.println(i + " " + (j - i));
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    if (p == n - 1) return true;
                    match = true;
                    ns = i + sz;
                    break;
                }
            }
            // System.out.println(match);
            // System.out.println(ns);
            if (!match) return false;
        }
        return false;
    }
}
