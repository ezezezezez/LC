import java.util.*;
import java.io.*;
import java.lang.*;

// 1052. Grumpy Bookstore Owner

public class P1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int ret = 0, cur = 0;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (grumpy[i] == 0 ? customers[i] : 0);
        }

        for (int i = 0, j = 0; i < n; i++) {
            cur += customers[i];
            int len = i - j + 1;
            if (len > minutes) {
                cur -= customers[j++];
            }
            // System.out.println(j + " " + i + " " + cur + " " + prefix[j]);
            ret = Math.max(ret, cur + prefix[j] + prefix[n] - prefix[i + 1]);
        }

        return ret;
    }
}
