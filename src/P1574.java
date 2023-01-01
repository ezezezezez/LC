import java.io.*;
import java.lang.*;
import java.util.*;

// 1574. Shortest Subarray to be Removed to Make Array Sorted

public class P1574 {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int left = -1, right = n;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                left = i;
                break;
            }
        }
        for (int i = n - 1; i >= 1; i--) {
            if (arr[i - 1] > arr[i]) {
                right = i;
                break;
            }
        }

        if (left == -1) return 0;
        int l = 0, r = right;
        int ret = n - Math.max(left + 1, n - right);
        // System.out.println(left + " " + right);
        while (l <= left && r < n) {
            if (arr[l] <= arr[r]) {
                int lCnt = l + 1, rCnt = n - r;
                ret = Math.min(ret, n - lCnt - rCnt);
                l++;
            } else {
                r++;
            }
        }
        return ret;
    }
}
