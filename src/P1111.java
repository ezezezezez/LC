import java.util.*;
import java.io.*;
import java.lang.*;

// 1111. Maximum Nesting Depth of Two Valid Parentheses Strings

public class P1111 {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ret = new int[n];
        for (int i = 0, d = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                d++;
                ret[i] = d % 2;
            } else {
                ret[i] = d % 2;
                d--;
            }
        }

        return ret;
    }
}
