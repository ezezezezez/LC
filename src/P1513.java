import java.io.*;
import java.lang.*;
import java.util.*;

// 1513. Number of Substrings With Only 1s

public class P1513 {
    int mod = (int)(1e9 + 7);
    public int numSub(String s) {
        int n = s.length();
        int ret = 0;
        for (int i = 0, j = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                j = i;
                while (i < n && s.charAt(i) == '1') i++;
                int len = i - j + 1;
                ret = (int)(ret + (long)len * (len - 1) / 2 % mod) % mod;
                i--;
            }
        }
        return ret;
    }
}
