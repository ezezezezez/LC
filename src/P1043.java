import java.util.*;
import java.io.*;
import java.lang.*;

// 1043. Partition Array for Maximum Sum

public class P1043 {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] f = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int mx = arr[i];
            for (int j = 0; j < k; j++) {
                int l = i - j;
                int len = i - l + 1;
                if (l == -1) break;
                mx = Math.max(mx, arr[l]);
                f[i + 1] = Math.max(f[i + 1], f[l] + mx * len);
            }
            // System.out.println(f[i + 1]);
        }

        return f[n];
    }
}
