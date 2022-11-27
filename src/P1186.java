import java.util.*;
import java.io.*;
import java.lang.*;

// 1186. Maximum Subarray Sum with One Deletion

public class P1186 {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int sum = arr[0], mx = arr[0];
        left[0] = arr[0];
        for (int i = 1; i < n; i++) {
            sum = Math.max(arr[i], arr[i] + sum);
            left[i] = sum;
            mx = Math.max(mx, sum);
        }
        int[] right = new int[n];
        right[n - 1] = arr[n - 1];
        sum = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sum = Math.max(arr[i], arr[i] + sum);
            right[i] = sum;
        }

        for (int i = 1; i < n - 1; i++) {
            mx = Math.max(mx, left[i - 1] + right[i + 1]);
        }
        return mx;
    }
}
