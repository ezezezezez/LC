import java.io.*;
import java.lang.*;
import java.util.*;

// 2232. Minimize Result by Adding Parentheses to Expression

public class P2232 {
    public String minimizeResult(String exp) {
        int n = exp.length();
        int plusIdx = exp.indexOf('+');
        String ret = "";
        int retVal = Integer.MAX_VALUE;
        for (int i = 0; i < plusIdx; i++) {
            for (int j = plusIdx + 1; j < n; j++) {
                String t = exp.substring(0, i) + '(' + exp.substring(i, j + 1) + ')' + exp.substring(j + 1);
                int tVal = calc(t);
                if (tVal < retVal) {
                    retVal = tVal;
                    ret = t;
                }
            }
        }
        return ret;
    }

    int calc(String t) {
        int n = t.length();
        int leftIdx = t.indexOf('('), rightIdx = t.indexOf(')');
        int plusIdx = t.indexOf('+');
        int a = leftIdx == 0 ? 1 : Integer.parseInt(t.substring(0, leftIdx));
        int b = Integer.parseInt(t.substring(leftIdx + 1, plusIdx));
        int c = Integer.parseInt(t.substring(plusIdx + 1, rightIdx));
        int d = rightIdx == n - 1 ? 1 : Integer.parseInt(t.substring(rightIdx + 1));
        return a * (b + c) * d;
    }
}
