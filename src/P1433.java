import java.io.*;
import java.lang.*;
import java.util.*;

// 1433. Check If a String Can Break Another String

public class P1433 {
    public boolean checkIfCanBreak(String s1, String s2) {
        int n = s1.length();
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();
        Arrays.sort(cs1); Arrays.sort(cs2);
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (cs1[i] < cs2[i]) {
                flag = false;
                break;
            }
        }
        if (flag) return true;
        flag = true;
        for (int i = 0; i < n; i++) {
            if (cs2[i] < cs1[i]) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
