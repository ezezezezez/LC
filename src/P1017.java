import java.util.*;
import java.io.*;
import java.lang.*;

// 1017. Convert to Base -2

public class P1017 {
    public String baseNeg2(int n) {
        if (n == 0) return "0";
        int t = n;
        int[] arr = new int[100];
        int idx = 0, jw = 0;

        while (t != 0) {
            int m = t % -2;

            if (m == 1) {
                arr[idx] = 1 + jw;
                if (arr[idx] == 2) {
                    arr[idx] = 0;
                    jw = -1;
                } else {
                    jw = 0;
                }
            } else if (m == -1) {
                arr[idx] = -1 + jw;
                if (arr[idx] == -2) {
                    arr[idx] = 0;
                    jw = 1;
                } else if (arr[idx] == -1) {
                    arr[idx] = 1;
                    jw = 1;
                } else {
                    jw = 0;
                }
            } else {
                if (jw == 1) {
                    arr[idx] = 1;
                    jw = 0;
                } else if (jw == -1) {
                    arr[idx] = 1;
                    jw = 1;
                }
            }

            idx++;
            t /= -2;
        }
        if (jw == -1) {
            arr[idx] = 1;
            arr[idx + 1] = 1;
            idx++;
        } else if (jw == 1) {
            arr[idx] = 1;
        }

        StringBuilder ret = new StringBuilder();
        if (arr[idx] == 1) ret.append(1);
        for (int i = idx - 1; i >= 0; i--) {
            ret.append(arr[i]);
        }
        return ret.toString();
    }
}
