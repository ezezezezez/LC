import java.io.*;
import java.lang.*;
import java.util.*;

// 2243. Calculate Digit Sum of a String

public class P2243 {
    public String digitSum(String s, int k) {
        int n = s.length();
        while (n > k) {
            int sum = 0, cnt = 0;
            String t = "";
            for (int i = 0; i < n; i++) {
                sum += s.charAt(i) - '0';
                cnt++;
                if (cnt == k) {
                    t += sum;
                    sum = 0;
                    cnt = 0;
                }
            }
            if (cnt > 0) {
                t += sum;
            }
            s = t;
            n = s.length();
        }
        return s;
    }
}
