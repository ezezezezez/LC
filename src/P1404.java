import java.io.*;
import java.lang.*;
import java.util.*;

// 1400. Construct K Palindrome Strings

public class P1404 {
    public int numSteps(String s) {
        int n = s.length();
        int ret = 0;
        StringBuilder sb = new StringBuilder(s);

        while (sb.length() != 1 || sb.charAt(0) != '1') {
            char c = sb.charAt(sb.length() - 1);
            if (c == '0') {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                int j = sb.length() - 1;
                while (j >= 0 && sb.charAt(j) == '1') {
                    sb.setCharAt(j--, '0');
                }
                if (j < 0) {
                    sb.insert(0, '1');
                } else {
                    sb.setCharAt(j, '1');
                }
            }
            ret++;
        }

        return ret;
    }
}
