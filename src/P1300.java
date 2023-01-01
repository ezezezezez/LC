import java.util.*;
import java.io.*;
import java.lang.*;

// 1300. Sum of Mutated Array Closest to Target

public class P1300 {
    public int findBestValue(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        int ret = arr[n - 1], diff = Math.abs(prefix[n] - target);
        for (int i = arr[n - 1] - 1; i >= 0; i--) {
            int lo = 0, hi = n - 1;
            int t = -1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                if (arr[mid] > i) {
                    t = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            int sum = prefix[t] + (n - t) * i;
            if (Math.abs(sum - target) <= diff) {
                ret = i;
                diff = Math.abs(sum - target);
            }
        }

        return ret;
    }

    public int findBestValue2(int[] arr, int target) {
        int n = arr.length;
        int mx = arr[0];
        for (int i = 1; i < n; i++) mx = Math.max(mx, arr[i]);
        int lo = 0, hi = mx;
        int t = -1, diff = Integer.MAX_VALUE;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            int sum = 0;
            for (int num : arr) {
                sum += num > mid ? mid : num;
            }
            int v = sum - target;

            if (v > 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }

            int newDiff = Math.abs(v);
            if (newDiff < diff) {
                diff = newDiff;
                t = mid;
            } else if (diff == newDiff && mid < t) {
                t = mid;
            }
        }

        return t;
    }
}
