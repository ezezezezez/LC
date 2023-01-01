import java.io.*;
import java.lang.*;
import java.util.*;

// 1802. Maximum Value at a Given Index in a Bounded Array

public class P1802 {
    public int maxValue(int n, int index, int maxSum) {
        int lo = 1, hi = (int)1e9;
        int t = -1;
        while (lo <= hi) {
            long mid = (lo + hi) >> 1;
            long sum = 0;
            long leftCnt = index + 1, rightCnt = n - leftCnt + 1;
            long leftMost = mid - leftCnt + 1, rightMost = mid - rightCnt + 1;
            // if (mid == 3) System.out.println(leftCnt + " " + rightCnt);
            // if (mid == 3) System.out.println(leftMost + " " + rightMost);
            if (leftMost <= 0) {
                long leftBound = index - mid;
                sum += (1 + mid) * mid / 2;
                sum += leftBound + 1;
            } else {
                sum += (leftMost + mid) * leftCnt / 2;
            }
            if (rightMost <= 0) {
                long rightBound = index + mid;
                sum += (1 + mid) * mid / 2;
                sum += n - rightBound;
            } else {
                sum += (rightMost + mid) * rightCnt / 2;
            }
            sum -= mid;

            if (sum <= maxSum) {
                t = (int)mid;
                lo = (int)(mid + 1);
            } else {
                hi = (int)(mid - 1);
            }
        }
        return t;
    }
}
