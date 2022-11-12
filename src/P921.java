import java.util.*;
import java.io.*;
import java.lang.*;

// 921. Minimum Add to Make Parentheses Valid

public class P921 {
    public int minAddToMakeValid(String s) {
        int n = s.length();

        int t = 0, ret = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                if (t < 0) {
                    ret -= t;
                    t = 1;
                } else {
                    t += 1;
                }
            } else {
                t -= 1;
            }
        }

        if (t < 0) {
            ret -= t;
        } else {
            ret += t;
        }

        return ret;
    }
}
