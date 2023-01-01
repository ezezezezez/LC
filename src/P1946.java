import java.io.*;
import java.lang.*;
import java.util.*;

// 1946. Largest Number After Mutating Substring

public class P1946 {
    public String maximumNumber(String num, int[] change) {
        int n = num.length();
        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < n; i++) {
            int d = sb.charAt(i) - '0';
            if (change[d] > d) {
                int j = i;
                while (j < n && change[sb.charAt(j) - '0'] >= sb.charAt(j) - '0') {
                    sb.setCharAt(j, (char)(change[sb.charAt(j) - '0'] + '0'));
                    j++;
                }
                break;
            }
        }
        return sb.toString();
    }
}
