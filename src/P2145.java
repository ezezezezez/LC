import java.io.*;
import java.lang.*;
import java.util.*;

// 2145. Count the Hidden Sequences

public class P2145 {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        int n = differences.length;
        int ret = 0;
        long cur = 0, mx = cur, mn = cur;
        for (int d : differences) {
            cur += d;
            mx = Math.max(mx, cur);
            mn = Math.min(mn, cur);
        }
        long diff = mx - mn;
        if (upper - lower < diff) return ret;
        return (int)(upper - lower - diff + 1);
    }
}
