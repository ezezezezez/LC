import java.util.*;
import java.io.*;
import java.lang.*;

// 1191. K-Concatenation Maximum Sum

public class P1191 {
    int mod = (int)(1e9 + 7);
    public int kConcatenationMaxSum(int[] arr, int k) {
        int n = arr.length;
        int ret = 0;

        long sum = 0;
        boolean allNeg = true;
        for (int num : arr) {
            sum += num;
            if (num > 0) allNeg = false;
        }

        if (allNeg) return 0;

        int leftMx = arr[0], tMx = arr[0];
        for (int i = 1; i < n; i++) {
            leftMx = Math.max(arr[i], arr[i] + leftMx);
            tMx = Math.max(tMx, leftMx);
        }

        if (k == 1) return tMx;

        int rightMx = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMx = Math.max(arr[i], arr[i] + rightMx);
        }

        int mx = arr[0], ee = arr[0];
        for (int i = 1; i < 2 * n; i++) {
            ee = Math.max(arr[i % n], arr[i % n] + ee);
            mx = Math.max(mx, ee);
        }

        if (k == 2) return mx % mod;

        return (int)(Math.max(mx, (k - 2) * sum + leftMx + rightMx) % mod);
    }
}
