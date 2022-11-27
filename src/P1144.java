import java.util.*;
import java.io.*;
import java.lang.*;

// 1144. Decrease Elements To Make Array Zigzag

public class P1144 {
    public int movesToMakeZigzag(int[] nums) {
        int n = nums.length;
        int pre1 = nums[0], pre2 = nums[0];
        int cnt1 = 0, cnt2 = 0;
        for (int i = 1; i < n; i++) {
            if (i % 2 == 1) {
                if (nums[i] > pre1) {
                    pre1 = nums[i];
                } else {
                    cnt1 += pre1 - (nums[i] - 1);
                    pre1 = nums[i];
                }
            } else {
                if (nums[i] < pre1) {
                    pre1 = nums[i];
                } else {
                    cnt1 += nums[i] - (pre1 - 1);
                    pre1 = pre1 - 1;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (i % 2 == 1) {
                if (nums[i] < pre2) {
                    pre2 = nums[i];
                } else {
                    cnt2 += nums[i] - (pre2 - 1);
                    pre2 = pre2 - 1;
                }
            } else {
                if (nums[i] > pre2) {
                    pre2 = nums[i];
                } else {
                    cnt2 += pre2 - (nums[i] - 1);
                    pre2 = nums[i];
                }
            }
        }
        return Math.min(cnt1, cnt2);
    }
}
