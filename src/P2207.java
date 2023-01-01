import java.io.*;
import java.lang.*;
import java.util.*;

// 2207. Maximize Number of Subsequences in a String

public class P2207 {
    public long maximumSubsequenceCount(String text, String pattern) {
        int n = text.length();
        String astr = pattern.charAt(0) + text, bstr = text + pattern.charAt(1);
        int acnt1 = 0, acnt2 = 0, bcnt1 = 0, bcnt2 = 0;
        long ret1 = 0, ret2 = 0;
        for (int i = 0; i < astr.length(); i++) {
            if (astr.charAt(i) == pattern.charAt(0)) {
                if (astr.charAt(i) == pattern.charAt(1)) {
                    ret1 += acnt1;
                }
                acnt1++;
            } else if (astr.charAt(i) == pattern.charAt(1)) {
                ret1 += acnt1;
            }
        }
        for (int i = 0; i < bstr.length(); i++) {
            if (bstr.charAt(i) == pattern.charAt(0)) {
                if (bstr.charAt(i) == pattern.charAt(1)) {
                    ret2 += bcnt1;
                }
                bcnt1++;
            } else if (bstr.charAt(i) == pattern.charAt(1)) {
                ret2 += bcnt1;
            }
        }
        return Math.max(ret1, ret2);
    }
}
