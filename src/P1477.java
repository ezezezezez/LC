import java.io.*;
import java.lang.*;
import java.util.*;

// 1477. Find Two Non-overlapping Sub-arrays Each With Target Sum

public class P1477 {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] left = new int[n], right = new int[n];
        Arrays.fill(left, Integer.MAX_VALUE);
        Arrays.fill(right, Integer.MAX_VALUE);
        int leftCur = 0;
        Map<Integer, Integer> leftPos = new HashMap<>();
        leftPos.put(0, -1);
        for (int i = 0; i < n - 1; i++) {
            leftCur += arr[i];
            if (leftPos.containsKey(leftCur - target)) {
                int key = leftPos.get(leftCur - target);
                int len = i - key;
                left[i] = i - 1 >= 0 ? Math.min(left[i - 1], len) : len;
            } else if (i - 1 >= 0) {
                left[i] = left[i - 1];
            }
            leftPos.put(leftCur, i);
        }
        int rightCur = 0;
        Map<Integer, Integer> rightPos = new HashMap<>();
        rightPos.put(0, n);
        for (int i = n - 1; i > 0; i--) {
            rightCur += arr[i];
            if (rightPos.containsKey(rightCur - target)) {
                int key = rightPos.get(rightCur - target);
                int len = key - i;
                right[i] = i + 1 < n ? Math.min(right[i + 1], len) : len;
            } else if (i + 1 < n) {
                right[i] = right[i + 1];
            }
            rightPos.put(rightCur, i);
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            if (left[i] != Integer.MAX_VALUE && right[i + 1] != Integer.MAX_VALUE) {
                ret = Math.min(ret, left[i] + right[i + 1]);
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    public int minSumOfLengths2(int[] arr, int target) {
        int n = arr.length;
        int ret = Integer.MAX_VALUE;
        int cur = 0;
        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        int best = Integer.MAX_VALUE;

        for (int i = 0, j = 0; i < n; i++) {
            cur += arr[i];
            while (cur > target) {
                cur -= arr[j++];
            }
            if (cur == target) {
                if (j > 0 && f[j - 1] != Integer.MAX_VALUE) {
                    ret = Math.min(ret, f[j - 1] + i - j + 1);
                }
                best = Math.min(best, i - j + 1);
            }
            f[i] = best;
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
