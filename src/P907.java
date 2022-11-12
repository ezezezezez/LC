import java.util.*;
import java.io.*;
import java.lang.*;

// 907. Sum of Subarray Minimums

public class P907 {
    int mod = (int)(1e9 + 7);
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] l = new int[n], r = new int[n];

        for (int i = 0; i < n; i++) {
            int t = i - 1;
            while (t >= 0 && arr[t] > arr[i]) {
                t = l[t];
            }
            l[i] = t;
        }
        // System.out.println(Arrays.toString(l));

        for (int i = n - 1; i >= 0; i--) {
            int t = i + 1;
            while (t < n && arr[t] >= arr[i]) {
                t = r[t];
            }
            r[i] = t;
        }
        // System.out.println(Arrays.toString(r));

        long ret = 0;
        for (int i = 0; i < n; i++) {
            int llen = i - l[i], rlen = r[i] - i;
            ret = (ret + (long) arr[i] * llen * rlen) % mod;
        }

        return (int)ret;
    }
}
