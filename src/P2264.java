import java.io.*;
import java.lang.*;
import java.util.*;

// 2269. Find the K-Beauty of a Number

public class P2264 {
    public String largestGoodInteger(String num) {
        int n = num.length();
        String ret = "";
        int mx = 0;
        for (int i = 2; i < n; i++) {
            String t = num.substring(i - 3 + 1, i + 1);
            if (t.charAt(0) == t.charAt(1) && t.charAt(1) == t.charAt(2)) {
                int val = Integer.parseInt(t);
                if (val >= mx) {
                    mx = val;
                    ret = t;
                }
            }
        }
        return ret;
    }
}
