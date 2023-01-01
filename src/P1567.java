import java.io.*;
import java.lang.*;
import java.util.*;

// 1567. Maximum Length of Subarray With Positive Product

public class P1567 {
    public int getMaxLen(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) nums[i] = 1;
            else if (nums[i] < 0) nums[i] = -1;
        }
        int ret = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                ret = Math.max(ret, check(list));
                list.clear();
            } else {
                list.add(nums[i]);
            }
        }
        if (!list.isEmpty()) ret = Math.max(ret, check(list));
        return ret;
    }

    int check(List<Integer> list) {
        int sz = list.size();
        int ret = 0;
        int posIdx = -1, negIdx = Integer.MIN_VALUE;
        int prod = 1;
        for (int i = 0; i < sz; i++) {
            prod *= list.get(i);
            if (prod > 0) {
                ret = i + 1;
            } else {
                if (negIdx == Integer.MIN_VALUE) {
                    negIdx = i;
                } else {
                    ret = Math.max(ret, i - negIdx);
                }
            }
        }
        return ret;
    }
}
