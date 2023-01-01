import java.io.*;
import java.lang.*;
import java.util.*;

// 1541. Minimum Insertions to Balance a Parentheses Strings

public class P1541 {
    public int minInsertions(String s) {
        int n = s.length();
        int t = 0;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                t += 2;
            } else {
                if (i < n - 1) {
                    char nc = s.charAt(i + 1);
                    if (nc == '(') {
                        ret++;
                    } else {
                        i++;
                    }
                    t -= 2;
                } else {
                    ret++;
                    t -= 2;
                }
                if (t < 0) {
                    ret++;
                    t = 0;
                }
            }
        }
        return ret + t;
    }
}
