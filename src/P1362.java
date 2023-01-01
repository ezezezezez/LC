import java.io.*;
import java.lang.*;
import java.util.*;

// 1361. Validate Binary Tree Nodes

public class P1362 {
    public int[] closestDivisors(int num) {
        int a = num + 1, b = num + 2;
        int[] ret = new int[2];
        int diff = Integer.MAX_VALUE;
        for (int i = 1; i <= Math.sqrt(a); i++) {
            if (a % i == 0) {
                int ni = a / i;
                if (Math.abs(i - ni) < diff) {
                    diff = Math.abs(i - ni);
                    ret[0] = i; ret[1] = ni;
                }
            }
        }
        for (int i = 1; i <= Math.sqrt(b); i++) {
            if (b % i == 0) {
                int ni = b / i;
                if (Math.abs(i - ni) < diff) {
                    diff = Math.abs(i - ni);
                    ret[0] = i; ret[1] = ni;
                }
            }
        }

        return ret;
    }
}
