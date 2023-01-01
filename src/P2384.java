import java.io.*;
import java.lang.*;
import java.util.*;

// 2384. Largest Palindromic Number

public class P2384 {
    public String largestPalindromic(String num) {
        int n = num.length();
        StringBuilder ret = new StringBuilder();
        int[] ds = new int[10];
        for (int i = 0; i < n; i++) {
            ds[num.charAt(i) - '0']++;
        }
        boolean allZero = true;
        for (int i = 1; i <= 9; i++) {
            if (ds[i] > 0) {
                allZero = false;
                break;
            }
        }
        if (allZero) return "0";
        boolean hasMoreThanOne = false;
        for (int i = 1; i <= 9; i++) {
            if (ds[i] > 1) {
                hasMoreThanOne = true;
                break;
            }
        }
        if (!hasMoreThanOne) {
            for (int i = 9; i >= 1; i--) {
                if (ds[i] == 1) return String.valueOf(i);
            }
        }
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 9; i >= 0; i--) {
            if (ds[i] % 2 == 1) {
                dq.offer(i);
                break;
            }
        }
        for (int i = 0; i <= 9; i++) {
            int cnt = ds[i] % 2 == 0 ? ds[i] : (ds[i] - 1);
            for (int j = 0; j < cnt; j += 2) {
                dq.offerLast(i);
                dq.offerFirst(i);
            }
        }
        while (!dq.isEmpty()) ret.append(dq.poll());
        return ret.toString();
    }
}
