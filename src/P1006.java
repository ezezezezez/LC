import java.util.*;
import java.io.*;
import java.lang.*;

// 1006. Clumsy Factorial

public class P1006 {
    public int clumsy(int n) {
        int ret = 0;

        char[] ops = new char[]{'*', '/', '+', '-'};
        int op = 0, cur = 0, sign = 2;
        for (int i = n; i >= 1; i--) {
            int j = i;
            int glob = i--;
            int nxtSign = 0;
            while (i >= 1 && (nxtSign == 0 || nxtSign == 1)) {
                if (nxtSign == 0) {
                    glob *= i--;
                } else {
                    glob /= i--;
                }
                nxtSign++;
            }
            if (j == n) {
                ret += glob;
            } else {
                ret -= glob;
            }
            if (i >= 1) {
                ret += i--;
            }
            i++;
        }

        return ret;
    }
}
