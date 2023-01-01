import java.io.*;
import java.lang.*;
import java.util.*;

// 1410. HTML Entity Parser

public class P1432 {
    public int maxDiff(int num) {
        String numStr = String.valueOf(num);
        int n = numStr.length();
        int ret = 0;
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                // System.out.println("pre: " + numStr);
                String p1 = numStr.replaceAll("" + i, "" + j);
                // System.out.println("nxt: " + numStr);
                if (p1.charAt(0) == '0') continue;
                int num1 = Integer.parseInt(p1);

                for (int u = 0; u <= 9; u++) {
                    for (int v = 0; v <= 9; v++) {
                        String p2 = numStr.replaceAll("" + u, "" + v);
                        if (p2.charAt(0) == '0') continue;
                        int num2 = Integer.parseInt(p2);
                        // if (Math.abs(num1 - num2) > ret) {
                        //     System.out.println("i: " + i + ", j:" + j + ", u:" + u + ", v:" + v);
                        //     System.out.println(Math.abs(num1 - num2) + " " + numStr + " " + p1 + " " + p2);
                        // }
                        ret = Math.max(ret, Math.abs(num1 - num2));
                    }
                }
            }
        }
        return ret;
    }

    public int maxDiff2(int num) {
        String numStr = String.valueOf(num);
        int n = numStr.length();
        int mx = num, mn = num;
        for (int i = 0; i < n; i++) {
            if (numStr.charAt(i) != '9') {
                mx = Integer.parseInt(numStr.replace(numStr.charAt(i), '9'));
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == 0 && numStr.charAt(i) != '1') {
                mn = Integer.parseInt(numStr.replace(numStr.charAt(i), '1'));
                break;
            }
            if (i > 0 && numStr.charAt(i) != '0' && numStr.charAt(i) != '1') {
                mn = Integer.parseInt(numStr.replace(numStr.charAt(i), '0'));
                break;
            }
        }
        return mx - mn;
    }
}
