import java.io.*;
import java.lang.*;
import java.util.*;

// 1769. Minimum Number of Operations to Move All Balls to Each Box

public class P1769 {
    public int[] minOperations(String boxes) {
        char[] cs = boxes.toCharArray();
        int n = cs.length;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (cs[j] == '1') {
                    sum += Math.abs(j - i);
                }
            }
            ret[i] = sum;
        }
        return ret;
    }
}
