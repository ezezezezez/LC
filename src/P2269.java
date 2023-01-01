import java.io.*;
import java.lang.*;
import java.util.*;

// 2269. Find the K-Beauty of a Number

public class P2269 {
    public int divisorSubstrings(int num, int k) {
        int ret = 0;
        String s = String.valueOf(num);
        int n = s.length();
        for (int i = k - 1; i < n; i++) {
            String t = s.substring(i - k + 1, i + 1);
            int val = Integer.parseInt(t);
            if (val == 0) continue;
            if (num % val == 0) ret++;
        }
        return ret;
    }
}
