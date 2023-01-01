import java.io.*;
import java.lang.*;
import java.util.*;

// 1375. Number of Times Binary String Is Prefix-Aligned

public class P1375 {
    public int numTimesAllBlue(int[] flips) {
        int n = flips.length;

        int ret = 0;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur = Math.max(cur, flips[i]);
            if (cur == i + 1) ret++;
        }

        return ret;
    }
}
