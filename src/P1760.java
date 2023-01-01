import java.io.*;
import java.lang.*;
import java.util.*;

// 1760. Minimum Limit of Balls in a Bag

public class P1760 {
    public int minimumSize(int[] nums, int maxOperations) {
        int n = nums.length;
        int lo = 1, hi = (int)1e9;
        int t = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (valid(nums, mid, maxOperations)) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return t;
    }

    boolean valid(int[] nums, int cap, int opCnt) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= cap) continue;
            opCnt -= (nums[i] + cap - 1) / cap - 1;
            if (opCnt < 0) return false;
        }
        return true;
    }
}
