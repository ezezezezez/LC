import java.io.*;
import java.lang.*;
import java.util.*;

// 2496. Maximum Value of a String in an Array

public class P2496 {
    public int maximumValue(String[] strs) {
        int n = strs.length;
        int ret = 0;
        for (String str : strs) {
            int m = str.length();
            boolean flag = true;
            for (int i = 0; i < m; i++) {
                char c = str.charAt(i);
                if (!Character.isDigit(c)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ret = Math.max(ret, Integer.parseInt(str));
            } else {
                ret = Math.max(ret, m);
            }
        }
        return ret;
    }
}
